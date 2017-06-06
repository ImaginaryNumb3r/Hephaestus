package core.util.collections.iteration;

import core.exception.NoImplementationException;
import core.util.collections.interfaces.ListIteratorHelper;

import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public class ArrayListIterator<T> extends ArrayIterator<T> implements ListIteratorHelper<T> {

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
        return _pos;
    }

    @Override
    public T previous() {
        if (_pos == null) _pos = 0;
        if (!hasPrevious()) throw new NoSuchElementException();
        return _array[_pos--];
    }

    @Override
    public void remove() {
        if (_pos == null) throw new IllegalStateException();
        throw new NoImplementationException(); // TODO
    }

    @Override
    public void set(T t) {
        if (_pos == null) throw new IllegalStateException();
        _array[_pos] = t;
    }

    @Override
    public void add(T t) {
        if (_pos == null) throw new IllegalStateException();
        throw new NoImplementationException(); // TODO
    }
}
