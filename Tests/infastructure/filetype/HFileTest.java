package infastructure.filetype;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.PathFactory;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Patrick
 * @created 25.06.2016
 */
public class HFileTest {

    @Test
    public void testGetParent() throws Exception {
        final String dirPath = "D:\\Projekte\\Hephaestus\\Tests\\Files";
        final String fileName = "TestFile.txt";
        final String expected = "Files";
        File file = new File(dirPath + "\\" + fileName);
        HFile hFile = new HFile(file);

        RelativeDirectory parentPath = hFile.getParent();
        assert(expected.equals(parentPath.toString()));
    }

    @Test
    public void testGetParentPath() throws Exception {
        final String dirPath = "D:\\Projekte\\Hephaestus\\Tests\\Files";
        final String fileName = "TestFile.txt";
        File file = new File(dirPath + "\\" + fileName);
        HFile hFile = new HFile(file);

        AbsoluteDirectory parentPath = hFile.getParentPath();
        assert(dirPath.equals(parentPath.toString()));
    }

    @Test
    public void testMakeFromFile() throws Exception {
        File file = new File("D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt");
        String result = "TestFile.txt";
        HFile hFile = new HFile(file);

        String name = hFile.getName();
        assert(result.equals(name));
    }

    @Test
    public void testGetName() throws Exception {
        String result = "TestFile.txt";
        String path = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";
        HFile file = new HFile(path);

        String name = file.getName();
        assert(result.equals(name));
    }

    @Test
    public void testPostfix() throws Exception {
        String result = "txt";
        String path = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";
        HFile file = new HFile(path);

        String postfix = file.getPostfix();
        assert(result.equals(postfix));
    }

    @Test
    public void testPrefix() throws Exception {
        String result = "TestFile";
        String path = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";
        HFile file = new HFile(path);

        String prefix = file.getPrefix();
        assert(result.equals(prefix));
    }

    @Test
    public void testToString() throws Exception {
        final String path = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";
        HFile hFile = new HFile(path);

        String string = hFile.getPath().toString();
        String absolutePath = hFile.getAbsolutePath();
        boolean equals = path.equals(hFile.toString());
        assert equals;
    }

    @Test
    public void testToString2() throws Exception {
        final String path = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";
        AbsoluteFile filePath = PathFactory.makeAbsoluteFile(path);
        HFile hFile = new HFile(filePath);

        boolean equals = path.equals(hFile.toString());
        assert equals;
    }
}