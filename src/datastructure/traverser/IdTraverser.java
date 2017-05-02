package datastructure.traverser;

import datastructure.traverser.exceptions.NoValueException;
import datastructure.tree.node.TreeNodeReader;
import datastructure.tree.node.subtype.IdTreeNodeReader;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <I> Identifier of the node
 * @param <V> value of the node
 *
 * A Traverser for nodes of a Tree where each node has its own identifier on top of a value
 */
public interface IdTraverser<I, V> extends Traverser<V>  {

    /**
     * Returns the value of the next child node.
     * @return the value of the next child node
     */
    IdTreeNodeReader<I, V> nextChild();


    /**
     * Returns the value of the current node. This can always be accessed with
     * @return the value of the current node
     * @throws NoValueException if no value for the current node exists
     */
    IdTreeNodeReader<I, V> value();
}
