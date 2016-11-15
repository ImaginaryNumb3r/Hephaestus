package infastructure.path;


import infastructure.filetype.interfaces.aubtypes.RelativePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.PathsNotMatchingException;
import infastructure.path.interfaces.ConstructorCommand;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public abstract class RelativePathImpl<R> extends AbstractPath implements RelativePath {

    public RelativePathImpl(DirectoryNode head, DirectoryNode tail, int nodeCount) {
        super(head, tail, nodeCount);
    }

    // ====================
    //   Absolute Path
    // ====================

    // AbsolutePath

    @Override
    public abstract RelativePath concat (RelativeDirectory rel);

    @Override
    public abstract RelativePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    protected final <T extends RelativePathImpl<R>> T removeImpl(RelativeDirectory removal, ConstructorCommand<T> constructor) throws PathsNotMatchingException {
        if (removal == null) throw new IllegalArgumentException("Path may not be null");
        if (constructor == null) throw new IllegalArgumentException("ConstructorCommand may not be null");

        PathNodeList nodeList = null;

        if (!removal.isEmpty()){
            DirectoryNode newPath = getNewTail(tailNode().iterator(), removal.tailNode().iterator());

            if (newPath != null ){
                nodeList = newPath.copy();
            }
        }

        // If nodeList could not be initialized, make copy of current path
        if (nodeList == null){
            nodeList = tailNode().copy();
        }

        return constructor.execute(nodeList.getHead(), nodeList.getTail(), null, nodeList.length());

    }

    // ====================
    //   Abstract Path
    // ====================

    abstract R getEmptyPath();

    @Override
    public String getRelativePath() {
        return toPath();
    }
}
