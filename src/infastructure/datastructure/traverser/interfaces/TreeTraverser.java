package infastructure.datastructure.traverser.interfaces;

import infastructure.datastructure.basic.HIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @created 28.05.2016
 * @purpose
 */
public interface TreeTraverser<T> extends HIterator<T> {

    /**
     * enters the current node and moves down one level in the node hierarchy
     */
    void enter();

    /**
     * backs off from the current node and moves up one level in the node hierarchy
     */
    void back();


    /**
     * Returns the last element that was being iterated through
     *
     * @return the last element that was being iterated through
     */

    T current();

    /**
     * Returns the tryNext element in the iteration.
     *
     * @return the tryNext element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    T next();



    /**
     * Returns the tryPrevious element in the iteration.
     *
     * @return the tryPrevious element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    T previous();

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();


    /**
     * Returns {@code true} if the iteration has prior elements.
     * (In other words, returns {@code true} if {@link #previous()} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has prior elements
     */
    boolean hasPrevious();

}
