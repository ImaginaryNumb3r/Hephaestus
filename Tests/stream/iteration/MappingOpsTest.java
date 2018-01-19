package stream.iteration;

import core.util.collections.Lists;
import core.util.collections.iteration.Iterators;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Patrick
 * @since 15.01.2018
 */
public class MappingOpsTest {

    @Test
    public void testMapping() throws Exception {
        int[] intValues = {0, 1, 2, 3, 4, 5};
        String[] stringValues = {"0", "1", "2", "3", "4", "5"};
        ListIterator<Integer> iterator = Iterators.from(intValues);
        List<String> strings = Arrays.asList(stringValues);

        List<String> mappedList = Iteration.of(iterator)
                .map(Object::toString)
                .collect(Collectors.toList());

        assert Objects.equals(strings, mappedList);
    }

    @Test
    public void testIndexMapping() throws Exception {
        String[] strings = {"a", "ba", null, "ao", "%"};
        Integer[] indices = {0, 1, 2, 3, 4};

        ListIterator<String> iterator = Iterators.from(strings);

        List<Integer> indexList = Iteration.of(iterator)
                .mapIndices((val, index) -> index)
                .collect(Collectors.toList());

        ArrayList<Integer> expectedIndices = Lists.toArrayList(indices);
        assert Objects.equals(expectedIndices, indexList);
    }
}