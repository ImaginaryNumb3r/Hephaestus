package core.util.collections.iteration;

import org.junit.jupiter.api.Test;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
class EmptyIteratorTest {


    @Test
    void testNext() {
        ListIterator<Object> iter = new EmptyIterator<>();
        assert !iter.hasNext();
        try {
            iter.next();
            assert false;
        } catch (NoSuchElementException ignore){ }
    }

    @Test
    void testPrevious() {
        ListIterator<Object> iter = new EmptyIterator<>();
        assert !iter.hasPrevious();
        try {
            iter.previous();
            assert false;
        } catch (NoSuchElementException ignore){ }
    }

    @Test
    void testIllegalStateException() {
        ListIterator<String> iterator = EmptyIterator.get();

        try{
            iterator.set(null);
            assert false;
        } catch (IllegalStateException ignore){ }

        try{
            iterator.remove();
            assert false;
        } catch (IllegalStateException ignore){ }

        try{
            iterator.add(null);
            assert false;
        } catch (IllegalStateException ignore){ }
    }
}