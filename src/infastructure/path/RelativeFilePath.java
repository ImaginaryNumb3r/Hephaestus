package infastructure.path;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.empty.EmptyRelativeDirectoryPath;
import infastructure.path.empty.EmptyRelativeFilePath;
import infastructure.path.exceptions.PathsNotMatchingException;
import infastructure.path.interfaces.ConstructorCommand;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public class RelativeFilePath extends RelativePathImpl<RelativeFilePath> implements RelativeFile {
    private FileNode _fileNode;
    private FilePathImpl _filePath;


    public RelativeFilePath(DirectoryNode head, DirectoryNode tail, FileNode file, int nodeCount) {
        super(head, tail, nodeCount);
        _fileNode = file;
        _filePath = new FilePathImpl();
    }

    // ==================
    //   Relative Path
    // ==================

    @Override
    public RelativeFile concat(RelativeDirectory relDir) {
        return relDir.concat(this);
        // return _filePath.concat(this, relDir, RelativeFilePath::new);
    }

    @Override
    public RelativeFile remove(RelativeDirectory removal) throws PathsNotMatchingException {
        ConstructorCommand<RelativeFilePath> constructor = (head, tail, file, length) ->
                head == null
                    ? new EmptyRelativeFilePath(new FileNode(null, file.getNodeName()))
                    : new RelativeFilePath(head, tail, file, length);

        RelativeFilePath relativeFilePath = removeImpl(removal, constructor);
        relativeFilePath.setFile(new FileNode(null, _fileNode.getNodeName()));

        return relativeFilePath;
    }

    @Override
    public RelativeFile copy() {
        PathNodeList nodeList = tailNode().copy();

        return new RelativeFilePath(nodeList.getHead(), nodeList.getTail(), nodeList.getFile(), nodeList.length());
    }

    // ====================
    //   Relative File
    // ====================


    @Override
    public String getName() {
        return _fileNode.getNodeName();
    }

    @Override
    public RelativeDirectory remove(RelativeFile removal) throws PathsNotMatchingException{
        ConstructorCommand<RelativeDirectory> constructor = (head, tail, file, length) ->
                head == null
                    ? EmptyRelativeDirectoryPath.instance()
                    : new RelativeDirectoryPath(head, tail, length);

        return _filePath.remove(this, removal, constructor);
    }

    @Override
    public String getPostfix() {
        return _fileNode.getPostfix();
    }

    @Override
    public String getPrefix() {
        return _fileNode.getPrefix();
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

    @Override
    RelativeFilePath getEmptyPath() {
        return new EmptyRelativeFilePath(new FileNode(null, fileNode().getNodeName()));
    }

    @Override
    public boolean equals(Path path) {
        boolean equals = false;

        if (path != null && !path.isEmpty() && path instanceof RelativeFile){
            equals = super.equals(path);
        }

        return equals;
    }

    public void setFile(FileNode file) {
        _fileNode = file;
    }
}