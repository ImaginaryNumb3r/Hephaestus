package infastructure.filetype;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Patrick
 * @created 28.05.2016
 * masks java default file for better type safety and additional features
 */
public abstract class HEntry implements AbsolutePath{
    protected File _file;

    // ======================
    //      Constructors
    // ======================

    protected HEntry(String file) {
        this(new File(file));
    }

    protected HEntry(File file) {
        _file = file;
    }

    // ======================
    //   Interface Methods
    // ======================

    @Override
    public String getAbsolutePath() {
        return _file.getAbsolutePath();
    }

    abstract public AbsolutePath getPath();

    @Override
    public boolean equals(Object object){
        boolean equals;

        if (object == this) {
            equals = true;
        } else if (object instanceof AbsolutePath){
            AbsolutePath file = (AbsolutePath) object;
            equals = file.equals(this);
        } else {
            equals = false;
        }

        return equals;
    }

    // ======================
    //      Methods
    // ======================

    public abstract AbsoluteDirectory getParentPath();

    public LocalDateTime lastModifiedDateTime(){
        Date date = new Date(_file.lastModified());

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public long lastModifiedMillies(){
        return _file.lastModified();
    }


    public boolean delete(){
        return _file.delete();
    }

    public void deleteOnExit(){
        _file.deleteOnExit();
    }

    public boolean create() throws IOException{
        return _file.createNewFile();
    }


    /**
     * Returns the basic java file backing this HEntry
     * @return java IO.File
     */
    public File getFile(){
        return _file;
    }

    /**
     * Returns name of the last entry
     * @return name of the last entry
     */
    public abstract String getName();

    /**
     * The object represents a directory in the file system
     * @return boolean if object is directory
     */
    public boolean isDirectory() {
        return _file.isDirectory();
    }

    /**
     * Turns this entry to a directory if possible
     * @return null if not a directory, CnCDirectory if it is a directory
     */
    public HDirectory toDirectory(){
        HDirectory directory;

        if (isDirectory()){

            if (this instanceof HDirectory){
                directory = (HDirectory) this;
            } else {
                directory = new HDirectory(this);
            }

        } else {
            directory = null;
        }

        return directory;
    }

    /**
     * Turns this entry to a File if possible
     * @return null if not a file, CnCFile if it is a file
     */
    public HFile toFile(){
        HFile file;

        if (isFile()){
            if (this instanceof HFile){
                file = (HFile) this;
            } else {
                file = new HFile(this);
            }

        } else {
            file = null;
        }

        return file;
    }

    /**
     * The object represents a file in the file system
     * @return boolean if object is file
     */
    public boolean isFile() {
        return _file.isFile();
    }


    /**
     * Performs a checkNull whether the compared files represent the same data on the file system
     * @param file the file to be compared
     * @return true if paths are equal
     */
    public boolean equalPath(HEntry file) {
        return equalPath(file.getFile());
    }


    /**
     * Performs a checkNull whether the compared files represent the same data on the file system
     * @param file the file to be compared
     * @return true if paths are equal
     */
    public boolean equalPath(File file){
        boolean equals;

        // if one file exists and the other doesn't they cannot be equal
        if (_file.exists() != file.exists()){
            equals = false;
        } else {
            equals = _file.getAbsoluteFile().equals(file.getAbsoluteFile());
        }

        return equals;
    }

    /**
     * @return true if file represents an existing entry in the file system
     */
    @Override
    public boolean exists() {
        return _file.exists();
    }

    @Override
    public String toString(){
        return _file.toString();
    }

    /**
     * Returns the parent directory. In other words, it returns the entry that is located one level below the current entry on the filesystem.
     * @return  The directory when object is a file. Or the mother directory if object is a directory.
     *          Returns null if there is no parent directory
     */
    public HDirectory parentDirectory() {
        String parent = _file.getParent();

        return _file != null
                ? null
                : new HDirectory(parent);
    }
}