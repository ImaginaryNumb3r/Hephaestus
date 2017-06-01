package core.util.collections.iteration;

import core.util.collections.interfaces.BiLinkable;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public class EmptyIterator<T> extends NodeListIterator<T, EmptyIterator.EmptyLinkable<T>> {

    public EmptyIterator() {
        super(new EmptyLinkable<>());
    }

    @Override
    public void remove() {
        throw new IllegalStateException();
    }

    @Override
    public void add(Object o) {
        throw new IllegalStateException();
    }

    @Override
    public void set(Object o) {
        throw new IllegalStateException();
    }

    public static <T> EmptyIterator<T> from(){
        return new EmptyIterator<>();
    }

    public static class EmptyLinkable<T> implements BiLinkable<T, EmptyLinkable<T>>{

        @Override
        public T value() {
            return null;
        }

        @Override
        public EmptyLinkable<T> next() {
            return null;
        }

        @Override
        public EmptyLinkable<T> previous() {
            return null;
        }
    }
}
