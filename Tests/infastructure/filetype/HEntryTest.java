package infastructure.filetype;

import infastructure.path.PathFactory;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author Patrick
 * @since 31.07.2016
 */
public class HEntryTest {


    @Test
    public void testLastModified() throws Exception {
        String dirPath = "D:\\Projekte\\Hephaestus\\Tests\\Files\\Neuer Ordner";
        HDirectory directory = new HDirectory(PathFactory.makeAbsoluteDirectory(dirPath));

        String filePath = "D:\\Projekte\\Hephaestus\\Tests\\Files\\TestFile.txt";
        HFile file = new HFile(PathFactory.makeAbsoluteFile(filePath));

        LocalDateTime fileTime = file.lastModifiedDateTime();
        LocalDateTime dirTime = directory.lastModifiedDateTime();

        boolean before = fileTime.isBefore(dirTime);

        // TODO: Maybe make it a bit better? Don't (really) worry when it fails
        assert before;
    }
}