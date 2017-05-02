package core.util;

import core.exception.ParameterNullException;

import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @description
 * @since 01.05.2017
 */
@SuppressWarnings("WeakerAccess")
public class Iterators {

    private Iterators(){
    }

    public static <T> Iterable<T> asIterable(T[] array){
        if (array == null) throw new ParameterNullException();
        return () -> From(array);
    }

    public static <T> Iterator<T> From(T[] array){
        if (array == null) throw new ParameterNullException();
        return new Iterator<>(array);
    }

    private static class Iterator<T> implements java.util.Iterator<T>{
        private final T[] _array;
        private int _pos = 0;

        public Iterator(T[] array) {
            _array = array;
        }

        @Override
        public boolean hasNext() {
            return _pos < _array.length;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return _array[_pos++];
        }
    }
}
