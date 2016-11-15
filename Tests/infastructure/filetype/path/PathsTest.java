package infastructure.filetype.path;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.*;
import infastructure.path.enums.PathType;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public class PathsTest extends Paths {

    // ====================
    //    Factory Tests
    // ====================

    @Test
    public void testMakeAbsoluteDirectory() throws Exception {
        final String absDirPath = "D:\\Projekte\\Hephaestus\\Tests\\Files";

        AbsoluteDirectory path = PathFactory.makeAbsoluteDirectory(absDirPath);

        assert(path != null);
    }

    @Test
    public void testMakeAbsoluteFile() throws Exception {
        final String absDirPath = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";

        AbsoluteFile path = PathFactory.makeAbsoluteFile(absDirPath);

        assert(path != null);
    }

    @Test
    public void testMakeWrongAbsoluteFile() throws Exception {
        final String absDirPath = "Hephaestus\\Tests\\Files\\TestFile.txt";

        AbsoluteFile path = PathFactory.makeAbsoluteFile(absDirPath);

        assert(path.isEmpty());
    }

    @Test
    public void testMakeWrongRelativeDirectory() throws Exception {
        final String relDirPath = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";

        RelativeDirectory path = PathFactory.makeRelativeDirectory(relDirPath);

        assert(path.isEmpty());
    }

    @Test
    public void testMakeRelativeDirectory() throws Exception {
        final String relDirPath = "Hephaestus\\Tests\\Files";

        RelativeDirectory path = PathFactory.makeRelativeDirectory(relDirPath);

        assert(!path.isEmpty());
    }

    @Test
    public void testMakeRelativeFile() throws Exception {
        final String absDirPath = "Hephaestus\\Tests\\Files\\TestFile.txt";

        RelativeFile path = PathFactory.makeRelativeFile(absDirPath);
        assert(!path.isEmpty());
    }

    @Test
    public void testMakeWrongEmptyPath() throws Exception {
        final String absDirPath = "";

        boolean throwsException = false;
        try {
            RelativeFile path = PathFactory.makeRelativeFile(absDirPath);
        } catch (IllegalArgumentException ex){
            throwsException = true;
        }

        assert throwsException;
    }

    @Test
    public void testMakeWrongEmptyPath2() throws Exception {
        final String absDirPath = "a\\\\a";

        boolean throwsException = false;
        try {
            RelativeFile path = PathFactory.makeRelativeFile(absDirPath);
        } catch (IllegalArgumentException ex){
            throwsException = true;
        }

        assert throwsException;
    }

    // ====================
    //    Other Tests
    // ====================

    @Test
    public void testPathType() throws Exception {
        String path = Test.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = URLDecoder.decode(path, "UTF-8");

        final PathType expected = PathType.ABSOLUTE_FILE;
        PathType result = getPathType(decodedPath);

        assert (result.equals(expected));

    }

    @Test
    public void testSplit() throws Exception {
        final String path = "K:\\Projekte\\Command Line Conquer\\Test\\Files\\IncludeTemplates.txt";
        final String expected = "K: Projekte Command Line Conquer Test Files IncludeTemplates.txt";
        String result = "";
        LinkedList<String> strings = splitPath(path);

        for (Iterator<String> iter = strings.iterator(); iter.hasNext(); ) {
            String cur = iter.next();

            result += cur;
            if (iter.hasNext()){
                result += ' ';
            }
        }

        assert (expected.equals(result));
    }
}