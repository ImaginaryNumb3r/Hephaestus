package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.util.interfaces.Accessible;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 19.05.2017
 *
 * General purpose ListIterator that implements the most common traits just as next and previous.
 */
@SuppressWarnings("WeakerAccess")
public abstract class AbstractListIterator<T> extends GenericIterator<T> implements ListIterator<T>{

    protected AbstractListIterator(@NotNull Accessible<T> accessible, int length) {
        super(accessible, length);
    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return an element
     * rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     *         traversing the list in the reverse direction
     */
    @Override
    public boolean hasPrevious() {
        return _pos != 0;
    }

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code next} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *         element
     */
    @Override
    public T previous() {
        if (!hasPrevious()) throw new NoSuchElementException();
        return _accessible.getAt(_pos--);
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #next}. (Returns list size if the list
     * iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code next}, or list size if the list
     *         iterator is at the end of the list
     */
    @Override
    public int nextIndex() {
        return hasNext()
                ? _pos + 1
                : _length;
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #previous}. (Returns -1 if the list
     * iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code previous}, or -1 if the list
     *         iterator is at the beginning of the list
     */
    @Override
    public int previousIndex() {
        return _pos - 1;
    }
}
