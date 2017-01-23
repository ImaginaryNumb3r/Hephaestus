package infastructure.filetype.interfaces;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 27.07.2016
 */
public interface AbstractFile extends AbsoluteFile {

    //<editor-fold desc="Absolute File">
    AbsoluteFile concat (RelativeDirectory rel);

    AbsoluteFile remove (RelativeDirectory removal) throws PathsNotMatchingException;

    RelativeDirectory remove(AbsoluteFile absFile) throws PathsNotMatchingException;

    AbsoluteFile copy();
    //</editor-fold>
}
