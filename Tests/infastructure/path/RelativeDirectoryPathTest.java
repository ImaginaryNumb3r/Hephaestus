package infastructure.path;

import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.empty.EmptyRelativeDirectoryPath;
import infastructure.path.exceptions.PathsNotMatchingException;
import org.junit.Test;

/**
 * @author Patrick
 * @created 06.07.2016
 */
public class RelativeDirectoryPathTest {

    // ====================
    //  Path Arithmetic
    // ====================

    @Test
    public void testConcatRelRel() throws Exception {
        final String dir1 = "Hephaestus\\Tests\\Files";
        final String dir2 = "SubFiles\\AnotherDirectory";
        final int expectedSize = 5;

        RelativeDirectory path1 = PathFactory.makeRelativeDirectory(dir1);
        RelativeDirectory path2 = PathFactory.makeRelativeDirectory(dir2);
        final String expected = dir1 + "\\" + dir2;

        RelativeDirectory concat = path1.concat(path2);
        String string = concat.toString();

        assert (expected.equals(string));
        assert (expectedSize == concat.length());
    }

    @Test
    public void testConcatRelFile() throws Exception {
        final String relDir = "Hephaestus\\Tests\\Files";
        final String relFile = "SubFiles\\AnotherDirectory\\File.txt";
        final int expectedSize = 6;

        RelativeDirectory path1 = PathFactory.makeRelativeDirectory(relDir);
        RelativeFile result = PathFactory.makeRelativeFile(relFile);
        final String expected = relDir + "\\" + relFile;

        RelativeFile concat = path1.concat(result);
        String string = concat.toString();

        assert (expected.equals(string));
        assert (expectedSize == concat.length());
    }

    @Test
    public void testRemoveRelRel() throws Exception {
        final String dir1 = "Hephaestus\\Tests\\Files";
        final String dir2 = "Tests\\Files";
        final String expected = "Hephaestus";
        final int expectedSize = 1;

        RelativeDirectory path1 = PathFactory.makeRelativeDirectory(dir1);
        RelativeDirectory path2 = PathFactory.makeRelativeDirectory(dir2);

        RelativeDirectory concat = path1.remove(path2);
        String string = concat.toString();

        assert (expected.equals(string));
        assert (expectedSize == concat.length());
    }

    @Test
    public void testRelDirRemoveEmpty() throws Exception {
        final String dir1 = "Hephaestus\\Tests\\Files";
        final int expectedSize = 3;

        RelativeDirectory base = PathFactory.makeRelativeDirectory(dir1);
        RelativeDirectory removal = EmptyRelativeDirectoryPath.instance();

        RelativeDirectory removed = base.remove(removal);
        String string = removed.toString();

        assert (dir1.equals(string));
        assert (expectedSize == removed.length());
    }

    @Test
    public void testWrongRemoveRelRel() throws Exception {
        // Removal is too long!
        final String basePath = "Tests\\Files";
        final String removalPath = "Hephaestus\\Tests\\Files";

        RelativeDirectory base = PathFactory.makeRelativeDirectory(basePath);
        RelativeDirectory removal = PathFactory.makeRelativeDirectory(removalPath);

        boolean throwsException;
        try {
            base.remove(removal);
            throwsException = false;
        } catch (PathsNotMatchingException ex){
            throwsException = true;
        }

        assert (throwsException);
    }

}