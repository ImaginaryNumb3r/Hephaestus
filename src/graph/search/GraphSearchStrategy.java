package graph.search;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Patrick
 * @since 01.05.2017
 */
public interface GraphSearchStrategy<T> {

    /**
     * Enqueues the next node into the given collection.
     * @param nodeCollection The collection of nodes
     * @param node that is to be added to the LinkedList.
     */
    void enqueue(Deque<T> nodeCollection, T node);

    /**
     * Dequeues the next node fromKeys the given list
     * @param nodeCollection The list that serves as the provider for the next node
     * @return Next node fromKeys the list
     */
     T dequeue(Deque<T> nodeCollection);
}
