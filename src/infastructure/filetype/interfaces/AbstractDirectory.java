package infastructure.filetype.interfaces;

import infastructure.filetype.HDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.PathsNotMatchingException;

import java.io.FileFilter;
import java.util.List;
import java.util.Optional;

/**
 * @author Patrick
 * @created 24.07.2016
 */
public interface AbstractDirectory extends AbsoluteDirectory {

    Optional<? extends List<? extends AbstractFile>> getFiles(FileFilter filter);

    Optional<? extends List<? extends AbstractFile>> getFiles();

    Optional<? extends List<? extends AbstractDirectory>> getDirectories(FileFilter filter);

    Optional<? extends List<? extends AbstractDirectory>> getDirectories();

    //<editor-fold desc="Absolute directory">
    AbsoluteDirectory concat (RelativeDirectory rel);

    AbsoluteDirectory remove (RelativeDirectory removal) throws PathsNotMatchingException;

    AbsoluteDirectory copy();
    //</editor-fold>
}
