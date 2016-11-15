package infastructure.filetype.interfaces.aubtypes.subtypes;

import infastructure.filetype.interfaces.aubtypes.FilePath;
import infastructure.filetype.interfaces.aubtypes.RelativePath;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface RelativeFile extends FilePath, RelativePath {

    // File Path

    RelativeDirectory remove(RelativeFile rel) throws PathsNotMatchingException;

    // File Path + Relative Path

    RelativeFile concat (RelativeDirectory rel);

    RelativeFile remove (RelativeDirectory removal) throws PathsNotMatchingException;

    RelativeFile copy();
}
