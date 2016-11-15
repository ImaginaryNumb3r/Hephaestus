package infastructure.util;

import core.util.Strings;
import org.junit.Test;

/**
 * @author Patrick
 * @created 29.06.2016
 */
public class StringsTest {

    @Test
    public void testRemoving() throws Exception {
        final String pattern = "pattern";
        final String base = "original - pattern";
        final String result = "original - ";

        String removed = Strings.getInstance().remove(base, pattern);


        assert (removed.equals(result));
    }

    @Test
    public void testWrongRemoving() throws Exception {
        final String pattern = "wrong";
        final String base = "original - pattern";

        String removed = Strings.getInstance().remove(base, pattern);


        assert (removed.equals(base));
    }

    @Test
    public void testRepeatRemoving() throws Exception {
        final String pattern = "pattern ";
        final String base = "original pat - pattern";
        final String result = "original pat - ";

        String removed = Strings.getInstance().remove(base, pattern);


        assert (removed.equals(result));
    }
}