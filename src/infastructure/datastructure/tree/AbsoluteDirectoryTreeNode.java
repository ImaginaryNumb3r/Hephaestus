package infastructure.datastructure.tree;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;

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
        return null;
    }

    /**
     * Root cannot have a parent, returns false
     * @return false
     */
    @Override
    final public boolean hasParent() {
        return false;
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
    public String getAbsolutePath() {
        return _directory.toString();
    }
}
