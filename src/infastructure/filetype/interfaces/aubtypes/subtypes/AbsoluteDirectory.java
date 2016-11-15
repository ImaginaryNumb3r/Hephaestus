package infastructure.filetype.interfaces.aubtypes.subtypes;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.filetype.interfaces.aubtypes.DirectoryPath;
import infastructure.filetype.interfaces.aubtypes.FilePath;
import infastructure.filetype.interfaces.aubtypes.RelativePath;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface AbsoluteDirectory extends AbsolutePath, DirectoryPath {

    // Absolute Path

    RelativeDirectory remove(AbsoluteDirectory absDir) throws PathsNotMatchingException;

    // Directory Path

    AbsoluteFile concat(RelativeFile relFile);

    // Absolute Path + Directory Path

    AbsoluteDirectory concat (RelativeDirectory rel);
    AbsoluteDirectory add (String rel); // TOOD: Remove

    AbsoluteDirectory remove (RelativeDirectory removal) throws PathsNotMatchingException;

    AbsoluteDirectory copy();

    // TODO: Move down

}
