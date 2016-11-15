package infastructure.path;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.empty.EmptyRelativeDirectoryPath;
import infastructure.path.exceptions.PathsNotMatchingException;
import infastructure.path.interfaces.ConstructorCommand;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public class RelativeDirectoryPath extends RelativePathImpl<RelativeDirectoryPath> implements RelativeDirectory {
    private DirectoryPathImpl _directoryPath;

    public RelativeDirectoryPath(DirectoryNode head, DirectoryNode tail, int nodeCount) {
        super(head, tail, nodeCount);
        _directoryPath = new DirectoryPathImpl();
    }

    // Getter

    @Override
    public DirectoryNode headNode(){
        return _head;
    }

    @Override
    public DirectoryNode tailNode(){
        return _tail;
    }

    @Override
    public RelativeDirectory copy() {
        PathNodeList nodeList = tailNode().copy();

        return new RelativeDirectoryPath(nodeList.getHead(), nodeList.getTail(), nodeList.length());
    }

    // Path Arithmetic


    @Override
    public RelativeDirectory concat(RelativeDirectory relDir) {
        ConstructorCommand<RelativeDirectoryPath> constructor = (head, tail, file, length)
                -> new RelativeDirectoryPath(head, tail, length);

        return _directoryPath.concat(this, relDir, constructor);
    }

    @Override
    public RelativeDirectory remove(RelativeDirectory removal) throws PathsNotMatchingException {
        ConstructorCommand<RelativeDirectoryPath> constructor = (head, tail, file, length) ->
                head == null
                        ? null
                        : new RelativeDirectoryPath(head, tail, length);

        return removeImpl(removal, constructor);
    }

    // ====================
    //  Relative Directory
    // ====================

    @Override
    public RelativeFile concat(RelativeFile relFile) {
        RelativeFilePath retVal = _directoryPath.concat(this, relFile, RelativeFilePath::new);
        retVal.setFile(new FileNode(null, relFile.fileNode().getNodeName()));

        return retVal;
    }

    // Path

    @Override
    RelativeDirectoryPath getEmptyPath() {
        return EmptyRelativeDirectoryPath.instance();
    }

    @Override
    public boolean equals(Path path) {
        boolean equals = false;

        if (path != null && !path.isEmpty() && path instanceof RelativeDirectory){
            equals = super.equals(path);
        }

        return equals;
    }
}
