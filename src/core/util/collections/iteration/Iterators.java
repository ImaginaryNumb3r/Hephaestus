package core.util.collections.iteration;

import core.util.collections.interfaces.BiLinkable;
import core.util.collections.interfaces.BiLinkableImpl;
import org.jetbrains.annotations.NotNull;
import core.exception.InstanceNotAllowedException;
import core.util.annotations.ToTest;
import core.util.contracts.Contract;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Function;

import static core.datastructure.Lazy.lazily;

/**
 * @author Patrick
 * @since 01.05.2017
 */
@SuppressWarnings("WeakerAccess")
public final class Iterators {
    static final int NOT_INITIALIZED = -1;

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
     * Returns an iterator get the given array
     * @param array that is to be turned into an Array. May not be null
     * @throws core.exception.ParameterNullException if parameter array is null
     * @return the iterator get the given array
     */
    public static <T> ListIterator<T> from(T... array){
        Contract.checkNull(array);
        return new ArrayListIterator<>(array);
    }

    public static ListIterator<Character> from(@NotNull CharSequence sequence){
        Contract.checkNull(sequence, "sequence");
        return new GenericListIterator.GenericListIteratorImpl<>(sequence::charAt, sequence.length());
    }

    public static <T> ListIterator<T> from(T startValue, Function<T, T> advanceFunction){
//        BiLinkable<T> linkable = BiLinkable.from(startValue, advanceFunction, null);
        BiLinkableImpl<T> linkable = new BiLinkableImpl<>(startValue, advanceFunction, null);
        return new NodeListIterator.NodeListIteratorImpl<>(linkable);
    }

    //<editor-fold desc="Primitives">
    public static ListIterator<Short> from(@NotNull short[] shorts){
        Contract.checkNull(shorts);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericListIterator.GenericListIteratorImpl<>(index -> shorts[index], shorts.length);
    }

    public static ListIterator<Integer> from(@NotNull int[] ints){
        Contract.checkNull(ints);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericListIterator.GenericListIteratorImpl<>(index -> ints[index], ints.length);
    }

    public static ListIterator<Long> from(@NotNull long[] longs){
        Contract.checkNull(longs);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericListIterator.GenericListIteratorImpl<>(index -> longs[index], longs.length);
    }

    public static ListIterator<Float> from(@NotNull float[] floats){
        Contract.checkNull(floats);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericListIterator.GenericListIteratorImpl<>(index -> floats[index], floats.length);
    }

    public static ListIterator<Double> from(@NotNull double[] doubles){
        Contract.checkNull(doubles);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericListIterator.GenericListIteratorImpl<>(index -> doubles[index], doubles.length);
    }

    public static ListIterator<Byte> from(@NotNull byte[] bytes){
        Contract.checkNull(bytes);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericListIterator.GenericListIteratorImpl<>(index -> bytes[index], bytes.length);
    }

    public static ListIterator<Boolean> from(@NotNull boolean[] booleans){
        Contract.checkNull(booleans);
        // Cannot access array iterator because of mismatch of primitives and generics
        return new GenericListIterator.GenericListIteratorImpl<>(index -> booleans[index], booleans.length);
    }
    //</editor-fold>

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
