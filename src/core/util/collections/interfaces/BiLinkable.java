package core.util.collections.interfaces;

import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 01.06.2017
 *
 * Interface for bidirectional nodes inside of a ListIterator
 */
public interface BiLinkable<T> extends Linkable<T> {

    T previous() throws NoSuchElementException;

    default boolean hasPrevious(){
        return previous() != null;
    }

}
