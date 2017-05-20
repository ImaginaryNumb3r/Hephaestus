package infastructure.filetype;

import infastructure.datastructure.traverser.NodeIterator;
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
import java.util.ArrayList;
import java.util.List;

/**
 * An improved implementation of the default Java file, representing a directory on the file system
 * @author Patrick
 * @since 28.05.2016
 */
public class HDirectory extends HEntry implements Iterable<HDirectory>, AbstractDirectory {
    private final AbsoluteDirectory _absoluteDirectory;

    // =======================
    //     Constructors
    // =======================

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


    // =======================
    //     Methods
    // =======================

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
     *
     * @param typeFilter First filter to determine the type
     * @param generalFilter Second filter for other sorts of filtering
     * @param constructor The Constructor to create the wanted type of value
     * @param <T> the desired type of value
     * @return List of creates types
     */
    public <T> List<T> getEntries(FileFilter typeFilter, FileFilter generalFilter, BiSupplier<T, File> constructor){
        FileFilter filter = pathname -> typeFilter.accept(pathname) && generalFilter.accept(pathname);

        File[] files = _file.listFiles(filter);
        ArrayList<T> hFiles = new ArrayList<>(files.length);

        for (int i = 0; i != files.length; ++i){
            hFiles.add(i, constructor.make(files[i]));
        }

        return hFiles;
    }


    /**
     * Returns list of directories that match the filter
     * @param filter to sort unwanted entries
     * @return List of directories that match the filter
     */
    public List<HDirectory> getDirectories(FileFilter filter) {
        FileFilter baseFilter = File::isDirectory;

        return getEntries(baseFilter, filter, HDirectory::new);
    }
    /**
     * returns array of existing sub-directories in this directory
     * @return array of existing sub-directories in this directory
     */
    public List<HDirectory> getDirectories() {
        return getDirectories(arg -> true);
    }

    /**
     * Returns list of files that match the filter
     * @param filter to sort unwanted entries
     * @return List of files that match the filter
     */
    public List<HFile> getFiles(FileFilter filter){
        FileFilter baseFilter = File::isFile;

        return getEntries(baseFilter, filter,  HFile::new);
    }

    /**
     * returns array of existing files in this directory
     * @return array of existing files in this directory
     */
    public List<HFile> getFiles(){
        return getFiles(arg -> true);
    }

    /**
     * Directory InnerIterator
     * @return InnerIterator of the directories
     */

    public NodeIterator<HDirectory> iterator(){
        return new CnCDirectoryIterator<>();
    }


    // ==================
    //   Path Interface
    // ==================

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


    // =================
    //   Absolute Path
    // =================

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

    // Path

    @Override
    public boolean equals(Path path) {
        return _absoluteDirectory.equals(path);
    }

    /******************
     * Node InnerIterator
     /*****************/
    protected class CnCDirectoryIterator<T extends HDirectory> extends NodeIterator<HDirectory> {

        public CnCDirectoryIterator(List<HDirectory> init) {
            super(init);
        }

        /**
         * Default Constructor initializing with child directories
         */
        public CnCDirectoryIterator() {
            this(getDirectories());
        }
    }
}