package infastructure.datastructure.tree;

import infastructure.datastructure.tree.container.ListCompareResult;
import infastructure.filetype.HDirectory;
import infastructure.filetype.HFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.path.PathFactory;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @created 30.07.2016
 */
public class AbstractTreeNodeTest {

    @Test
    public void testCompare() throws Exception {
        String rootDir = "D:\\Projekte\\Hephaestus\\Tests\\infastructure\\datastructure\\tree\\Files";
        AbsoluteDirectory absDir = PathFactory.makeAbsoluteDirectory(rootDir);
        HDirectory directory = new HDirectory(absDir);

        AbsoluteDirectoryTreeNode treeNode = new AbsoluteDirectoryTreeNode(absDir);
        ListCompareResult compare = treeNode.compare();

        assert compare.getNewFiles().size() == 3;

        List<HFile> files = directory.getFiles();
        Optional<HFile> hFile = files.stream().filter(file -> file.getName().equals("deleted.txt")).findFirst();

        assert hFile.isPresent();

        HFile fileDelete = hFile.get();
        HFile fileCreate = new HFile(PathFactory.makeAbsoluteFile(rootDir + "\\" + "new.txt"));

        try {
            fileDelete.delete();
            fileCreate.create();

            ListCompareResult newCompare = treeNode.compare();

            // TODO: Persistence Facade - Add: newCompare.getNewFiles()
            // TODO: Persistence Facade - Remove: newCompare.getDeletedFiles()
            // TODO: Persistence Facade - Add: newCompare.getUnchangedFiles()

            System.out.println();

        } finally {
            fileDelete.create();
            fileCreate.delete();
        }


    }
}