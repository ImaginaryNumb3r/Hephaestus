package infastructure.datastructure.tree;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.AbsoluteDirectoryPath;
import infastructure.path.DirectoryNode;
import infastructure.path.PathFactory;

/**
 * @author Patrick
 * @created 30.07.2016
 */
public class RelativeDirectoryTreeNode extends AbstractTreeNode {
    protected AbsoluteDirectory _absolutePath; // LazyImpl
    protected AbstractTreeNode _parentNode;
    final protected RelativeDirectory _name;

    // ================
    //   Constructors
    // ================

    /**
     * @param parent Previous directory in the file hierarchy
     * @param name MUST not be an absolute file
     */
    protected RelativeDirectoryTreeNode(AbstractTreeNode parent, RelativeDirectory name) {
        super();
        _name = name;
        _parentNode = parent;
    }

    // =====================
    //   Interface Methods
    // =====================

    @Override
    public boolean hasParent() {
        return _parentNode != null;
    }

    @Override
    public RelativeDirectory getParent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(String path) {
        return getAbsolutePath().equals(path);
    }

    @Override
    public String getName() {
        return _name.toString();
    }

    public AbstractTreeNode getParentNode() {
        return _parentNode;
    }

    void setParentNode(AbstractTreeNode parentNode) {
        _parentNode = parentNode;
    }

    // ===========
    //   Methods
    // ===========

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass") // Checked in Sub Class
    @Override
    public boolean equals(Object tree){
        return equals(tree, getClass());
    }

    @Override
    public String toString() {
        return _name.toString();
    }

    protected void initializePath() {
        if (_absolutePath == null){
            DirectoryNode tail = new DirectoryNode(null, getName());
            int length = 1;

            AbstractTreeNode curTreeNode = this;
            DirectoryNode curNode = tail;
            while (curTreeNode.hasParent()){

                curTreeNode = curTreeNode.getParentNode();
                curNode = NodeConverter.toNode(curNode, curTreeNode);
                ++length;
            }

            _absolutePath = null;
            _absolutePath = new AbsoluteDirectoryPath(curNode, tail, length);
        }
    }

    @Override
    public String getAbsolutePath() {
        initializePath();
        return _absolutePath.toString();
    }

    @Override
    public boolean isSubPath(RelativeDirectory relDir) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSubPath(AbsoluteDirectory absDir) {
        throw new UnsupportedOperationException();
    }
}
