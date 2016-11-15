package infastructure.filetype.interfaces;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.PathsNotMatchingException;

import java.io.FileFilter;
import java.util.List;

/**
 * @author Patrick
 * @created 24.07.2016
 */
public interface AbstractDirectory extends AbsoluteDirectory {

    List<? extends AbstractFile> getFiles(FileFilter filter);

    List<? extends AbstractFile> getFiles();

    List<? extends AbstractDirectory> getDirectories(FileFilter filter);

    List<? extends AbstractDirectory> getDirectories();

    //<editor-fold desc="Absolute directory">
    AbsoluteDirectory concat (RelativeDirectory rel);

    AbsoluteDirectory remove (RelativeDirectory removal) throws PathsNotMatchingException;

    AbsoluteDirectory copy();
    //</editor-fold>
}
