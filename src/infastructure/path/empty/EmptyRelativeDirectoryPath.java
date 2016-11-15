package infastructure.path.empty;

import infastructure.filetype.interfaces.EmptyPath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.RelativeDirectoryPath;
import infastructure.path.exceptions.EmptyPathException;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 09.07.2016
 */
public class EmptyRelativeDirectoryPath extends RelativeDirectoryPath implements EmptyPath {

    private EmptyRelativeDirectoryPath() {
        super(null, null, 0);
    }

    @Override
    public EmptyPath remove (AbsoluteDirectory absDir) throws PathsNotMatchingException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyRelativeFilePath concat(RelativeFile relFile) throws EmptyPathException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyRelativeDirectoryPath concat(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyRelativeDirectoryPath remove(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyPath remove(RelativeFile relFile) throws PathsNotMatchingException {
        throw new EmptyPathException();
    } /*

    public EmptyPath remove(AbsoluteFile absFile) throws PathsNotMatchingException {
        return EmptyPath.super.remove(absFile);
    } */

    @Override
    public String toString() {
        return "";
    }

    public EmptyRelativeDirectoryPath copy() {
        return EmptyRelativeDirectoryPath.instance();
    }

    public static EmptyRelativeDirectoryPath instance() {
        return EmptyRelativeDirectoryPathHolder.INSTANCE;
    }

    private static class EmptyRelativeDirectoryPathHolder{
        private static final EmptyRelativeDirectoryPath INSTANCE = new EmptyRelativeDirectoryPath();
    }
}
