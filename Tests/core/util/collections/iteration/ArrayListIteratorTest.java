package core.util.collections.iteration;

import org.junit.jupiter.api.Test;

/**
 * @author Patrick
 * @since 01.06.2017
 */
class ArrayListIteratorTest {
    private static final String[] STRINGS = {"Item1", "Item2", "Item3", "Item4", "Item5"};

    private static ArrayListIterator<String> get(){
        return new ArrayListIterator<>(STRINGS);
    }

    @Test
    void testIteration() {
        ArrayListIterator<String> iterator = get();
        assert Iterators.equals(iterator, STRINGS);
    }

    @Test
    void testIllegalStateException() {
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