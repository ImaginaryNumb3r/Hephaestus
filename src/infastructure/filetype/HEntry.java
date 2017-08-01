package infastructure.filetype;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * @author Patrick
 * @created 28.05.2016
 * Masks java default file for better type safety and additional features
 */
public abstract class HEntry implements AbsolutePath{
    protected File _file;

    //<editor-fold desc="Constructors">
    // ======================
    //      Constructors
    // ======================

    protected HEntry(String file) {
        this(new File(file));
    }

    protected HEntry(File file) {
        _file = file;
    }
    //</editor-fold>

    //<editor-fold desc="Interface Methods">
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
        boolean equals = false;
        if (object instanceof HEntry){
            HEntry entry = (HEntry) object;
            equals = _file.equals(entry._file);
        }

        return equals;
    }
    //</editor-fold>

    // ======================
    //      Methods
    // ======================

    /**
     * Creates an object with the set of properties the entry has at this given moment.
     * Because the program has no control over the file system the properties can change at any given moment.
     * When working with the object, the file might have been deleted or changed already
     * Therefore, this is just a momentary value object.
     * @return  Returns FileTimeStamp of the current filesystem entry if the current HEntry really exists.
     *          Otherwise, returns null.
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent") // Get from CatchBlock
    public FileTimeStamp makeTimeStamp(){
        FileTimeStamp retVal = null;

        try {
            if (exists()){
                // Optional Longs should always be valid, since they are only empty if this entry does not exist
                long lastModified = lastModifiedMillies().getAsLong();
                long length = contentSize().getAsLong();

                if (exists()){
                    retVal = new FileTimeStampImpl(lastModified, length, this, LocalDateTime.now());
                }
            }
        } catch (NoSuchElementException ignore){ }

        return retVal;
    }

    public abstract AbsoluteDirectory getParentPath();

    /**
     * Returns the size of the content of this directory or file.
     * @return the size of the content of this directory or file.
     */
    public OptionalLong contentSize(){
        return exists()
                ? OptionalLong.of(_file.length())
                : OptionalLong.empty();
    }

    /**
     * Returns the last date of modification of this if it exists as a LocalDateTime
     * The optional only has a value if the file really exists
     * @return Optional of the last date of modification as LocalDateTime. Returns empty optional if file does not exist
     */
    public Optional<LocalDateTime> lastModifiedDateTime(){
        Optional<LocalDateTime> optional = Optional.empty();
        if (exists()){
            Date date = new Date(_file.lastModified());
            LocalDateTime time = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            optional = Optional.of(time);
        }

        return optional;
    }

    /**
     * Returns the last date of modification of this if it exists. Result is measured in milliseconds.
     * The optional only has a value if the file really exists
     * @return Optional of the last date of modification as milliseconds. Returns empty optional if file does not exist
     */
    public OptionalLong lastModifiedMillies(){
        return exists()
                ? OptionalLong.of(_file.lastModified())
                : OptionalLong.empty();
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
     * Performs a checkNulls whether the compared files represent the same data on the file system
     * @param file the file to be compared
     * @return true if paths are equal
     */
    public boolean equalPath(HEntry file) {
        return equalPath(file.getFile());
    }


    /**
     * Performs a checkNulls whether the compared files represent the same data on the file system
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

    @Override
    public int hashCode() {
        return _file.hashCode();
    }
}