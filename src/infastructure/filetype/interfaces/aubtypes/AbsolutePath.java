package infastructure.filetype.interfaces.aubtypes;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface AbsolutePath extends Path {

    // Path

    AbsolutePath concat (RelativeDirectory rel);

    AbsolutePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // Absolute Path

    RelativePath remove(AbsoluteDirectory absDir) throws PathsNotMatchingException;

    // Absolute Path

    String getAbsolutePath();

    boolean exists();

    AbsolutePath copy();

    // TODO:
    boolean hasParent();

    RelativeDirectory getParent();

    AbsoluteDirectory getParentPath();
}
