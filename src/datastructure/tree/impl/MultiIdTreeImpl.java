package datastructure.tree.impl;

import com.sun.istack.internal.NotNull;
import datastructure.tree.impl.node.MultiIdTreeNodeImpl;
import datastructure.tree.impl.node.MultiIdTreeNodeReaderImpl;
import graph.GraphIterator;
import graph.search.DepthFirstSearch;
import graph.search.GraphSearchStrategy;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 29.01.2017
 * @param <I> Identifier of each node
 * @param <V> Type of value of each node
 */
public class MultiIdTreeImpl<I extends Comparable<I>, V>
        extends AbstractMultiIdTree<I, V, MultiIdTreeNodeImpl<I, V>, MultiIdTreeNodeReaderImpl<I, V>>
        implements Iterable<MultiIdTreeNodeReaderImpl<I, V>> {

    //<editor-fold desc="Constructors">
    @SuppressWarnings("WeakerAccess")
    protected MultiIdTreeImpl(I identifier, V value){
        super(new MultiIdTreeNodeImpl<>(identifier, value, null));
    }

    protected MultiIdTreeImpl(@NotNull MultiIdTreeNodeImpl<I, V> root){
        super(root);
    }
    //</editor-fold>

    @Override
    protected MultiIdTreeNodeImpl<I, V> makeNode(I identifier, V value, MultiIdTreeNodeImpl<I, V> root) {
        return new MultiIdTreeNodeImpl<>(identifier, value, root);
    }

    /**
     * Iterate the tree by a given strategy
     * @param strategy for iteration
     * @return InnerIterator for all nodes of the tree
     */
    @Override
    public Iterator<MultiIdTreeNodeReaderImpl<I, V>> iterator(GraphSearchStrategy<MultiIdTreeNodeReaderImpl<I, V>> strategy){
        return GraphIterator.from(_sentinel.toReadOnly(), strategy);
    }
}
