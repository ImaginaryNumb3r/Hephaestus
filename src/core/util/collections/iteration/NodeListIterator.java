package core.util.collections.iteration;

import com.sun.istack.internal.NotNull;
import core.util.collections.interfaces.BiLinkable;
import core.util.contracts.Contract;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public abstract class NodeListIterator<T extends BiLinkable<T>> extends NodeIterator<T> implements ListIteratorHelper<T> {
    protected int _index;

    protected NodeListIterator(@NotNull T startNode) {
        super(startNode);
        _index = 0;
    }

    @Override
    public T next(){
        ++_index;
        return _current.next();
    }

    @Override
    public T previous() {
        --_index;
        return _current.previous();
    }

    @Override
    public int index() {
        return _index;
    }

    public static <T extends BiLinkable<T>> NodeListIterator<T> from(T startNode) {
        Contract.checkNull(startNode, "startNode");
        return new NodeListIteratorImpl<>(startNode);
    }

    //<editor-fold desc="Inner Impl Class">
    protected static class NodeListIteratorImpl<I extends BiLinkable<I>> extends NodeListIterator<I> {

        protected NodeListIteratorImpl(@NotNull I startNode) {
            super(startNode);
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
