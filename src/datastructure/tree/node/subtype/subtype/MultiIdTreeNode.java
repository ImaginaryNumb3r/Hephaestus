package datastructure.tree.node.subtype.subtype;

import datastructure.tree.node.subtype.IdTreeNode;
import datastructure.tree.node.subtype.MultiTreeNode;

import java.util.List;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <I> Identifier of the current node
 * @param <V> Value of the current node
 * @param <N> Type for the family of nodes (parents and children)
 */
public interface MultiIdTreeNode<I, V, N extends MultiIdTreeNode<I, V, N>>
        extends MultiTreeNode<V, N>, IdTreeNode<I, V>, MultiIdTreeNodeReader<I, V, N>{

    // Multi Tree Node

    boolean hasParent();

    N parent();

    List<N> children();

    void addChild(N child);

    N removeChild(I identifier);
}
