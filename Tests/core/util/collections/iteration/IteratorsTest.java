package core.util.collections.iteration;

import org.junit.jupiter.api.Test;

/**
 * @author Patrick
 * @description
 * @since 01.06.2017
 */
class IteratorsTest {
    private static final String[] STRINGS1 = {"item1", "item2", "item3"};
    private static final String[] STRINGS2 = {"item1", "item2", null};
    private static final String[] STRINGS3 = {"fail"};

    @Test
    void testEqualsSameLength() {

        assert !Iterators.equals(STRINGS1, STRINGS2);
        assert Iterators.equals(STRINGS1, STRINGS1);
        assert Iterators.equals(Iterators.empty(), Iterators.empty());
    }

    @Test
    void testEqualsDifferentLength() {
        assert !Iterators.equals(Iterators.empty(), STRINGS1);
        assert !Iterators.equals(STRINGS3, STRINGS1);
    }
}