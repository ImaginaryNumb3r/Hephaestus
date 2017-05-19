package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 05.05.2017
 */
@SuppressWarnings("WeakerAccess")
public class GenericIterator<T> implements Iterator<T> {
    protected final Accessible<T> _accessible;
    protected final int _length;
    protected int _pos;

    GenericIterator(@NotNull Accessible<T> accessible, int length) {
        _accessible = accessible;
        _length = length;
    }

    /**
     * Returns a GenericIterator<T> from the given lambda and length information
     * @param accessible providing access to the collection or array. May not be null
     * @param length of the array or collection to determine when the destination is reached
     * @return GenericIterator<T> based on parameters
     */
    public static <T> GenericIterator<T> from(@NotNull Accessible<T> accessible, int length) {
        Contract.checkNull(accessible);
        if (length < 0) throw new IllegalArgumentException("Length may not be smaller than zero");
        return new GenericIterator<>(accessible, length);
    }
    /**
     * Returns a GenericIterator<T> from the given lambda and length information
     * @param items providing access to the collection or array. May not be null
     * @return GenericIterator<T> based on parameters
     */
    public static <T> GenericIterator<T> from(@NotNull T... items) {
        Contract.checkNull(items, "items");
        return new GenericIterator<>(i -> items[i], items.length);
    }

    @Override
    public boolean hasNext() {
        return _pos < _length;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        return _accessible.getAt(_pos++);
    }
}
