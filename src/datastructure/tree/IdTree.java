package datastructure.tree;

import com.sun.istack.internal.NotNull;
import datastructure.traverser.IdTraverser;
import datastructure.tree.node.subtype.IdTreeNodeReader;

import java.util.Deque;
import java.util.List;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <I> Identifier of each node
 * @param <V> Value of each node
 *
 * A tree where each node inside a tree has its own identifier and can have its own value
 */
public interface IdTree<I, V> extends Tree<V> {

    void add(List<I> sequence, I identifier, V value);

    default void add(List<I> sequence, I identifier){
        add(sequence, identifier, null);
    }

    /**
     *
     * @param sequence
     * @param value
     * @return
     * @throws java.util.NoSuchElementException if the element did not exist
     */
    IdTreeNodeReader<I, V> replace(Deque<I> sequence, I identifier, @NotNull V value);

    default IdTreeNodeReader<I, V> replace(Deque<I> sequence, I identifier){
       return replace(sequence, identifier, null);
    }

    /**
     *
     * @param sequence
     * @throws java.util.NoSuchElementException If no such entry exists
     */
    IdTreeNodeReader<I, V> get(List<I> sequence);

    /**
     *
     * @param sequence
     * @throws java.util.NoSuchElementException If no such entry exists
     */
    IdTreeNodeReader<I, V> remove(Deque<I> sequence);

    /**
     * Returns the traverser for the given starting point
     * @param startingPoint the starting point of the traversion
     * @return The traverser for the given starting point
     * @throws java.util.NoSuchElementException if no such node exists
     */
    IdTraverser<I, V> traverser(List<I> startingPoint);


}
