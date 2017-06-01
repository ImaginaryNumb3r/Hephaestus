package infastructure.path;

import core.exception.NoImplementationException;
import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.empty.EmptyRelativeDirectoryPath;
import infastructure.path.exceptions.NoParentException;
import infastructure.path.exceptions.PathsNotMatchingException;
import infastructure.path.interfaces.PathSupplier;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public class AbsoluteDirectoryPath extends AbsolutePathImpl<AbsoluteDirectoryPath> implements AbsoluteDirectory {
    private DirectoryPathImpl _directoryPath;

    /**
     * Protected Constructor, use PathFactory for construction
     * @param head node, start of the path
     * @param tail node, end of the path
     * @param nodeCount amount of nodes in the path
     */
    public AbsoluteDirectoryPath(DirectoryNode head,DirectoryNode tail, int nodeCount){
        super(head, tail, nodeCount);
        _directoryPath = new DirectoryPathImpl();
    }


    // ====================
    //   Absolute Directory
    // ====================

    @Override // Just call from _directoryPath.concat
    public AbsoluteFile concat(RelativeFile relFile) {
        return _directoryPath.concat(this, relFile, AbsoluteFilePath::new);
    }

    @Override
    public AbsoluteDirectory concat(RelativeDirectory relDir) {
        PathSupplier<AbsoluteDirectoryPath> constructor = (head, tail, file, length)
                -> new AbsoluteDirectoryPath(head, tail, length);

        return _directoryPath.concat(this, relDir, constructor);
    }

    @Override
    public AbsoluteDirectory add(String rel) {
        return concat(PathFactory.makeRelativeDirectory(rel));
    }

    // Remove

    @Override
    public AbsoluteDirectory remove(RelativeDirectory removal) throws PathsNotMatchingException {
        PathSupplier<AbsoluteDirectory> constructor = (head, tail, file, length) ->
                head == null
                        // ? EmptyAbsoluteDirectoryPath.instance()
                        ? null
                        : new AbsoluteDirectoryPath(head, tail, length);

        return removeImpl(removal, constructor);
    }

    @Override
    public RelativeDirectory remove (AbsoluteDirectory absDir) throws PathsNotMatchingException{
        PathSupplier<RelativeDirectoryPath> constructor = (head, tail, file, length) ->
                head == null
                        ? EmptyRelativeDirectoryPath.instance()
                        : new RelativeDirectoryPath(head, tail, length);

        return removeImpl(absDir, constructor);
    }

    // Path


    @Override
    public boolean hasParent() {
        return tailNode().getPrev() != null;
    }

    @Override
    public RelativeDirectory getParent() {
        if (!hasParent()) throw new NoParentException();

        DirectoryNode parentNode = new DirectoryNode(null, tailNode().getPrev().getNodeName());
        return new RelativeDirectoryPath(parentNode,parentNode, 1);
    }

    @Override
    public AbsoluteDirectory getParentPath() {
        if (!hasParent()) throw new NoParentException();

        return copy(tailNode().getPrev());
    }


    @Override
    public AbsoluteDirectory copy() {
        return copy(tailNode());
    }

    // TODO
    @Override
    public boolean isSubPath(RelativeDirectory relDir) {
        throw new NoImplementationException();
    }

    // TODO
    @Override
    public boolean isSubPath(AbsoluteDirectory absDir) {
        throw new NoImplementationException();
    }


    @Override
    public boolean equals(Path path) {
        boolean equals = false;

        if (path != null && !path.isEmpty() && path instanceof AbsoluteDirectory){
            equals = super.equals(path);
        }

        return equals;
    }

    @Override
    public boolean equals(String path) {
        return toPath().equals(path);
    }
}
