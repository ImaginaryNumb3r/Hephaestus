package infastructure.path.empty;

import infastructure.filetype.interfaces.EmptyPath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.AbsoluteFilePath;
import infastructure.path.FileNode;
import infastructure.path.exceptions.EmptyPathException;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 27.07.2016
 */
public class EmptyAbsoluteFilePath extends AbsoluteFilePath implements EmptyPath {

    // ==================
    //   Constructors
    // ==================
//    public EmptyAbsoluteFilePath() {
//        super(null, null, null, 0);
//    }

    public EmptyAbsoluteFilePath(String fileName) {
        this(new FileNode(null, fileName));
    }

    public EmptyAbsoluteFilePath(FileNode file) {
        super(null, null, file, 0);
    }

    @Override
    public EmptyRelativeFilePath remove (AbsoluteDirectory absDir) throws PathsNotMatchingException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyRelativeFilePath concat(RelativeFile relFile) throws EmptyPathException {
        throw new EmptyPathException();
    }

    @Override
    public EmptyAbsoluteFilePath concat(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyAbsoluteFilePath remove(RelativeDirectory relDir) {
        throw new EmptyPathException();
    }

    @Override
    public EmptyAbsoluteDirectoryPath remove(RelativeFile relFile) throws PathsNotMatchingException {
        throw new EmptyPathException();
    } /*

    public EmptyPath remove(AbsoluteFile absFile) throws PathsNotMatchingException {
        return EmptyPath.super.remove(absFile);
    } */

    @Override
    public String toString() {
        return fileNode().getNodeName();
    }

    public EmptyAbsoluteFilePath copy() {
        return new EmptyAbsoluteFilePath(new FileNode(null, fileNode().getNodeName()));
    }/*

    public static EmptyAbsoluteFilePath instance() {
        return EmptyEmptyAbsoluteFilePathHolder.INSTANCE;
    }

    private static class EmptyEmptyAbsoluteFilePathHolder{
        private static final EmptyAbsoluteFilePath INSTANCE = new EmptyAbsoluteFilePath();
    }*/
}
