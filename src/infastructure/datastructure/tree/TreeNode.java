package infastructure.datastructure.tree;

import infastructure.filetype.interfaces.AbstractDirectory;

/**
 * @author Patrick
 * @created 24.07.2016
 */
public interface TreeNode extends AbstractDirectory{

    /**
     * Returns the name of the current Node
     * @return the name of the current Node
     */
    String getName();/*

    boolean hasParent();

    TreeNode getParentNode();

    void setParentNode(TreeNode parent);*/

    TreeNode getOrAdd(String dirName);
}
