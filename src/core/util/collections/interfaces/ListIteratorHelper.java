package core.util.collections.interfaces;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 01.06.2017
 *
 * Internal helper Interface to unify logic and minimize code imprint.
 * Has default implementations for hasPrevious(), nextIndex() and previousIndex();
 */
public interface ListIteratorHelper<T> extends ListIterator<T> {

    /**
     * Returns the index of the last returned element
     * @return the index of the last returned element
     */
    int index();

    @Override
    default boolean hasPrevious() {
        return index() > 0;
    }

    @Override
    default int nextIndex() {
        return hasNext()
                ? index() + 1
                : index();
    }

    @Override
    default int previousIndex() {
        return hasPrevious()
                ? index() - 1
                : 0;
    }
}