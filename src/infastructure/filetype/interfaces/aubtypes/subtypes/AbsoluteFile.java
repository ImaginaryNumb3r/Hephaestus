package infastructure.filetype.interfaces.aubtypes.subtypes;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.filetype.interfaces.aubtypes.FilePath;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface AbsoluteFile extends AbsolutePath, FilePath {

    // Absolute Path

    RelativeFile remove(AbsoluteDirectory absDir) throws PathsNotMatchingException;

    // File Path

    AbsoluteDirectory remove(RelativeFile relFile) throws PathsNotMatchingException;

    // Absolute Path + File Path

    AbsoluteFile concat (RelativeDirectory rel);

    AbsoluteFile remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // Absolute File

    RelativeDirectory remove(AbsoluteFile absFile) throws PathsNotMatchingException;

    AbsoluteFile copy();

    boolean isSubPath(AbsoluteFile absFile);
}
