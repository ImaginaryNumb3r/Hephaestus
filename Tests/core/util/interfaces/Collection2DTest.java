package core.util.interfaces;

import core.util.collections.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Creator: Patrick
 * Created: 19.09.2017
 * Purpose:
 */
public class Collection2DTest {

    @Test
    public void toArrayTest() throws Exception {
        // Initialize
        List<String> line1 = Arrays.asList("a", "b", "c");
        List<String> line2 = Arrays.asList("1", "2", "3");
        List<String> line3 = Arrays.asList("%", "?", "!");
        List<String> line4 = Arrays.asList("", "\"", "\\");
        List<List<String>> lists = Arrays.asList(line1, line2, line3, line4);

        // Run
        String[][] stringMatrix = Collection2D.toMatrix(lists, (i1, i2) -> new String[i1][i2], String[]::new);

        // Test
        int i = 0;
        for (String[] line : stringMatrix) {
            // Compare generated matrix with initial lines (line1 to line4)
            assert Arrays.equals(line, Lists.toArray(lists.get(i++), String[]::new));
        }
    }
}