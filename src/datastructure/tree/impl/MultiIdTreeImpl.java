package datastructure.tree.impl;

import com.sun.istack.internal.NotNull;
import core.util.interfaces.IterableList;
import datastructure.tree.impl.node.MultiIdTreeNodeImpl;
import datastructure.tree.impl.node.MultiIdTreeNodeReaderImpl;
import datastructure.tree.node.subtype.subtype.MultiIdTreeNodeReader;
import graph.search.GraphIterator;
import graph.search.GraphSearchStrategy;

import java.util.Iterator;


/**
 * @author Patrick
 * @since 29.01.2017
 * @param <I> Identifier of each node
 * @param <V> Type of value of each node
 */
public class MultiIdTreeImpl<I extends Comparable<I>, V>
        extends AbstractMultiIdTree<I, V, MultiIdTreeNodeImpl<I, V>> {

    @SuppressWarnings("WeakerAccess")
    protected MultiIdTreeImpl(I identifier, V value){
        super(new MultiIdTreeNodeImpl<>(identifier, value, null));
    }

    protected MultiIdTreeImpl(@NotNull MultiIdTreeNodeImpl<I, V> root){
        super(root);
    }

    @Override
    protected MultiIdTreeNodeImpl<I, V> makeNode(I identifier, V value, MultiIdTreeNodeImpl<I, V> root) {
        return new MultiIdTreeNodeImpl<>(identifier, value, root);
    }

    public Iterator<MultiIdTreeNodeReaderImpl<I, V>> iterator(GraphSearchStrategy strategy){
        return GraphIterator.from(_sentinel.toReadOnly(), strategy);
    }



    private class TreeIterator<N extends MultiIdTreeNodeReader<I, V, N>> implements Iterator<N> {

        public TreeIterator(){

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public N next() {
            return null;
        }
    }

}
