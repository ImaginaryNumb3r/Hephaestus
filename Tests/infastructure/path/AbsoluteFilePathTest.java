package infastructure.path;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.empty.EmptyRelativeFilePath;
import infastructure.path.exceptions.EmptyPathException;
import infastructure.path.exceptions.PathsNotMatchingException;
import org.junit.Test;

/**
 * @author Patrick
 * @created 07.07.2016
 */
public class AbsoluteFilePathTest {

    @Test
    public void testFilePathToString() throws Exception {
        final String expected = "D:\\Files\\Hephaestus\\Tests\\Files\\File";
        final int expectedSize = 6;

        AbsoluteFile absFile= PathFactory.makeAbsoluteFile(expected);

        String anObject = absFile.toString();
        assert expected.equals(anObject);
    }

    @Test
    public void testConcatFileFile() throws Exception {
        final String absPath = "D:\\Files\\Hephaestus\\File.txt";
        final String relPath = "Tests\\Files";
        final String expected = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";
        final int expectedSize = 6;

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absPath);
        RelativeDirectory add = PathFactory.makeRelativeDirectory(relPath);

        AbsoluteFile concat = base.concat(add);

        assert expectedSize == concat.length();
        assert expected.equals(concat.toString());
    }

    @Test
    public void testAbsFileRemoveRelDir() throws Exception {
        final String absFilePath = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";
        final String absDirPath = "Tests\\Files";
        final String expected = "D:\\Files\\Hephaestus\\File.txt";
        final int expectedSize = 4;

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absFilePath);
        RelativeDirectory removal = PathFactory.makeRelativeDirectory(absDirPath);

        AbsoluteFile removed = base.remove(removal);

        assert expectedSize == removed.length();
        assert expected.equals(removed.toString());
    }

    @Test
    public void testRemoveFileDir() throws Exception {
        final String absFilePath = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";
        final String absDirPath = "D:\\Files\\Hephaestus";
        final String expected = "Tests\\Files\\File.txt";
        final int expectedSize = 3;

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absFilePath);
        AbsoluteDirectory removal = PathFactory.makeAbsoluteDirectory(absDirPath);

        RelativeFile removed = base.remove(removal);

        assert expectedSize == removed.length();
        assert expected.equals(removed.toString());
    }

    @Test
    public void testAbsFileRemoveAbsFile() throws Exception {
        final String absFilePath = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";
        final String absDirPath = "D:\\Files\\Hephaestus\\File.txt";
        final String expected = "Tests\\Files";
        final int expectedSize = 2;

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absFilePath);
        AbsoluteFile removal = PathFactory.makeAbsoluteFile(absDirPath);

        RelativeDirectory removed = base.remove(removal);

        assert expectedSize == removed.length();
        assert expected.equals(removed.toString());
    }

    @Test
    public void testAbsFileRemoveAbsFileWrong() throws Exception {
        final String absFilePath = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";
        final String absDirPath = "D:\\Files\\Hephaestus\\Wrong.txt";

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absFilePath);
        AbsoluteFile removal = PathFactory.makeAbsoluteFile(absDirPath);

        boolean thrown;
        try {
            base.remove(removal);
            thrown = false;
        } catch (PathsNotMatchingException ex){
            thrown = true;
        }

        assert thrown;
    }

    @Test
    public void testWrongRemoveFileDir() throws Exception {
        final String absFilePath = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";
        final String absDirPath = "D:\\Hephaestus\\File.txt";

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absFilePath);
        AbsoluteFile removal = PathFactory.makeAbsoluteFile(absDirPath);

        boolean throwsException;
        try {
            base.remove(removal);
            throwsException = false;
        } catch (PathsNotMatchingException ex){
            throwsException = true;
        }

        assert throwsException;
    }

    @Test
    public void testAbsFileRemoveRelFile() throws Exception {
        final String absFilePath = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";
        final String absDirPath = "Tests\\Files\\File.txt";
        final String expected = "D:\\Files\\Hephaestus";
        final int expectedSize = 3;

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absFilePath);
        RelativeFile removal = PathFactory.makeRelativeFile(absDirPath);

        AbsoluteDirectory removed = base.remove(removal);

        assert expectedSize == removed.length();
        assert expected.equals(removed.toString());
    }

    @Test
    public void testAbsFileRemoveEmptyFile() throws Exception {
        final String absFilePath = "D:\\Files\\Hephaestus\\Tests\\Files\\File.txt";

        AbsoluteFile base = PathFactory.makeAbsoluteFile(absFilePath);
        EmptyRelativeFilePath removal = new EmptyRelativeFilePath("File.txt");
        // TODO: Should work as long as the file names are matching

        boolean throwsException = false;
        try {
            AbsoluteDirectory removed = base.remove(removal);
        } catch (EmptyPathException ex){
            throwsException = true;
        }

        assert throwsException;
    }

    @Test
    public void testToString() throws Exception {
        final String path = "D:\\KW SDK\\Mods\\One Vision\\Workspace\\Data\\Includes Config.txt";
        AbsoluteFile absFile = PathFactory.makeAbsoluteFile(path);

        boolean equals = path.equals(absFile.toString());
        assert equals;
    }
}