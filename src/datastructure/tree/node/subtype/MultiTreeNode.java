package datastructure.tree.node.subtype;

import datastructure.tree.node.TreeNode;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <V> Value of the current node
 * @param <N> Type for the family of nodes (parents and children)
 */
public interface MultiTreeNode<V, N extends MultiTreeNode<V, N>>
        extends TreeNode<V>, MultiTreeNodeReader<V, N> {

}
