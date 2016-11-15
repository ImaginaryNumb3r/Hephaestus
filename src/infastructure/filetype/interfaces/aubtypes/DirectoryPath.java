package infastructure.filetype.interfaces.aubtypes;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface DirectoryPath extends Path {

    // Path

    DirectoryPath concat (RelativeDirectory rel);

    DirectoryPath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // Directory Path

    FilePath concat(RelativeFile relFile);

    DirectoryPath copy();
}
