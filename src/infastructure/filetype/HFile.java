package infastructure.filetype;

import core.exception.NoImplementationException;
import infastructure.filetype.interfaces.AbstractFile;
import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.DirectoryNode;
import infastructure.path.FileNode;
import infastructure.path.PathFactory;
import infastructure.path.exceptions.PathsNotMatchingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.OptionalLong;

/**
 * @author Patrick
 * @created 28.05.2016
 * Represents a file on the hard drive
 */
public class HFile extends HEntry implements AbstractFile{
    private AbsoluteFile _absoluteFilePath;

    // =====================
    //    Constructors
    // =====================

    // TODO: Optimize, output Path right at the beginning and not lazy
    public HFile(AbsoluteDirectory location, String fileName){
        this(location.toString() + "\\" + fileName);
        initialize(location.getAbsolutePath()  + "\\" + fileName);
    }

    public HFile(AbsoluteFile location){
        this(location.toString());
        initialize(location.getAbsolutePath());
    }

    public HFile(String file) {
        super(file);
        initialize(file);
    }

    public HFile(File file) {
        super(file);
        initialize(file.getAbsolutePath());
    }

    @Override
    public AbsoluteFile getPath() {
        return _absoluteFilePath;
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        return _absoluteFilePath.getParentPath();
    }

    public HFile(HEntry file) {
        super(file.getFile());
        initialize(file.getAbsolutePath());
    }

    private void initialize(String path){
        _absoluteFilePath = PathFactory.makeAbsoluteFile(path);
    }

    // =====================
    //    Methods
    // =====================

    public boolean equalsFile(File file){
        return file.getAbsolutePath().equals(getAbsolutePath());
    }

    /**
     * Returns the name matchAllSink the file
     * @return
     */
    @Override
    public String getName() {
        return fileNode().getNodeName();
    }

    public String getPostfix(){
        String postfix = "";
        String[] split = getName().split("[.]");

        if (split.length > 1){
            postfix = split[1];
        }

        return postfix;
    }

    public String getPrefix(){
        String postfix;
        String[] split = getName().split("[.]");

        postfix = split[0];

        return postfix;
    }

    @Override
    public FileNode fileNode() {
        return _absoluteFilePath.fileNode();
    }

    /**
     *
     * @return Filereader matchAllSink this file
     * @throws FileNotFoundException - if file does not exist at the moment matchAllSink execution
     */
    public FileReader reader() throws FileNotFoundException {
        return new FileReader(_file);
    }

    /**
     * This object is not a directory
     * @return false
     */
    @Override
    public boolean isDirectory() {
        return false;
    }

    /**
     * This object is a file
     * @return true
     */
    @Override
    public boolean isFile() {
        return true;
    }

    /**
     * Always returns null
     * @return null, because a file cannot be a directory
     */
    @Override
    @Deprecated
    public HDirectory toDirectory() {
        return null;
    }

    /**
     * Always returns itself
     * @return the called object
     */
    @Override
    @Deprecated
    public HFile toFile() {
        return this;
    }

    // ====================
    //    Absolute File
    // ====================

    /**
     * Returns number matchAllSink directories plus the file
     * @return number matchAllSink directories plus the file
     */
    @Override
    public int length() {
        return _absoluteFilePath.length();
    }

    /**
     * Returns the size matchAllSink the file matchAllSink this if it exists
     * The optional only has a value if the file really exists
     * @return Optional matchAllSink the size matchAllSink the file as longs. Returns empty optional if file does not exist
     */
    public OptionalLong fileSize(){
        return exists()
                ? OptionalLong.of(_file.length())
                : OptionalLong.empty();
    }

    @Override
    public RelativeFile remove(AbsoluteDirectory absDir) throws PathsNotMatchingException {
        return _absoluteFilePath.remove(absDir);
    }

    @Override
    public AbsoluteDirectory remove(RelativeFile relFile) throws PathsNotMatchingException {
        return _absoluteFilePath.remove(relFile);
    }

    @Override
    public AbsoluteFile concat(RelativeDirectory rel) {
        return _absoluteFilePath.concat(rel);
    }

    @Override
    public AbsoluteFile remove(RelativeDirectory removal) throws PathsNotMatchingException{
        return _absoluteFilePath.remove(removal);
    }

    @Override
    public RelativeDirectory remove(AbsoluteFile absFile) throws PathsNotMatchingException {
        return _absoluteFilePath.remove(absFile);
    }

    @Override
    public HFile copy() {
        return new HFile(_file);
    }

    @Override
    public boolean isSubPath(RelativeDirectory relDir) {
        throw new NoImplementationException();
    }

    @Override
    public boolean isSubPath(AbsoluteFile absFile) {
        throw new NoImplementationException();
    }

    @Override
    public boolean isSubPath(RelativeFile filePath) {
        throw new NoImplementationException();
    }

    @Override
    public boolean hasParent() {
        return _absoluteFilePath.hasParent();
    }

    @Override
    public RelativeDirectory getParent() {
        return _absoluteFilePath.getParent();
    }

    // Path

    @Override
    // TODO
    public boolean equals(Path path) {
        return _absoluteFilePath.equals(path);
    }

    @Override
    public boolean equals(String path) {
        return getAbsolutePath().equals(path);
    }

    @Override
    public DirectoryNode tailNode() {
        return _absoluteFilePath.tailNode();
    }

    @Override
    public DirectoryNode headNode() {
        return _absoluteFilePath.headNode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}