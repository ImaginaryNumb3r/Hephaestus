package processing.math;

import org.junit.Test;

/**
 * Creator: Patrick
 * Created: 06.08.2017
 * Purpose:
 */
public class DoubleMathTest {

    @Test
    public void testLcm() throws Exception {
        assert DoubleMath.lcm(17, 3) == 51;
        assert DoubleMath.lcm(12, 4)== 12;
        assert DoubleMath.lcm(182, 154) == 2002;
        assert DoubleMath.lcm(25, 88) == 2200;
        assert DoubleMath.lcm(24, 9) == 72;
    }
}