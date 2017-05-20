package infastructure.datastructure.tree;

import core.exception.NoImplementationException;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;

/**
 * @author Patrick
 * @created 30.07.2016
 */
public class AbsoluteDirectoryTreeNode extends AbstractTreeNode {
    protected AbsoluteDirectory _directory;

    public AbsoluteDirectoryTreeNode(AbsoluteDirectory directory){
        super();
        _directory = directory;
    }

    /**
     * Root cannot have a parent, returns null
     * @return null
     */
    @Override
    final AbstractTreeNode getParentNode() {
        throw new UnsupportedOperationException();
    }

    /**
     * Root cannot have a parent, returns false
     * @return false
     */
    @Override
    final public boolean hasParent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public RelativeDirectory getParent() {
        return null;
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        return null;
    }


    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass") // Checked in subclass
    @Override
    public boolean equals(Object tree) {
        return equals(tree, getClass());
    }

    @Override
    public String toString() {
        return getAbsolutePath();
    }

    @Override
    public String getName() {
        return getAbsolutePath();
    }

    @Override
    public boolean isSubPath(RelativeDirectory relDir) {
        throw new NoImplementationException();
    }

    @Override
    public String getAbsolutePath() {
        return _directory.toString();
    }

    @Override
    public boolean isSubPath(AbsoluteDirectory absDir) {
        throw new NoImplementationException();
    }
}
