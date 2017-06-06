package core.util.collections.iteration;

import com.sun.istack.internal.NotNull;
import core.exception.InstanceNotAllowedException;
import core.util.annotations.ToTest;
import core.util.contracts.Contract;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

import static core.datastructure.Lazy.lazily;

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

    public static <T> ListIterator<T> empty() {
        return new EmptyIterator<>();
    }

    //<editor-fold desc="Iterator Construction">
    /**
     * Returns an iterator from the given array
     * @param array that is to be turned into an Array. May not be null
     * @throws core.exception.ParameterNullException if parameter array is null
     * @return the iterator from the given array
     */
    public static <T> ListIterator<T> from(T... array){
        Contract.checkNull(array);
        return new ArrayListIterator<>(array);
    }

    public Iterator<Character> from(@NotNull CharSequence sequence){
        Contract.checkNull(sequence, "sequence");
        return new GenericListIterator.GenericListIteratorImpl<>(sequence::charAt, sequence.length());
    }

    public Iterator<Character> from(@NotNull char[] chars){
        Contract.checkNull(chars);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericIterator<>(index -> chars[index], chars.length);
    }
    //</editor-fold>

    //<editor-fold desc="Equals">
    @SafeVarargs
    public static <T> boolean equals(@NotNull T[]iter1, @NotNull T... iter2){
        return equals(Iterators.from(iter1), Iterators.from(iter2));
    }

    @SafeVarargs
    public static <T> boolean equals(@NotNull Iterator<T> iter1, @NotNull T... iter2){
        return equals(iter1, Iterators.from(iter2));
    }

    public static <T> boolean equals(@NotNull T[] iter2, @NotNull Iterator<T> iter1){
        return equals(iter1, Iterators.from(iter2));
    }

    @ToTest // TODO: Testing
    public static <T> boolean equals(@NotNull Iterator<T> iter1, @NotNull Iterator<T> iter2){
        Contract.checkNulls(iter1, iter2);
        boolean equals;
        // Return true if both iterators are empty
        if (!iter1.hasNext() && !iter2.hasNext()){
            equals = true;
        }
        else {
            do {
                // Both iterators must have next elements, otherwise NoSuchElement Ex would be thrown.
                equals = iter1.hasNext() == iter2.hasNext();

                // Fails if iterators are of uneven length or if comparison fails
                if (equals){
                    equals = Objects.equals(iter1.next(), iter2.next());
                }

                // Finishes when both iterators are found to be not equal or if no elements exist anymore
            } while (equals && iter1.hasNext());
        }

        return equals;
    }
    //</editor-fold>
}
