package infastructure.path;

import core.exception.NoImplementationException;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.empty.EmptyRelativeFilePath;
import infastructure.path.exceptions.NoParentException;
import infastructure.path.exceptions.PathsNotMatchingException;
import infastructure.path.interfaces.PathSupplier;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public class AbsoluteFilePath extends AbsolutePathImpl<AbsoluteFilePath> implements AbsoluteFile {
    private FileNode _fileNode;
    private FilePathImpl _filePath;

    public AbsoluteFilePath(DirectoryNode head, DirectoryNode tail, FileNode fileNode, int nodeCount) {
        super(head, tail, nodeCount);
        _fileNode = fileNode;
        _filePath = new FilePathImpl();
    }


    // ====================
    //   Absolute File
    // ====================

    /**
     * Removes the file to an absolute file and returns the difference as relative directory {@link AbsoluteFile}
     * @param absFile the absolute Directory that is being removed fromEntries this file
     * @return  Relative Directory, being the difference between current file and parameter {@link AbsoluteFile}
     *          EmptyPath is EmptyPath is given as parameter
     * @throws  PathsNotMatchingException if the absolute directory is not a subset of current file
     *          IllegalArgumentException if empty file was given as argument
     */
    public RelativeDirectory remove(AbsoluteFile absFile) throws PathsNotMatchingException {
        if (!fileNode().equals(absFile.fileNode())) throw new PathsNotMatchingException();

        PathSupplier<RelativeDirectoryPath> constructor = (head, tail, file, length) ->
                head == null
                    ? null
                    : new RelativeDirectoryPath(head, tail, length);

        return removeImpl(absFile, constructor);
    }

    @Override
    public boolean hasParent() {
        return tailNode() != null;
    }

    @Override
    public RelativeDirectory getParent() {
        if (!hasParent()) throw new NoParentException();
        DirectoryNode parentNode = new DirectoryNode(null, tailNode().getNodeName());

        return new RelativeDirectoryPath(parentNode, parentNode, 1);
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        if (!hasParent()) throw new NoParentException();

        return copy(tailNode());
    }

    @Override
    public AbsoluteFile copy() {
        PathNodeList nodeList = tailNode().copy();

        return new AbsoluteFilePath(nodeList.getHead(), nodeList.getTail(), nodeList.getFile(), nodeList.length());
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
    public AbsoluteDirectory remove(RelativeFile relFile) throws PathsNotMatchingException{
        PathSupplier<AbsoluteDirectory> constructor = (head, tail, file, length) ->
                head == null
                    // ? EmptyAbsoluteDirectoryPath.instance()
                    ? null
                    : new AbsoluteDirectoryPath(head, tail, length);

        return _filePath.remove(this, relFile, constructor);
    }

    @Override
    public AbsoluteFile concat(RelativeDirectory relDir) {
        return _filePath.concat(this, relDir, AbsoluteFilePath::new);
    }

    @Override
    public AbsoluteFile remove(RelativeDirectory removal) throws PathsNotMatchingException{
        PathSupplier<AbsoluteFile> constructor = (head, tail, file, length) ->
                head == null
                        // ? EmptyAbsoluteDirectoryPath.instance()
                        ? null
                        : new AbsoluteFilePath(head, tail, file, length);

        return removeImpl(removal, constructor);
    }

    @Override
    public boolean equals(String path) {
        return getAbsolutePath().equals(path);
    }

    @Override
    public RelativeFile remove(AbsoluteDirectory absDir) throws PathsNotMatchingException {
        PathSupplier<RelativeFilePath> constructor = (head, tail, file, length) ->
            head == null
                ? new EmptyRelativeFilePath(new FileNode(null, file.getNodeName()))
                : new RelativeFilePath(head, tail, file, length);

        RelativeFilePath relativeFile = removeImpl(absDir, constructor);
        relativeFile.setFile(new FileNode(relativeFile.tailNode(), _fileNode._nodeName));

        return relativeFile;
        // return _filePath.remove(this, absDir, RelativeFilePath::new);
    }

    @Override
    public String getName() {
        return _fileNode.getNodeName();
    }

    @Override
    public String getAbsolutePath() {
        return super.getAbsolutePath() + "\\" + _fileNode.getNodeName();
    }

    // ====================
    //   File Path
    // ====================

    @Override
    public String getPostfix() {
        return fileNode().getPostfix();
    }

    @Override
    public String getPrefix() {
        return fileNode().getPrefix();
    }

    @Override
    public FileNode fileNode() {
        return _fileNode;
    }

    // FilePathImpl

    @Override
    public String toString() {
        return super.toString() + "\\" + _fileNode.getNodeName();
    }

    @Override
    public int length() {
        return super.length() + 1;
    }
}