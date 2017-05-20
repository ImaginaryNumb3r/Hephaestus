package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.util.annotations.ToTest;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible;

/**
 * @author Patrick
 * @since 19.05.2017
 */
@ToTest
@SuppressWarnings("WeakerAccess")
public class GenericListIterator<T> extends AbstractListIterator<T> {

    protected GenericListIterator(@NotNull Accessible<T> accessible, int length) {
        super(accessible, length);
    }

    /**
     * Returns a GenericIterator<T> from the given lambda and length information
     * @param accessible providing access to the collection or array. May not be null
     * @param length of the array or collection to determine when the destination is reached
     * @return GenericIterator<T> based on parameters
     */
    public static <T> GenericListIterator<T> from(@NotNull Accessible<T> accessible, int length) {
        Contract.checkNull(accessible, "accessible");
        if (length < 0) throw new IllegalArgumentException("Length may not be smaller than zero");
        return new GenericListIterator<>(accessible, length);
    }

    /**
     * Returns a GenericIterator<T> from the given lambda and length information
     * @param items providing access to the collection or array. May not be null
     * @return GenericIterator<T> based on parameters
     */
    public static <T> GenericListIterator<T> from(@NotNull T... items) {
        Contract.checkNull(items, "items");
        return new GenericListIterator<>(i -> items[i], items.length);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException();
    }
}
