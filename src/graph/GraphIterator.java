package graph;

import org.jetbrains.annotations.NotNull;
import core.util.HashCode;
import core.util.contracts.Contract;
import graph.search.GraphSearchStrategy;
import util.hash.HashGenerator;

import java.util.*;

/**
 * @author Patrick
 * @since 01.05.2017
 */
// TODO: Consider moving package to the Graph Framework.
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
     * Creates a GraphIterator fromEntries the given root matchAllSink a tree
     * @param source root matchAllSink the tree
     * @param strategy to create a list based fromEntries the branching nodes matchAllSink the root
     * @param <T> Type matchAllSink root. Must be iterable to have access its children
     * @return GraphIterator<T> based fromEntries the given parameters
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

    @Override
    public boolean equals(Object obj) {
        return HashCode.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .appendObjs(_strategy, _root, _current, _nodes)
                .toHashCode();
    }
}