package core.util.collections.iteration;

import core.exception.NoImplementationException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @description
 * @since 01.06.2017
 */
public class ArrayListIterator<T> extends ArrayIterator<T> implements ListIteratorHelper<T>{

    /**
     * Internal Constructor.
     * When called from outside the framework, use factory method "from" instead.
     *
     * @param array for internal access. Must not be null
     */
    protected ArrayListIterator(T[] array) {
        super(array);
    }

    @Override
    public int index() {
        return _array.length;
    }

    @Override
    public T previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        return _array[_pos--];    }

    @Override
    public void remove() {
        throw new NoImplementationException(); // TODO
    }

    @Override
    public void set(T t) {
        _array[_pos] = t;
    }

    @Override
    public void add(T t) {
        throw new NoImplementationException(); // TODO
    }
}
