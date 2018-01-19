package core.util.collections.iteration;

import core.util.collections.Lists;
import org.junit.Test;

import java.util.*;

/**
 * @author Patrick
 * @since 19.01.2018
 */
public class CompositeIteratorTest {

    @Test
    public void testNulls() {
        List<Object> singleNull1 = Collections.singletonList(null);
        List<Object> singleNull2 = Collections.singletonList(null);
        ArrayList<List<Object>> compositeList = new ArrayList<>();
        compositeList.add(singleNull2);

        assertEqual(singleNull1, compositeList);
    }

    @Test
    public void testEmpty() {
        assertEqual(Collections.emptyList(), Collections.emptyList());
    }

    @Test
    public void assertEqual() {
        List<Integer> intList1 = List.of(0, 1, 2, 3);
        List<Integer> intList2 = List.of(4, 5, 6, 7);
        List<Integer> intList3 = Lists.toArrayList(null, 8, 9, 10);
        List<Integer> expectedList = Lists.toArrayList(
                0, 1, 2, 3,
                4, 5, 6, 7,
                null, 8, 9, 10);

        List<List<Integer>> compositeList = List.of(intList1, intList2, intList3);
        assertEqual(expectedList, compositeList);
    }

    private <T> void assertEqual(List<T> expectedList, List<List<T>> compositeList) {
        Iterator<T> expectedIter = expectedList.iterator();
        CompositeIterator<T> compositeIter = CompositeIterator.of(compositeList);

        boolean equals = expectedIter.hasNext() == compositeIter.hasNext();

        while (equals && expectedIter.hasNext() && compositeIter.hasNext()){
            T expected = expectedIter.next();
            T actual = compositeIter.next();

            equals = Objects.equals(expected, actual);
            equals &= expectedIter.hasNext() == compositeIter.hasNext();
        }

        assert equals;
    }
}