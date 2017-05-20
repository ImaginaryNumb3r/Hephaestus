package core.util.interfaces;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public interface IterableList<T> extends Iterable<T> {

    /**
     * Returns a ListIterator over elements of type {@code T}.
     * @return a ListIterator
     */
    @Override
    default ListIterator<T> iterator(){
        return listIterator();
    }

    /**
     * Returns a ListIterator over elements of type {@code T}.
     * @return a ListIterator
     */
    ListIterator<T> listIterator();
}
