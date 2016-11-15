package infastructure.datastructure.map;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Patrick
 * @created 24.07.2016
 */
public class TolerantHashMapTest {

    @Test
    public void testCapacity() throws Exception {
        final int size = 10;
        final int duplication = 2;
        final int expectedSize = size * duplication;

        TolerantHashMapFactory<String, Boolean> factory = new TolerantHashMapFactory<>(0, duplication);
        TolerantHashMap<String, Boolean> hashMap = factory.create(size);

        boolean correctSize = hashMap.totalCapacity() == expectedSize;
        assert correctSize;
    }

    @Test
    public void testCorrectCapacity() throws Exception {
        final int size = 10;
        final int duplication = 2;
        final int capacity = size * duplication;

        TolerantHashMapFactory<Integer, Boolean> factory = new TolerantHashMapFactory<>(0, duplication);
        TolerantHashMap<Integer, Boolean> hashMap = factory.create(size);

        for (int i = 0; i != (capacity - 1); ++i){
            hashMap.put(i, true);
        }

        boolean capacityReached = hashMap.isCapacityReached();
        assert !capacityReached;
    }

    @Test
    public void testOverCapacity() throws Exception {
        final int size = 10;
        final int duplication = 2;
        final int capacity = size * duplication;

        TolerantHashMapFactory<Integer, Boolean> factory = new TolerantHashMapFactory<>(0, duplication);
        TolerantHashMap<Integer, Boolean> hashMap = factory.create(size);

        for (int i = 0; i != (capacity  + 1); ++i){
            hashMap.put(i, true);
        }

        boolean capacityReached = hashMap.isCapacityReached();
        assert (capacityReached);
    }
}