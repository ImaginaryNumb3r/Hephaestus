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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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

    // TODO: Optimize, get Path right at the beginning and not lazy
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
     * @return Filereader of this file
     * @throws FileNotFoundException - if file does not exist at the moment of execution
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
    public HDirectory toDirectory() {
        return null;
    }

    /**
     * Always returns itself
     * @return the called object
     */
    @Override
    public HFile toFile() {
        return this;
    }

    // ====================
    //    Absolute File
    // ====================

    /**
     * Returns number of directories plus the file
     * @return number of directories plus the file
     */
    @Override
    public int length() {
        return _absoluteFilePath.length();
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
    // TODO: Could get problems
    public AbsoluteFile copy() {
        return new HFile((AbsoluteFile) this);
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
        throw new NotImplementedException();
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