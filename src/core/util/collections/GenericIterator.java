package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 05.05.2017
 */
@SuppressWarnings("WeakerAccess")
public class GenericIterator<T> implements Iterator<T> {
    private final Accessible<T> _collection;
    private final int _length;
    private int _pos;

    public GenericIterator(@NotNull Accessible<T> collection, int length) {
        _collection = collection;
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

    @Override
    public boolean hasNext() {
        return _pos < _length;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new ParameterNullException();
        return _collection.getAt(_pos++);
    }
}
