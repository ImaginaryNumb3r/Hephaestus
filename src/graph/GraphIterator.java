package graph;

import com.sun.istack.internal.NotNull;
import core.util.contracts.Contract;
import graph.search.GraphSearchStrategy;

import java.util.*;

/**
 * @author Patrick
 * @since 01.05.2017
 */
@SuppressWarnings("WeakerAccess")
public class GraphIterator<N extends Iterable<N>> implements Iterator<N> {
    protected final GraphSearchStrategy<N> _strategy;
    protected final LinkedList<N> _nodes;
    protected final N _root;
    protected N _current;

    protected GraphIterator(N root, GraphSearchStrategy<N> strategy){
        _nodes = new LinkedList<>();
        _strategy = strategy;
        _root = root;
        _current = _root;

        strategy.enqueue(_nodes, _current);
    }

    /**
     * Creates a GraphIterator from the given root of a tree
     * @param source root of the tree
     * @param strategy to create a list based from the branching nodes of the root
     * @param <T> Type of root. Must be iterable to have access its children
     * @return GraphIterator<T> based from the given parameters
     */
    public static <T extends Iterable<T>> GraphIterator<T> from (T source, @NotNull GraphSearchStrategy<T> strategy){
        Contract.checkNull(strategy);
        return new GraphIterator<>(source, strategy);
    }

    @Override
    public boolean hasNext() {
        return !_nodes.isEmpty();
    }

    @Override
    public N next() {
        if (!hasNext()) throw new NoSuchElementException();

        N next = _strategy.dequeue(_nodes);
        next.forEach(node -> _strategy.enqueue(_nodes, node));

        return next;
    }
}