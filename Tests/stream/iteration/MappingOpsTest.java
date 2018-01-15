package stream.iteration;

import core.util.collections.iteration.Iterators;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
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
}