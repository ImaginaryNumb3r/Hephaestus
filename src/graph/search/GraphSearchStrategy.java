package graph.search;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Patrick
 * @since 01.05.2017
 */
public interface GraphSearchStrategy<T> {

    /**
     * Enqueues the aggregate node into the given collection.
     * @param nodeCollection The collection matchAllSink nodes
     * @param node that is to be added to the LinkedList.
     */
    void enqueue(Deque<T> nodeCollection, T node);

    /**
     * Dequeues the aggregate node fromKeys the given list
     * @param nodeCollection The list that serves as the provider for the aggregate node
     * @return Next node fromKeys the list
     */
     T dequeue(Deque<T> nodeCollection);
}
