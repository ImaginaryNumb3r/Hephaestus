package core.util.collections.iteration;

import com.sun.istack.internal.NotNull;
import core.util.annotations.ToTest;
import core.util.collections.iterating.AbstractListIterator;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible;

/**
 * @author Patrick
 * @since 19.05.2017
 */
@ToTest
@SuppressWarnings("WeakerAccess")
public abstract class GenericListIterator<T> extends AbstractListIterator<T> {

    protected GenericListIterator(@NotNull Accessible<T> accessible, int length) {
        super(accessible, length);
    }

    /**
     * Returns a GenericIterator<I> from the given lambda and length information
     * @param accessible providing access to the collection or array. May not be null
     * @param length of the array or collection to determine when the destination is reached
     * @return GenericIterator<I> based on parameters
     */
    public static <T> GenericListIterator<T> from(@NotNull Accessible<T> accessible, int length) {
        Contract.checkNull(accessible, "accessible");
        Contract.checkNegative(length, "length ");
        return new GenericListIteratorImpl<>(accessible, length);
    }

    /**
     * Returns a GenericIterator<I> from the given lambda and length information
     * @param items providing access to the collection or array. May not be null
     * @return GenericIterator<I> based on parameters
     */
    public static <T> GenericListIterator<T> from(@NotNull T... items) {
        Contract.checkNull(items, "items");
        return new GenericListIteratorImpl<>(i -> items[i], items.length);
    }

    //<editor-fold desc="Inner Impl Class">
    protected static class GenericListIteratorImpl<I> extends GenericListIterator<I>{

        protected GenericListIteratorImpl(@NotNull Accessible<I> accessible, int length) {
            super(accessible, length);
        }

        /**
         * No implementation
         * @throws UnsupportedOperationException on call
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /**
         * No implementation
         * @throws UnsupportedOperationException on call
         */
        @Override
        public void set(I t) {
            throw new UnsupportedOperationException();
        }

        /**
         * No implementation
         * @throws UnsupportedOperationException on call
         */
        @Override
        public void add(I t) {
            throw new UnsupportedOperationException();
        }
    }
    //</editor-fold>
}
