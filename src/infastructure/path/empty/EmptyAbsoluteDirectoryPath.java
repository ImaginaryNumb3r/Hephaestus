package infastructure.path.empty;

import infastructure.filetype.interfaces.EmptyPath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.AbsoluteDirectoryPath;
import infastructure.path.DirectoryNode;
import infastructure.path.RelativeDirectoryPath;
import infastructure.path.exceptions.EmptyPathException;
import infastructure.path.exceptions.NoParentException;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 27.07.2016
 */
public class EmptyAbsoluteDirectoryPath extends AbsoluteDirectoryPath implements EmptyPath{

    public EmptyAbsoluteDirectoryPath() {
        super(null, null, 0);
    }

    @Override
    public EmptyRelativeDirectoryPath remove (AbsoluteDirectory absDir) throws PathsNotMatchingException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyAbsoluteFilePath concat(RelativeFile relFile) throws EmptyPathException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyAbsoluteDirectoryPath concat(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyAbsoluteDirectoryPath remove(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyAbsoluteDirectoryPath remove(RelativeFile relFile) throws PathsNotMatchingException {
        throw new EmptyPathException();
    }

    @Override
    public String toString() {
        return "";
    }

    public EmptyAbsoluteDirectoryPath copy() {
        return EmptyAbsoluteDirectoryPath.instance();
    }


    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
     public RelativeDirectory getParent() {
        throw new NoParentException();
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        throw new NoParentException();
    }



    public static EmptyAbsoluteDirectoryPath instance() {
        return EmptyEmptyAbsoluteFilePathHolder.INSTANCE;
    }

    private static class EmptyEmptyAbsoluteFilePathHolder{
        private static final EmptyAbsoluteDirectoryPath INSTANCE = new EmptyAbsoluteDirectoryPath();
    }
}
