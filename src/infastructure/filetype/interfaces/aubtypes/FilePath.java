package infastructure.filetype.interfaces.aubtypes;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.FileNode;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface FilePath extends Path {

    // Path

    FilePath concat (RelativeDirectory rel);

    FilePath remove (RelativeDirectory removal) throws PathsNotMatchingException;

    // File Path

    DirectoryPath remove(RelativeFile relFile) throws PathsNotMatchingException;

    String getPostfix();

    String getPrefix();

    FileNode fileNode();

    FilePath copy();

    boolean isSubPath(RelativeFile filePath);
}
