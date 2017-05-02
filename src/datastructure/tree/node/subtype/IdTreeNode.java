package datastructure.tree.node.subtype;

import datastructure.tree.node.TreeNode;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <I> Identifier for the current node
 * @param <V> Value for the current node
 */
public interface IdTreeNode<I, V /*, N extends IdTreeNode<I, V, N> */>
        extends TreeNode<V>, IdTreeNodeReader<I, V> {

    void setIdentifier(I identifier);

}
