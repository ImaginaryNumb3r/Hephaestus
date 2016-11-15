package infastructure.filetype;


import org.junit.Test;

import java.util.List;

/**
 * @author Patrick
 * @created 21.06.2016
 */
public class HDirectoryTest {

    @Test
    public void testConstructor() throws Exception {
        String testDirectory = "D:\\Projekte\\Hephaestus\\Tests\\Files";
        HDirectory directory = new HDirectory(testDirectory);

        assert(directory.isDirectory());
    }

    @Test
    public void testGetDirectories() throws Exception {
        String testDirectory = "D:\\Projekte\\Hephaestus\\Tests\\Files";
        HDirectory directory = new HDirectory(testDirectory);

        List<HDirectory> directories = directory.getDirectories();
        assert(directories.size() == 3);
    }

    @Test
    public void testGetFiles() throws Exception {
        String testDirectory = "D:\\Projekte\\Hephaestus\\Tests\\Files";
        HDirectory directory = new HDirectory(testDirectory);

        List<HFile> files = directory.getFiles();
        assert(files.size() == 4);
    }

    @Test
    public void testGetName() throws Exception {
        String testDirectory = "D:\\Projekte\\Hephaestus\\Tests\\Files";
        final String result = "Files";
        HDirectory directory = new HDirectory(testDirectory);

        String name = directory.getName();
        assert(result.equals(name));
    }
}