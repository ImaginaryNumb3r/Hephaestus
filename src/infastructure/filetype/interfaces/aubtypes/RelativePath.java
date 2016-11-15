package infastructure.filetype.interfaces.aubtypes;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface RelativePath extends Path {

    // Relative Path

    RelativePath concat (RelativeDirectory rel);

    RelativePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    String getRelativePath();

    RelativePath copy();

}
