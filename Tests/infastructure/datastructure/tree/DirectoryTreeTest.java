package infastructure.datastructure.tree;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.path.PathFactory;
import org.junit.Test;

/**
 * @author Patrick
 * @created 24.07.2016
 */
public class DirectoryTreeTest {

    @Test
    public void testName() throws Exception {
        final String path = "D:";
        AbsoluteDirectory absDir = PathFactory.makeAbsoluteDirectory(path);
        DirectoryTree tree = new DirectoryTree(absDir);
        TreeNode root = tree.getRoot();

        String string = root.toString();
        assert path.equals(string);
    }

    @Test
    public void testAbsolutePath() throws Exception {
        final String path = "D:";
        AbsoluteDirectory absDir = PathFactory.makeAbsoluteDirectory(path);
        DirectoryTree tree = new DirectoryTree(absDir);
        TreeNode root = tree.getRoot();

        String string = root.getAbsolutePath();
        assert path.equals(string);
    }
}