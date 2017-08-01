package filesystem.path.interfaces.concrete;

import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.DirectoryNode;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @since 14.06.2017
 */
public class AbstractDirectory implements AbsoluteDirectory {
    @Override
    public int length() {
        return 0;
    }

    @Override
    public RelativeDirectory remove(AbsoluteDirectory absDir) throws PathsNotMatchingException {
        return null;
    }

    @Override
    public String getAbsolutePath() {
        return null;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public RelativeDirectory getParent() {
        return null;
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        return null;
    }

    @Override
    public AbsoluteFile concat(RelativeFile relFile) {
        return null;
    }

    @Override
    public AbsoluteDirectory concat(RelativeDirectory rel) {
        return null;
    }

    @Override
    public AbsoluteDirectory add(String rel) {
        return null;
    }

    @Override
    public AbsoluteDirectory remove(RelativeDirectory removal) throws PathsNotMatchingException {
        return null;
    }

    @Override
    public boolean equals(Path path) {
        return false;
    }

    @Override
    public boolean equals(String path) {
        return false;
    }

    @Override
    public DirectoryNode headNode() {
        return null;
    }

    @Override
    public DirectoryNode tailNode() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public AbsoluteDirectory copy() {
        return null;
    }

    @Override
    public boolean isSubPath(RelativeDirectory relDir) {
        return false;
    }

    @Override
    public boolean isSubPath(AbsoluteDirectory absDir) {
        return false;
    }
}
