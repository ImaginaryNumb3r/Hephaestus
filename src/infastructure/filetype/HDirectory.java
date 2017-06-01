package infastructure.filetype;

import com.sun.istack.internal.NotNull;
import core.util.collections.iterating.AbstractListIterator;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible;
import infastructure.filetype.interfaces.AbstractDirectory;
import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import functional.BiSupplier;
import infastructure.path.DirectoryNode;
import infastructure.path.PathFactory;
import infastructure.path.exceptions.PathsNotMatchingException;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.function.Consumer;

/**
 * An improved implementation of the default Java file, representing a directory on the file system
 * @author Patrick
 * @since 28.05.2016
 */
// TODO: Consider changing returned files and directories to optional. To signify that you might get a null as a return value
public class HDirectory extends HEntry implements Iterable<HDirectory>, AbstractDirectory {
    private final AbsoluteDirectory _absoluteDirectory;

    //<editor-fold desc="Constructors">
    public HDirectory (AbsolutePath location){
        this(location.getAbsolutePath());
    }

    public HDirectory(String file) {
        this(new File(file));

        if (!isDirectory()){
            throw new IllegalArgumentException("Make into factory!");
        }
    }

    public HDirectory(HEntry file){
        this(file.getFile());
    }

    public HDirectory(File file) {
        super(file);

        _absoluteDirectory = PathFactory.makeAbsoluteDirectory(file.getAbsolutePath());
    }
    //</editor-fold>

    //<editor-fold desc="Directory Methods">
    public void forEachFile(@NotNull Consumer<HFile> action) {
        Contract.checkNull(action);
        Optional<List<HFile>> optionalFiles = getFiles();

        optionalFiles.ifPresent(files -> files.forEach(action));
    }

    @Override
    public AbsoluteDirectory getPath() {
        return _absoluteDirectory;
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        return _absoluteDirectory.getParentPath();
    }

    @Override
    public String getName() {
        return _absoluteDirectory.tailNode().getNodeName();
    }

    /**
     * Always returns itself
     * @return the called object
     */
    @Override
    public HDirectory toDirectory() {
        return this;
    }

    /**
     * Always returns null
     * @return null, because a directory cannot be a file
     */
    @Override
    public HFile toFile() {
        return null;
    }

    /**
     * This object is a directory
     * @return false
     */
    @Override
    public boolean isDirectory() {
        return true;
    }

    /**
     * This object is not a file
     * @return true
     */
    @Override
    public boolean isFile() {
        return false;
    }

    /**
     * Returns the list of entries, as specified with the filters.
     * If the file does not exist or if an I/O exception occurred, the optional will be empty
     * Returns null if an I/O Exception occurred or if the file does not exist.
     * @param typeFilter First filter to determine the type
     * @param generalFilter Second filter for other sorts of filtering
     * @param constructor The Constructor to create the wanted type of value
     * @param <T> the desired type of value
     * @throws  SecurityException
     *          If a security manager exists and its {@link
     *          SecurityManager#checkRead(String)} method denies read access to
     *          the directory
     * @return List of creates types or empty optional.
     */
    public <T> Optional<List<T>> getEntries(FileFilter typeFilter, FileFilter generalFilter, BiSupplier<T, File> constructor){
        FileFilter filter = pathname -> typeFilter.accept(pathname) && generalFilter.accept(pathname);

        File[] files = _file.listFiles(filter);
        ArrayList<T> hFiles = null;

        if (files != null){
            hFiles = new ArrayList<>(files.length);

            for (int i = 0; i != files.length; ++i){
                hFiles.add(i, constructor.make(files[i]));
            }
        }

        return Optional.ofNullable(hFiles);
    }


    /**
     * Returns list of directories that match the filter
     * @param filter to sort unwanted entries
     * @return List of directories that match the filter.
     *
     */
    public Optional<List<HDirectory>> getDirectories(FileFilter filter) {
        FileFilter baseFilter = File::isDirectory;

        return getEntries(baseFilter, filter, HDirectory::new);
    }

    /**
     * Returns the list of existing sub-directories in this directory
     * Returns null if an I/O Exception occurred or if the file does not exist.
     * returns array of
     * @throws  SecurityException
     *          If a security manager exists and its {@link
     *          SecurityManager#checkRead(String)} method denies read access to
     *          the directory
     * @return  Null if the directory does not exist or if an I/O error occurs
     *          list of existing sub-directories in this directory
     */
    public Optional<List<HDirectory>> getDirectories() {
        return getDirectories(arg -> true);
    }

    /**
     * Returns the list of existing sub-directories in this directory
     * Returns null if an I/O Exception occurred or if the file does not exist.
     * @param filter to sort unwanted entries
     * @throws  SecurityException
     *          If a security manager exists and its {@link
     *          SecurityManager#checkRead(String)} method denies read access to
     *          the directory
     * @return  Null if the directory does not exist or if an I/O error occurs
     *          Otherwise, a list of existing sub-directories that match he filter
     */
    public Optional<List<HFile>> getFiles(FileFilter filter){
        FileFilter baseFilter = File::isFile;

        return getEntries(baseFilter, filter,  HFile::new);
    }

    /**
     * returns array of existing files in this directory
     * @return array of existing files in this directory
     */
    public Optional<List<HFile>> getFiles(){
        return getFiles(arg -> true);
    }

    /**
     * Directory InnerIterator
     * @return InnerIterator of the directories
     */

    public ListIterator<HDirectory> iterator(){
        return new DirectoryIterator<>();
    }
    //</editor-fold>

    //<editor-fold desc="Absolute Path">
    @Override
    public AbsoluteDirectory concat(RelativeDirectory rel) {
        return _absoluteDirectory.concat(rel);
    }

    @Override
    public AbsoluteDirectory add(String rel) {
        return _absoluteDirectory.add(rel);
    }

    @Override
    public AbsoluteDirectory remove(RelativeDirectory removal) throws PathsNotMatchingException{
        return _absoluteDirectory.remove(removal);
    }

    @Override
    public RelativeDirectory remove(AbsoluteDirectory absDir) throws PathsNotMatchingException {
        return _absoluteDirectory.remove(absDir);
    }

    @Override
    public AbsoluteFile concat(RelativeFile relFile) {
        return _absoluteDirectory.concat(relFile);
    }

    @Override
    public HDirectory copy() {
        return new HDirectory(this);
    }

    @Override
    public boolean isSubPath(RelativeDirectory relDir) {
        return false;
    }

    @Override
    public boolean isSubPath(AbsoluteDirectory absDir) {
        return false;
    }

    @Override
    public boolean hasParent() {
        return _absoluteDirectory.hasParent();
    }

    @Override
    public RelativeDirectory getParent() {
        return _absoluteDirectory.getParent();
    }
    //</editor-fold>

    //<editor-fold desc="Path Methods">
    @Override
    public int length() {
        return _absoluteDirectory.length();
    }

    public DirectoryNode headNode() {
        return _absoluteDirectory.headNode();
    }

    @Override
    public DirectoryNode tailNode() {
        return _absoluteDirectory.tailNode();
    }

    @Override
    public boolean equals(Path path) {
        return _absoluteDirectory.equals(path);
    }

    @Override
    public boolean equals(String path) {
        return false;
    }
    //</editor-fold>

    /******************
     * Node InnerIterator
     /*****************/
    protected class DirectoryIterator<T extends HDirectory> extends AbstractListIterator<T>
            /*NodeIterator<HDirectory>*/ {

        protected DirectoryIterator(@NotNull Accessible<T> accessible, int length) {
            super(accessible, length);
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T hDirectories) {

        }

        @Override
        public void add(T hDirectories) {

        }
    }
}