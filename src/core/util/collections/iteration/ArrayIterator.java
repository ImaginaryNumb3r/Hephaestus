package core.util.collections.iteration;

import com.sun.istack.internal.NotNull;
import core.util.HashCode;
import core.util.contracts.Contract;
import util.hash.HashGenerator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 05.05.2017
 * @param <T> Generic Value of the array
 */
public class ArrayIterator<T> implements Iterator<T> {
    private final T[] _array;
    private int _pos = 0;

    /**
     * Internal Constructor.
     * When called from outside the framework, use factory method "from" instead.
     * @param array for internal access. Must not be null
     */
    @SuppressWarnings("WeakerAccess")
    protected ArrayIterator(T[] array) {
        _array = array;
    }

    /**
     * Returns an iterator from the given array
     * @param array that is to be turned into an Array. May not be null
     * @throws core.exception.ParameterNullException if parameter array is null
     * @return the iterator from the given array
     */
    @SafeVarargs
    public static <T> Iterator<T> from(@NotNull T... array) {
        Contract.checkNull(array);
        return new ArrayIterator<>(array);
    }

    @Override
    public boolean hasNext() {
        return _pos < _array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        return _array[_pos++];
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ArrayIterator && HashCode.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .append(_array)
                .appendAll(_array.length, _pos)
                .toHashCode();
    }
}
