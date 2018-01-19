package datastructure.tree.node.subtype;

import datastructure.tree.node.TreeNodeReader;

/**
 * @author Patrick
 * @since 29.01.2017
 * @param <V> Value matchAllSink the current node
 * @param <I> Identifier for the current node
 *
 * Read-only access interface for IdTreeNodes
 * */
public interface IdTreeNodeReader<I, V> extends TreeNodeReader<V> {

    I getIdentifier();
}
