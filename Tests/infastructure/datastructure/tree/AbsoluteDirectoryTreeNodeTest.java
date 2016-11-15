package infastructure.datastructure.tree;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.PathFactory;
import org.junit.Test;

/**
 * @author Patrick
 * @created 30.07.2016
 */
public class AbsoluteDirectoryTreeNodeTest {

    @Test
    public void testCreation() throws Exception {
        final String absDir = "C:\\Users\\Test";
        AbsoluteDirectory absoluteDirectory = PathFactory.makeAbsoluteDirectory(absDir);

        AbsoluteDirectoryTreeNode absTreeNode = new AbsoluteDirectoryTreeNode(absoluteDirectory);

        assert absDir.equals(absTreeNode.getAbsolutePath());
        assert absDir.equals(absTreeNode.toString());
        assert absDir.equals(absTreeNode.getName());
    }

    @Test
    public void testWrongAdd() throws Exception {
        final String rootDir = "C:\\Users\\Test";
        final String subDir = "C:\\Users\\Test";

        RelativeDirectory relDir = PathFactory.makeRelativeDirectory(subDir);
        AbsoluteDirectory absDir = PathFactory.makeAbsoluteDirectory(rootDir);

        AbsoluteDirectoryTreeNode absTreeNode = new AbsoluteDirectoryTreeNode(absDir);
        boolean canAdd = absTreeNode.addNode(relDir);

        assert !canAdd;
    }

    /**
     * Adds 2 nodes by hand and then creates another node by calling getOrAdd
     * @throws Exception
     */
    @Test
    public void testGetOrAdd() throws Exception {
        final String absDir = "C:\\Users\\Test";
        final String subDir = "SubDir";
        final String subDir2 = "SubDir2";
        final String subDirNew = "SubDirNew";
        final int childCount = 3;

        RelativeDirectory relDir = PathFactory.makeRelativeDirectory(subDir);
        RelativeDirectory relDir2 = PathFactory.makeRelativeDirectory(subDir2);

        AbsoluteDirectory absoluteDirectory = PathFactory.makeAbsoluteDirectory(absDir);

        AbsoluteDirectoryTreeNode absTreeNode = new AbsoluteDirectoryTreeNode(absoluteDirectory);

        absTreeNode.addNode(relDir);
        absTreeNode.addNode(relDir2);

        AbstractTreeNode node1 = absTreeNode.getOrAdd(relDir);
        AbstractTreeNode node2 = absTreeNode.getOrAdd(relDir2);
        AbstractTreeNode nodeNew = absTreeNode.getOrAdd(subDirNew);

        assert absTreeNode.childrenCount() == childCount;
        assert node1.getName().equals(subDir);
        assert node2.getName().equals(subDir2);
        assert nodeNew.getName().equals(subDirNew);
    }

    @Test
    public void testAdd() throws Exception {
        final String absDir = "C:\\Users\\Test";
        final String subDir = "SubDir";
        final String subDir2 = "SubDir2";
        RelativeDirectory relDir = PathFactory.makeRelativeDirectory(subDir);
        RelativeDirectory relDir2 = PathFactory.makeRelativeDirectory(subDir2);

        AbsoluteDirectory absoluteDirectory = PathFactory.makeAbsoluteDirectory(absDir);

        AbsoluteDirectoryTreeNode absTreeNode = new AbsoluteDirectoryTreeNode(absoluteDirectory);
        RelativeDirectoryTreeNode relTreeNode = new RelativeDirectoryTreeNode(absTreeNode, relDir);
        RelativeDirectoryTreeNode relTreeNode2 = new RelativeDirectoryTreeNode(absTreeNode, relDir2);

        absTreeNode.addNode(relTreeNode);
        absTreeNode.addNode(relTreeNode2);

        boolean foundSubDir1 = false;
        boolean foundSubDir2 = false;
        for (AbstractTreeNode childNode : absTreeNode.getChildNodes()) {

            if (subDir.equals(childNode.getName())){
                foundSubDir1 = true;
            }

            if (subDir2.equals(childNode.getName())){
                foundSubDir2 = true;
            }
        }

        assert foundSubDir1 && foundSubDir2;
    }
}