package infastructure.datastructure.tree;

import infastructure.datastructure.tree.container.ListCompareResult;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Patrick
 * @created 27.07.2016
 */
public class ListComparerTest {

    @Test
    public void testComparing() throws Exception {
        ListComparer comparer = new ListComparer();
        String[] newFiles = { "newFile", "unchangedFile", "changedFile" };
        String[] oldFiles = { "removedFile", "unchangedFile", "changedFile" };

        //noinspection unchecked - we know the type for sure
        ListCompareResult<String> compare = comparer.compare(Arrays.asList(oldFiles), Arrays.asList(newFiles));

        assert compare.getNewFiles().size() == 1;
        assert compare.getUnchangedFiles().size() == 2;
        assert compare.getRemovedFiles().size() == 1;
    }
}