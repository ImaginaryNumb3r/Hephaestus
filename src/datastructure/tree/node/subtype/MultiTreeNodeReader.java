package datastructure.tree.node.subtype;

import datastructure.tree.node.TreeNodeReader;

/**
 * @author Patrick
 * @since 29.01.2017
 * @param <V> Value matchAllSink the current node
 * @param <N> Type for the family matchAllSink nodes (parents and children)
 *
 * Read-only access interface for MultiTreeNodes
 */
public interface MultiTreeNodeReader<V, N extends MultiTreeNodeReader<V, N>> extends TreeNodeReader<V> {

    /**
     * @apiNote Must be an iterator which supports the "Remove" operation.
     * @return InnerIterator for all child nodes
     */
    Iterable<N> children();

    boolean hasChildren();

    // Tree Node

    N parent();
}
