package processing.analytics.range;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Creator: Patrick
 * Created: 18.08.2017
 * Purpose:
 */
public class IntRangeTest {

    @Test
    public void testConstruction() throws Exception {
        final int minVal = 0;
        final int maxVal = 10;
        IntRange range = new IntRange(minVal, maxVal);
        final int expectedSize = maxVal + 1;

        assert range.size() == expectedSize;

        int i = minVal;
        for (Integer cur : range) {
            assert cur == i++;
        }
    }
}