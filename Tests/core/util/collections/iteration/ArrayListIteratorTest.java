package core.util.collections.iteration;


import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 01.06.2017
 */
// TODO: Add test cases which include null values
public class ArrayListIteratorTest {
    private static final String[] STRINGS = {"Item1", "Item2", "Item3", "Item4", "Item5"};

    private static ArrayListIterator<String> get(){
        return new ArrayListIterator<>(STRINGS);
    }

    @Test
    public void testIteration() {
        ArrayListIterator<String> iterator = get();
        assert Iterators.equals(iterator, STRINGS);
    }


    @Test
    public void testEmptyArray() {
        Integer[] emptyArray = new Integer[0];
        ArrayListIterator<Integer> iterator = new ArrayListIterator<>(emptyArray);
        assert !iterator.hasNext();
        assert !iterator.hasPrevious();

        try {
            iterator.next();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }

        try {
            iterator.previous();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }
    }


    @Test
    public void testSingletonArray() {
        final int element = 1;

        ArrayListIterator<Integer> iterator = new ArrayListIterator<>(new Integer[]{element});
        assert iterator.hasNext();

        boolean equals = iterator.next() == element;
        assert equals;

        assert iterator.hasPrevious();

        try {
            iterator.next();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }

        equals = iterator.previous() == element;
        assert equals;

        assert !iterator.hasPrevious();
        try {
            iterator.previous();
            assert false; // Must throw NoSuchElementException
        } catch (NoSuchElementException ignored){ }
    }

    @Test
    public void testIllegalStateException() {
        ArrayListIterator<String> iterator = get();

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