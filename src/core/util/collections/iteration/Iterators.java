package core.util.collections.iteration;

import com.sun.istack.internal.NotNull;
import core.exception.InstanceNotAllowedException;
import core.exception.ParameterNullException;
import core.util.contracts.Contract;

import java.util.Iterator;

import static core.util.contracts.Contract.check;

/**
 * @author Patrick
 * @since 01.05.2017
 */
@SuppressWarnings("WeakerAccess")
public final class Iterators {

    /**
     * @throws InstanceNotAllowedException Cannot be instantiated
     */
    private Iterators(){
        throw new InstanceNotAllowedException(getClass());
    }

    @Deprecated // Use Iterables.from instead
    public static <T> Iterable<T> asIterable(@NotNull T[] array){
        Contract.checkNull(array, "array");
        return () -> from(array);
    }

    public static <T> Iterable<T> asIterable(@NotNull Iterator<T> iterator){
        Contract.checkNull(iterator, "iterator");
        return () -> iterator;
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

    /**
     * Returns an iterator from the given array
     * @param array that is to be turned into an Array. May not be null
     * @throws core.exception.ParameterNullException if parameter array is null
     * @return the iterator from the given array
     */
    public static Iterator<Character> from(Character[] array){
        return ArrayIterator.from(array);
    }


    public Iterator<Character> from(@NotNull CharSequence sequence){
        Contract.checkNull(sequence);
        return new GenericIterator<>(sequence::charAt, sequence.length());
    }

    public Iterator<Character> from(@NotNull char[] chars){
        Contract.checkNulls((Object) chars);
        return new GenericIterator<>(index -> chars[index], chars.length);
    }


}
