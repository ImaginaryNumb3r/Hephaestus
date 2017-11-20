package infastructure.path.empty;

import core.exception.NoImplementationException;
import infastructure.filetype.interfaces.EmptyPath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.FileNode;
import infastructure.path.RelativeFilePath;
import infastructure.path.exceptions.EmptyPathException;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 09.07.2016
 */

// TODO: Let it be just like a normal RelativeFilePAth, just without tail to hdead nodes
public class EmptyRelativeFilePath extends RelativeFilePath implements EmptyPath {

    // ==================
    //   Constructors
    // ==================
/*    public EmptyRelativeFilePath() {
        super(null, null, null, 0);
    }*/

    public EmptyRelativeFilePath(String fileName) {
        this(new FileNode(null, fileName));
    }

    public EmptyRelativeFilePath(FileNode file) {
        super(null, null, file, 0);
    }

    // ==================
    //   Path Methods
    // ==================

    @Override
    public EmptyPath remove (AbsoluteDirectory absDir) throws PathsNotMatchingException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyPath concat(RelativeFile relFile) throws EmptyPathException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyRelativeFilePath concat(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyRelativeFilePath remove(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyRelativeDirectoryPath remove(RelativeFile relFile) throws PathsNotMatchingException {
        if (relFile == null) throw new IllegalArgumentException("Path may not be null!");

        if (!relFile.fileNode().getNodeName().equals(fileNode().getNodeName())){
            throw new PathsNotMatchingException();
        }

        return EmptyRelativeDirectoryPath.instance();
    }

    @Override
    public EmptyRelativeFilePath copy() {
        throw new NoImplementationException();
    }

    @Override
    public String getName() {
        return fileNode().getNodeName();
    }

    @Override
    public String toString() {
        return getName();
    }
/*

    public static EmptyRelativeFilePath instance() {
        return EmptyRelativeFilePathHolder.INSTANCE;
    }

    private static class EmptyRelativeFilePathHolder{
        private static final EmptyRelativeFilePath INSTANCE = new EmptyRelativeFilePath();
    }*/
}
