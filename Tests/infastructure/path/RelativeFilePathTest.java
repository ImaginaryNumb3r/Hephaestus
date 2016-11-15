package infastructure.path;

import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.exceptions.PathsNotMatchingException;
import org.junit.Test;

/**
 * @author Patrick
 * @created 06.07.2016
 */
public class RelativeFilePathTest {

    @Test
    public void testConcat() throws Exception {
        final String directoryPath = "Hephaestus\\Tests\\Files";
        final String filePath = "SubFiles\\File.txt";
        final String expected = directoryPath + "\\" + filePath;
        final int expectedSize = 5;

        RelativeFile base = PathFactory.makeRelativeFile(filePath);
        RelativeDirectory add = PathFactory.makeRelativeDirectory(directoryPath);

        RelativeFile concat = base.concat(add);
        String string = concat.toString();

        assert (expected.equals(string));
        int length = concat.length();
        assert (expectedSize == length);
    }

    @Test
    public void testRemove() throws Exception {
        final String expected = "Hephaestus\\File.txt";
        final String filePath = "Hephaestus\\Tests\\Files\\File.txt";
        final String directoryPath = "Tests\\Files";
        final int expectedSize = 2;

        RelativeFile base = PathFactory.makeRelativeFile(filePath);
        RelativeDirectory removal = PathFactory.makeRelativeDirectory(directoryPath);

        RelativeFile removed = base.remove(removal);
        String string = removed.toString();

        assert (expected.equals(string));
        int length = removed.length();
        assert (expectedSize == length);
    }

    @Test
    public void testWrongRemove() throws Exception {
        final String expected = "Hephaestus\\File.txt";
        final String filePath = "Hephaestus\\Tests\\Files\\File.txt";
        final String directoryPath = "Tests\\Wrong";

        RelativeFile base = PathFactory.makeRelativeFile(filePath);
        RelativeDirectory removal = PathFactory.makeRelativeDirectory(directoryPath);


        boolean throwsException;
        try {
            base.remove(removal);
            throwsException = false;
        } catch (PathsNotMatchingException ex){
            throwsException = true;
        }

        assert (true);
    }

    @Test
    public void testRelRelRemoveFileFile() throws Exception {
        final String basePath = "Hephaestus\\Tests\\SubFiles\\File.txt";
        final String removalPath = "SubFiles\\File.txt";
        final String expected = "Hephaestus\\Tests";
        final int expectedSize = 2;

        RelativeFile base = PathFactory.makeRelativeFile(basePath);
        RelativeFile remove = PathFactory.makeRelativeFile(removalPath);

        RelativeDirectory removed = base.remove(remove);
        String string = removed.toString();

        boolean equals = expected.equals(string);
        assert equals;
        int length = removed.length();
        assert (expectedSize == length);
    }

    @Test
    public void testMakeEmptyRelFile() throws Exception {
        final String fileName = "file.txt";

        RelativeFile relativeFile = PathFactory.makeRelativeFile(fileName);

        assert relativeFile != null;
        assert fileName.equals(relativeFile.toString());
    }
}