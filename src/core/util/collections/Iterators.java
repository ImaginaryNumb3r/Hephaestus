package core.util.collections;

import core.util.contracts.Contract;

import java.util.Iterator;

/**
 * @author Patrick
 * @description
 * @since 01.05.2017
 */
@SuppressWarnings("WeakerAccess")
public final class Iterators {

    private Iterators(){
        throw new UnsupportedOperationException("Cannot instantiate class \"Iterators\"");
    }

    public static <T> Iterable<T> asIterable(T[] array){
        Contract.checkNull((Object[]) array);
        return () -> from(array);
    }

    /**
     * Returns an iterator from the given array
     * @param array that is to be turned into an Array. May not be null
     * @throws core.exception.ParameterNullException if parameter array is null
     * @return the iterator from the given array
     */
    public static <T> Iterator<T> from(T[] array){
        return ArrayIterator.from(array);
    }
}
