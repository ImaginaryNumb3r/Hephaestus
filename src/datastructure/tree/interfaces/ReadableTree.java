package datastructure.tree.interfaces;

import graph.search.DepthFirstSearch;
import graph.search.GraphSearchStrategy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 05.05.2017
 * @param <R> The readable class, accessed from outside
 * A tree that provides access from outside the class
 *
 */
public interface ReadableTree<R> {

    List<R> toList(GraphSearchStrategy<R> strategy);

    default List<R> toList(){
        return toList(new DepthFirstSearch<>());
    }

    default Stream<R> stream(){
        return toList().stream();
    }

    default Stream<R> stream(GraphSearchStrategy<R> strategy){
        return toList(strategy).stream();
    }
}
