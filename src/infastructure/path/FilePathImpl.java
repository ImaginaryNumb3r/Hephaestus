package infastructure.path;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.FilePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.exceptions.EmptyPathException;
import infastructure.path.exceptions.PathsNotMatchingException;
import infastructure.path.interfaces.PathSupplier;

import java.util.Iterator;

/**
 * @author Patrick
 * @created 08.07.2016
 */
class FilePathImpl  {


    // =======================
    // Concat: File + RelDir
    // =======================

    public final RelativeFilePath concat(RelativeFilePath base, RelativeDirectory relDir, PathSupplier<RelativeFilePath> constructor) {
        return concat(toMask(base), relDir, constructor);
    }

    public final AbsoluteFilePath concat(AbsoluteFilePath base, RelativeDirectory relDir, PathSupplier<AbsoluteFilePath> constructor) {
        return concat(toMask(base), relDir, constructor);
    }

    // final - cannot be moved down further
    protected final <T extends FilePath> T concat(FilePathMask base, RelativeDirectory relDir, PathSupplier<T> constructor) {
        if (relDir == null) throw new IllegalArgumentException("Path may not be null!");

        PathNodeList nodeList = base.concatNodes(relDir);
        FileNode newFile = new FileNode(nodeList.getTail(), base.fileNode().getNodeName());

        return constructor.get(nodeList.getHead(), nodeList.getTail(), newFile, nodeList.length());
    }

    // =======================
    // Concat: File - RelFile
    // =======================

    public AbsoluteDirectory remove(AbsoluteFilePath base, RelativeFile removal, PathSupplier<AbsoluteDirectory> constructor) throws PathsNotMatchingException {
        FilePathMask mask = toMask(base);
        return removeImpl(mask, removal, constructor);
    }

    public RelativeDirectory remove(RelativeFilePath base, RelativeFile removal, PathSupplier<RelativeDirectory> constructor) throws PathsNotMatchingException {
        FilePathMask mask = toMask(base);

        return removeImpl(mask, removal, constructor);
    }

    /**
     * Removes the last part of the base matching the removal string
     * @param base full base string
     * @param removal the appendix to be removed fromPair the base string
     * @param constructor the instance creating the return value
     * @param <T> Either a relative file file or an absolute file file
     * @return New shortened file, consisting of the base file without the part fromPair the removal file
     * @throws PathsNotMatchingException
     * @throws EmptyPathException
     */
    private <T extends Path> T removeImpl(FilePathMask base, RelativeFile removal, PathSupplier<T> constructor) throws PathsNotMatchingException {
        if (removal == null) throw new IllegalArgumentException("Path may not be null!");
        if (removal.isEmpty()) throw new EmptyPathException();

        if (!removal.fileNode().getNodeName().equals(base.fileNode().getNodeName())){
            throw new PathsNotMatchingException();
        }

        PathNodeList nodeList;
        if (!removal.isEmpty()){
            DirectoryNode newPath= base.getNewTail(base.tailNode().iterator(), removal.tailNode().iterator());

            nodeList = newPath.copy();
            // retVal = new RelativeDirectoryPath(nodeList.getHead(), nodeList.getTail(), nodeList.length());
        } else {
            // Just copy whole list
            nodeList = base.tailNode().copy();
        }

        return constructor.get(nodeList.getHead(), nodeList.getTail(), null, nodeList.length());
    }

    // ===================
    //    Masking Methods
    // ===================

    private FilePathMask toMask(RelativeFilePath relFile){
        return new FilePathMask() {
            public PathNodeList concatNodes(RelativeDirectory relDir) {
                return relFile.concatNodes(relDir);
            }
            public FileNode fileNode() {
                return relFile.fileNode();
            }
            public DirectoryNode tailNode() {
                return relFile.tailNode();
            }
            public RelativeFile copy() {
                return relFile.copy();
            }
            public DirectoryNode getNewTail(Iterator<DirectoryNode> iterator, Iterator<DirectoryNode>  iterator1) throws PathsNotMatchingException {
                return relFile.getNewTail(iterator, iterator1);
            }

        };
    }

    private FilePathMask toMask(AbsoluteFilePath relFile){
        return new FilePathMask() {
            public PathNodeList concatNodes(RelativeDirectory relDir) {
                return relFile.concatNodes(relDir);
            }
            public FileNode fileNode() {
                return relFile.fileNode();
            }
            public DirectoryNode tailNode() {
                return relFile.tailNode();
            }
            public AbsoluteFile copy() {
                return relFile.copy();
            }
            public DirectoryNode getNewTail(Iterator<DirectoryNode> iterator, Iterator<DirectoryNode> iterator1) throws PathsNotMatchingException {
                return relFile.getNewTail(iterator, iterator1);
            }
        };
    }

    // Private Interface

    private interface FilePathMask {

        PathNodeList concatNodes(RelativeDirectory relDir);

        DirectoryNode tailNode();

        FileNode fileNode();

        DirectoryNode getNewTail(Iterator<DirectoryNode> iterator, Iterator<DirectoryNode> iterator1) throws PathsNotMatchingException;

        Path copy();
    }
}
