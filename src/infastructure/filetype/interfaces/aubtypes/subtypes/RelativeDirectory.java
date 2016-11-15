package infastructure.filetype.interfaces.aubtypes.subtypes;

import infastructure.filetype.interfaces.aubtypes.DirectoryPath;
import infastructure.filetype.interfaces.aubtypes.RelativePath;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface RelativeDirectory extends RelativePath, DirectoryPath {

    // Directory Path

    RelativeFile concat(RelativeFile relFile);

    // Relative Path + Directory Path

    RelativeDirectory concat (RelativeDirectory rel);

    RelativeDirectory remove (RelativeDirectory removal) throws PathsNotMatchingException;

    RelativeDirectory copy();
}
