package core.util.collections.interfaces;

import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * @author Patrick
 * @since 01.06.2017
 *
 * Interface for bidirectional nodes inside of a ListIterator
 */
public interface BiLinkable<T> extends Linkable<T, BiLinkable<T>> {

    /**
     * @return  The tryPrevious node to the current one
     *          null if no element beyond this node exists previously.
     */
    BiLinkable<T> previous();

    /**
     * @return The tryPrevious element in the iteration that was returned
     * @throws NoSuchElementException if the iteration has no more elements
     */
    default BiLinkable<T> tryPrevious() throws NoSuchElementException{
        BiLinkable<T> previous = previous();
        if (previous.value() == null) throw new NoSuchElementException();
        return previous;
    }

    default boolean hasPrevious(){
        return tryPrevious() != null;
    }

    static <T> BiLinkable<T> from(T value, Function<T, T> advanceFunc){
        return from(value, advanceFunc, null);
    }

    static <T> BiLinkable<T> from(T value, Function<T, T> advanceFunc, BiLinkable<T> previous){
        return new BiLinkable<T>() {
            @Override
            public BiLinkable<T> previous() {
                return previous;
            }

            @Override
            public T value() {
                return value;
            }

            @Override
            public BiLinkable<T> next() {
                return BiLinkable.from(advanceFunc.apply(value), advanceFunc);
            }
        };
    }
}
