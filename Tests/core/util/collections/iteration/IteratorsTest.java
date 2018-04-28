package core.util.collections.iteration;

import org.junit.Test;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * @author Patrick
 * @description
 * @since 01.06.2017
 */
public class IteratorsTest {
    private static final String[] STRINGS1 = {"item1", "item2", "item3"};
    private static final String[] STRINGS2 = {"item1", "item2", null};
    private static final String[] STRINGS3 = {"fail"};

    @Test
    public void testEqualsSameLength() {

        assert !Iterators.equals(STRINGS1, STRINGS2);
        assert Iterators.equals(STRINGS1, STRINGS1);
        assert Iterators.equals(Iterators.empty(), Iterators.empty());
    }

    @Test
    public void testEqualsDifferentLength() {
        assert !Iterators.equals(Iterators.empty(), STRINGS1);
        assert !Iterators.equals(STRINGS3, STRINGS1);
    }

    @Test
    public void testNodeIterator() {
        int max = 10;
        Iterator<Integer> iterator = Iterators.from(0, i -> i != max ? ++i : null);

        int cur = 0;
        for (Integer i : Iterables.from(iterator)) {
            assert i == ++cur;
        }

        /*
        // Also test reverse
        cur = max;
        while (iterator.hasPrevious()){
            assert --cur == iterator.previous();
        } */
    }
}