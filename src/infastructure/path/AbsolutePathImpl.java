package infastructure.path;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.filetype.interfaces.aubtypes.FilePath;
import infastructure.filetype.interfaces.aubtypes.RelativePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.NoParentException;
import infastructure.path.exceptions.PathsNotMatchingException;
import infastructure.path.interfaces.ConstructorCommand;

import java.io.File;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public abstract class AbsolutePathImpl<T> extends AbstractPath implements AbsolutePath {
    /** Used for delegation on the file system */
    protected File _file; // lazy

    // ====================
    //   Constructors
    // ====================

    abstract public boolean hasParent();

    abstract public RelativeDirectory getParent();

    abstract public AbsoluteDirectory getParentPath();

    /**
     * Copies this and all prior nodes in order to create a new path
     * @return new path based on the last node
     */
    public AbsolutePath copy() {
        return copy(tailNode());
    }

    /**
     * Copies this and all prior nodes to the parameter in order to create a new path
     * @param tail the last node in the sequence
     * @return new path based on the last node
     */
    protected AbsoluteDirectory copy(PathNode tail) {
        PathNodeList nodeList = tail.copy();
        return new AbsoluteDirectoryPath(nodeList.getHead(), nodeList.getTail(), nodeList.length());
    }

    public AbsolutePathImpl(DirectoryNode head, DirectoryNode tail, int nodeCount) {
        super(head, tail, nodeCount);
    }

    // ====================
    //   Absolute Path
    // ====================

    @Override
    public String getAbsolutePath() {
        return toPath();
    }

    @Override
    public boolean exists() {
        if (_file == null){
            _file = new File(getAbsolutePath());
        }

        return _file.exists();
    }

    public abstract AbsolutePath concat (RelativeDirectory rel);

    // ====================
    // Remove: Abs - AbsDir
    // ====================

    public abstract RelativePath remove(AbsoluteDirectory absDir) throws PathsNotMatchingException;

    /**
     * Base function of remove AbsoluteDirectory
     * @param absDir the absolute Directory that is being removed from this path
     * @param constructor constructor of return value as lambda parameter
     * @param <P> the specified return type, either a File or Directory
     * @return  Relative Path, being the difference between current path and parameter {@link AbsoluteDirectory}
     *          EmptyPath if EmptyPath is given as parameter
     * @throws  PathsNotMatchingException if the absolute directory is not a subset of current path
     */
    protected <P extends RelativePathImpl<P>> P removeImpl(AbsolutePath  absDir, ConstructorCommand<P> constructor) throws PathsNotMatchingException{
        if (absDir == null) throw new IllegalArgumentException("Path may not be null!");
        if (absDir.isEmpty()) throw new IllegalArgumentException("Path may not be empty!");

        P relDir;
        DirectoryNode newHead = getNewHead(tailNode(), absDir.tailNode());

        if (newHead != null) {
            // Cut off the start of the new relative path, copy what was cut off and merge it to absolute path again
            DirectoryNode temp = newHead.getPrev();
            newHead.setPrev(null);
            PathNodeList nodeList = tailNode().copy();

            newHead.setPrev(temp);

            relDir = constructor.execute(nodeList.getHead(), nodeList.getTail(), null, nodeList.length());
        } else {
            // null = empty path = still empty path when copies
            relDir = constructor.execute(null, null, null, 0);
        }

        return relDir;
    }

    // ====================
    //  Remove: Abs - Rel
    // ====================

    public abstract AbsolutePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    /**
     * Base function of remove RelativeDirectory
     * @param removal the relative directory {@link RelativeDirectory}that is being removed from this path
     * @param constructor constructor of return value as lambda parameter.
     *                    Needs to return an empty path if arguments are null
     * @param <P> the specified return type, either a File or Directory
     * @return  Absolute path {@link AbsolutePath}, being the difference between current path and parameter {@link RelativeDirectory}.
     *          EmptyPath if EmptyPath is given as parameter
     * @throws PathsNotMatchingException
     */
    protected <P extends AbsolutePath> P removeImpl(RelativeDirectory removal, ConstructorCommand<P> constructor) throws PathsNotMatchingException{
        if (removal == null) throw new IllegalArgumentException("Path may not be null!");

        P retVal;

        if (removal.isEmpty()){
            // Return copy

            PathNodeList nodeList = tailNode().copy();
            retVal = constructor.execute(nodeList.getHead(), nodeList.getTail(), nodeList.getFile(), nodeList.length());

        } else {
            DirectoryNode curBase = getNewTail(tailNode().iterator(), removal.tailNode().iterator());

            // is not empty path
            if (curBase != null){
                PathNodeList newNodes = curBase.copy();
                // If base class is a file, simply add it to the end
                FileNode file = this instanceof FilePath ? ((FilePath) this).fileNode() : newNodes.getFile();

                retVal = constructor.execute(newNodes.getHead(), newNodes.getTail(), file, newNodes.length());
                // retVal = new AbsoluteDirectoryPath(newNodes.getHead(), newNodes.getTail(), newNodes.length());

            // is empty path
            } else {
                // Make complete copy of list
                PathNodeList newNodes = tailNode().copy();
                retVal = constructor.execute(null, null, null, 0);
                throw new RuntimeException("You should not be here! - if a copy was to be made, it should have been done in the first if@ removal.isEmpty() ");
            }
        }

        return retVal;
    }
}
