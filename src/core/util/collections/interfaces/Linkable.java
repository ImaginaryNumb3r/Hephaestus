package core.util.collections.interfaces;

import core.exception.ParameterNullException;

import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 01.06.2017
 *
 * Lightweight interface for the nodes of an iterator inside a LinkedList or similar structures.
 */
public interface Linkable<T> {

    T next() throws NoSuchElementException;

    default boolean hasNext(){
        return next() != null;
    }

}
