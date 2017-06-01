package core.util.collections.interfaces;

import core.util.contracts.Contract;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 01.06.2017
 * @param <T> Value of the node
 * @param <L> Type of node that follows
 * Lightweight interface for the nodes of an iterator inside a LinkedList or similar structures.
 */
public interface Linkable<T, L extends Linkable<T, L>> {

    T value();

    /**
     * @return  The next node to the current one.
     *          null if no element beyond this node exists.
     */
    L next();

    /**
     * @return The next element in the iteration. Throws an exception if no more elements exist
     * @throws NoSuchElementException if the iteration has no more elements
     */
    default L tryNext(){
        L next = next();
        if (next == null) throw new NoSuchElementException();
        return next;
    }

    default boolean hasNext(){
        return next() != null;
    }

    static <T, L extends Linkable<T,L>> Linkable<T, L> from
            (Supplier<T> valueSupplier, Supplier<L> advanceSupplier){
        Contract.checkNulls(valueSupplier, advanceSupplier);
        return new Linkable<T, L>() {
            @Override
            public T value() {
                return valueSupplier.get();
            }

            @Override
            public L next() {
                return advanceSupplier.get();
            }
        };
    }
}