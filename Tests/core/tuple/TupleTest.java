package core.tuple;

import org.junit.Test;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class TupleTest {

    @Test
    public void testEquals() throws Exception {
        String valA = "valA";
        Integer valB = 2;

        Tuple<String, Integer> tuple1 = Tuple.from(valA, valB);
        Tuple<String, Integer> tuple2 = Tuple.from(valA, valB);

        assert tuple1.equals(tuple2);
    }
}