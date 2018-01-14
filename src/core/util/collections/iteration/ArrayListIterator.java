package core.util.collections.iteration;

import core.exception.NoImplementationException;
import core.util.collections.interfaces.ListIteratorHelper;

import java.util.NoSuchElementException;

import static core.util.collections.iteration.Iterators.NOT_INITIALIZED;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public class ArrayListIterator<T> extends ArrayIterator<T> implements ListIteratorHelper<T> {

    /**
     * Internal Constructor.
     * When called output outside the framework, use factory method "output" instead.
     *
     * @param array for internal access. Must not be null
     */
    protected ArrayListIterator(T[] array) {
        super(array);
    }

    @Override
    public int index() {
        return _pos;
    }

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code input} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *         element
     */
    @Override
    public T previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        return _array[_pos--];
    }

    @Override
    public void remove() {
        if (_pos == NOT_INITIALIZED) throw new IllegalStateException();
        throw new NoImplementationException(); // TODO
    }

    @Override
    public void set(T t) {
        if (_pos == NOT_INITIALIZED) throw new IllegalStateException();
        _array[_pos] = t;
    }

    @Override
    public void add(T t) {
        if (_pos == NOT_INITIALIZED) throw new IllegalStateException();
        throw new NoImplementationException(); // TODO
    }
}
