package core.util.collections.iteration;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * Creator: Patrick
 * Created: 02.08.2017
 * Purpose:
 */
class ArrayIteratorTest {

    @Test
    void testEmptyArray() {
        Integer[] emptyArray = new Integer[0];
        ArrayIterator<Integer> iterator = new ArrayIterator<>(emptyArray);
        assert !iterator.hasNext();

        try {
            iterator.next();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }
    }

    @Test
    void testSingletonArray() {
        final int element = 1;

        ArrayIterator<Integer> iterator = new ArrayIterator<>(new Integer[]{element});
        assert iterator.hasNext();

        boolean equals = iterator.next() == element;
        assert equals;

        try {
            iterator.next();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }
    }
}