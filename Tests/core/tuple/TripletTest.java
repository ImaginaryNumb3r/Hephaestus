package core.tuple;

import org.junit.Test;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class TripletTest {

    @Test
    public void testEquals() throws Exception {
        String valA = "valA";
        int valB = 2;
        double valC = 3.0;

        Triplet<String, Integer, Double> tuple1 = new Triplet<>(valA, valB, valC);
        Triplet<String, Integer, Double> tuple2 = new Triplet<>(valA, valB, valC);
        Triplet<String, Integer, Double> wrong = new Triplet<>(valA, valB, valC + 1);

        assert tuple1.equals(tuple2);
        assert !tuple1.equals(wrong);
    }

    @Test
    public void testNotEquals() throws Exception {
        String valA = "valA";
        int valB = 2;
        double valC = 3.0;

        Triplet<String, Integer, Double> tuple1 = new Triplet<>(valA, valB, valC);
        Tuple<String, Integer> tuple2 = new Tuple<>(valA, valB);

        assert !tuple2.equals(tuple1);
        assert !tuple1.equals(tuple2);
    }
}