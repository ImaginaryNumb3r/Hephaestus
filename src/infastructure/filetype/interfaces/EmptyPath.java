package infastructure.filetype.interfaces;

import infastructure.filetype.interfaces.aubtypes.DirectoryPath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.DirectoryNode;
import infastructure.path.FileNode;
import infastructure.path.PathFactory;
import infastructure.path.exceptions.EmptyPathException;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 08.07.2016
 */
// TODO: Consider just making it a relativePath or relFile + relDir
public interface EmptyPath extends Path { // extends AbsoluteDirectory, AbsoluteFile, RelativeDirectory, RelativeFile {

    // Path Arithmetic

    EmptyPath concat(RelativeFile relFile);

    @Override
    EmptyPath concat(RelativeDirectory relDir);

    default EmptyPath remove(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    default EmptyPath remove(AbsoluteDirectory absDir) throws PathsNotMatchingException {
        throw new EmptyPathException();
    }

    default EmptyPath remove(RelativeFile relFile) throws PathsNotMatchingException {
        throw new EmptyPathException();
    }

    // Other

    @Override
    default int length(){
        return 0;
    }

    @Override
    default boolean equals(Path path) {
        return path instanceof EmptyPath;
    }

    @Override
    default DirectoryNode headNode(){
        return null;
    }

    @Override
    default DirectoryNode tailNode(){
        return null;
    }

    @Override
    default String getName(){
        return "";
    }

    default String getAbsolutePath(){
        return "";
    }

    default boolean exists(){
        return false;
    }

    default String getPostfix(){
        throw new EmptyPathException();
    }

    default String getPrefix(){
        throw new EmptyPathException();
    }

    default FileNode fileNode(){
        throw new EmptyPathException();
    }

    default String getRelativePath(){
        throw new EmptyPathException();
    }

    @Override
    default boolean isEmpty(){
        return true;
    }

    @Override
    String toString(); // Cannot make default directly...

    @Override
    default EmptyPath copy(){
        return this;
    }
}
