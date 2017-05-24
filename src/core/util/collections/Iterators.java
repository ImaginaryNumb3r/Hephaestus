package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.exception.InstanceNotAllowedException;
import core.tuple.Tuple;
import core.util.contracts.Contract;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Patrick
 * @since 01.05.2017
 */
@SuppressWarnings("WeakerAccess")
public final class Iterators {

    /**
     * @throws UnsupportedOperationException Cannot be instantiated
     */
    private Iterators(){
        throw new InstanceNotAllowedException(getClass());
    }

    public static <T> Iterable<T> asIterable(@NotNull T[] array){
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
