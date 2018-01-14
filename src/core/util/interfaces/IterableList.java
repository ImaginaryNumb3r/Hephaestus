package core.util.interfaces;

import org.jetbrains.annotations.NotNull;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 */
// TODO: Move to Collections Framework
public interface IterableList<T> extends Iterable<T> {

    /**
     * Returns a ListIterator over elements of type {@code T}.
     * @return a ListIterator
     */
    @NotNull
    @Override
    default ListIterator<T> iterator(){
        return listIterator();
    }

    /**
     * Returns a ListIterator over elements of type {@code T}.
     * @return a ListIterator
     */
    @NotNull
    ListIterator<T> listIterator();
}
