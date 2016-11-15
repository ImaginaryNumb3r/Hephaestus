package infastructure.path;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.exceptions.PathsNotMatchingException;
import org.junit.Test;

/**
 * @author Patrick
 * @created 07.07.2016
 */
public class AbsoluteDirectoryPathTest {
    @Test
    public void testParentPath() throws Exception {
        final String absDir = "D:\\Hephaestus\\Tests\\Files";
        final String parentDir = "D:\\Hephaestus\\Tests";
        final int expectedSize = 3;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(absDir);

        AbsoluteDirectory parentPath = base.getParentPath();
        String parentPathString = parentPath.toString();

        assert parentPath.length() == expectedSize;
        assert parentDir.equals(parentPathString);
    }

    @Test
    public void testParent() throws Exception {
        final String absDir = "D:\\Hephaestus\\Tests\\Files";
        final String parentDir = "Tests";
        final int expectedSize = 1;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(absDir);

        RelativeDirectory parentPath = base.getParent();
        String parentPathString = parentPath.toString();

        assert parentPath.length() == expectedSize;
        assert parentDir.equals(parentPathString);
    }

    // ====================
    //  Path Arithmetic
    // ====================

    @Test
    public void testAbsDirConcatRelDir() throws Exception {
        final String dir1 = "D:\\Hephaestus\\Tests\\Files";
        final String dir2 = "SubFiles\\AnotherDirectory";
        final int expectedSize = 6;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(dir1);
        RelativeDirectory addition = PathFactory.makeRelativeDirectory(dir2);
        final String expected = dir1 + "\\" + dir2;

        AbsoluteDirectory concat = base.concat(addition);
        String string = concat.toString();

        assert (expected.equals(string));
        int length = concat.length();
        assert (expectedSize == length);
    }

    @Test
    public void testAbsDirConcatEmpty() throws Exception {
        final String dir1 = "D:\\Hephaestus\\Tests\\Files";
        final String dir2 = "";
        final int expectedSize = 4;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(dir1);
        RelativeDirectory addition = PathFactory.makeRelativeDirectory(dir2);

        AbsoluteDirectory concat = base.concat(addition);
        String string = concat.toString();

        assert (dir1.equals(string));
        int length = concat.length();
        assert (expectedSize == length);
    }

    @Test
    public void testWrongRemoveAbsAbs() throws Exception {
        final String dir1 = "D:\\Hephaestus\\Tests\\Files";
        final String dir2 = "D:";
        final String expected = "Hephaestus\\Tests\\Files";
        final int expectedSize = 3;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(dir1);
        AbsoluteDirectory removal = PathFactory.makeAbsoluteDirectory(dir2);

        RelativeDirectory removed = base.remove(removal);

        assert expectedSize == removed.length();
        assert expected.equals(removed.toString());
    }

    @Test
    public void testWrongRemoveAbsAbs2() throws Exception {
        final String dir1 = "D:\\Hephaestus\\Tests\\Files";
        final String dir2 = "D:\\Hephaestus2";

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(dir1);
        AbsoluteDirectory removal = PathFactory.makeAbsoluteDirectory(dir2);

        boolean thrownException;
        try {
            RelativeDirectory removed = base.remove(removal);
            thrownException = false;
        } catch (PathsNotMatchingException ex){
            thrownException = true;
        }

        assert thrownException;
    }

    @Test
    public void testAbsDirRemoveAbsDir() throws Exception {
        final String longPath = "D:\\Files\\Hephaestus\\Tests\\Files";
        final String shortPath = "D:\\Files";
        final String expected = "Hephaestus\\Tests\\Files";
        final int expectedSize = 3;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(longPath);
        AbsoluteDirectory removal = PathFactory.makeAbsoluteDirectory(shortPath);

        RelativeDirectory removed = base.remove(removal);

        assert expectedSize == removed.length();
        assert expected.equals(removed.toString());
    }

    @Test
    public void testAbsDirRemoveAbsDirFull() throws Exception {
        final String absDir = "D:\\Files\\Hephaestus\\Tests\\Files";
        final String expected = "";
        final int expectedSize = 0;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(absDir);
        AbsoluteDirectory removal = PathFactory.makeAbsoluteDirectory(absDir);

        RelativeDirectory removed = base.remove(removal);

        AbsoluteDirectory unchanged = base.remove(removed);

        assert expectedSize == removed.length();
        assert expected.equals(removed.toString());

        assert base.toString().equals(unchanged.toString());
    }

    @Test
    public void testAbsDirConcatRelFile() throws Exception {
        final String absDir = "D:\\Files\\Hephaestus\\Tests";
        final String relFile = "Files\\Subdir\\File.txt";
        final String expected = "D:\\Files\\Hephaestus\\Tests\\Files\\Subdir\\File.txt";
        final int expectedSize = 7;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(absDir);
        RelativeFile addition = PathFactory.makeRelativeFile(relFile);

        AbsoluteFile added = base.concat(addition);

        assert expectedSize == added.length();
        assert expected.equals(added.toString());
    }

/*    @Test
    public void testAbsDirAddFile() throws Exception {
        final String absDir = "D:\\Files\\Hephaestus\\Tests";
        final String relFile = "File.txt";
        final String expected = "D:\\Files\\Hephaestus\\Tests\\File.txt";
        final int expectedSize = 5;

        AbsoluteDirectory base = PathFactory.makeAbsoluteDirectory(absDir);

        // Issue here is the problem with empty file paths. How should we treat that issue?
        AbsoluteFile added = base.addFile(relFile);

        assert expectedSize == added.length();
        assert expected.equals(added.toString());
    }*/
}