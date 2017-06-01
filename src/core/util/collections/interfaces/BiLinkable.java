package core.util.collections.interfaces;

import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 01.06.2017
 *
 * Interface for bidirectional nodes inside of a ListIterator
 */
public interface BiLinkable<T, L extends BiLinkable<T, L>> extends Linkable<T, L> {

    /**
     * @return  The tryPrevious node to the current one
     *          null if no element beyond this node exists previously.
     */
    L previous();

    /**
     * @return The tryPrevious element in the iteration that was returned
     * @throws NoSuchElementException if the iteration has no more elements
     */
    default L tryPrevious() throws NoSuchElementException{
        L previous = previous();
        if (previous == null) throw new NoSuchElementException();
        return previous;
    }

    default boolean hasPrevious(){
        return tryPrevious() != null;
    }
}
