package infastructure.filetype;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.path.AbsoluteDirectoryPath;
import infastructure.path.AbsoluteFilePath;
import infastructure.path.PathFactory;
import infastructure.path.Paths;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Patrick
 * @created 02.07.2016
 */
public class PathTest {
    private final String _testFilePath = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";
    private final String _wrongPath = "D:\\Projekte\\Hephaestus\\Tests\\Files\\WrongEntry";

    @Test
    public void testGetName() throws Exception {
        final String expected = "TestFile.txt";
        Path path = PathFactory.makeAbsoluteFile(_testFilePath);

        assert(expected.equals(path.getName()));
    }

    @Test
    public void testIsAbsolute() throws Exception {
        Path path = PathFactory.makeAbsoluteFile(_testFilePath);
        boolean isAbsolute = path instanceof AbsolutePath; // file.isAbsolutePath();

        assert(isAbsolute);
    }

    @Test
    public void testExists() throws Exception {
        AbsolutePath path = PathFactory.makeAbsoluteFile(_testFilePath);
        boolean exists = path.exists();

        assert (exists);
    }

    @Test
    public void testExistsNot() throws Exception {
        AbsolutePath path = PathFactory.makeAbsoluteDirectory(_wrongPath);
        File jFile = new File(_wrongPath);

        boolean exists1 = jFile.exists();
        boolean consistent = path.exists() == exists1;
        assert consistent;
    }

    @Test
    public void testNewExists() throws Exception {
        String newPath = "D:\\Projekte\\Hephaestus\\Tests\\Files\\temp.txt";
        AbsolutePath path = PathFactory.makeAbsoluteFile(newPath);
        File file = new File(newPath);

        boolean exists;
        try {
            boolean newFile = file.createNewFile();
            exists = path.exists();
        } catch (IOException ex){
            exists = false;
        } finally {
            boolean delete = file.delete();
        }

        boolean deleted = false;
        // Now checkNulls exists() again, after file has been deleted
        if (exists){
            deleted  = !path.exists();
        }

        assert (deleted);
    }

    @Test
    public void testEquals() throws Exception {
        boolean equals;
        final String destination = "D:\\Projekte\\Hephaestus\\Tests\\Files\\dest";
        AbsolutePath absolutePath = PathFactory.makeAbsoluteFile(destination);
        Path path = PathFactory.makeAbsoluteDirectory(destination);
        AbsolutePath locatable = PathFactory.makeAbsoluteFile(destination);

         equals = absolutePath.equals(locatable);
        //noinspection EqualsBetweenInconvertibleTypes
        equals &= path.equals(absolutePath);

        // FilePath is not the same as DirectoryPath
        assert(!equals);
    }
}