package infastructure.path;

import infastructure.filetype.interfaces.aubtypes.DirectoryPath;
import infastructure.filetype.interfaces.aubtypes.FilePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.interfaces.PathSupplier;

/**
 * Helper class for Directory Path classes
 * @author Patrick
 * @since 08.07.2016
 */
class DirectoryPathImpl {

    // =======================
    //  Concat: Dir + RelFile
    // =======================

    public AbsoluteFilePath concat(AbsoluteDirectoryPath base, RelativeFile relFile, PathSupplier<AbsoluteFilePath> constructor) {
        if (base == null) throw new IllegalArgumentException("Path may not be empty");
        return concatImpl(toMask(base), relFile, constructor);
    }

    public RelativeFilePath concat(RelativeDirectoryPath base, RelativeFile relFile, PathSupplier<RelativeFilePath> constructor) {
        if (base == null) throw new IllegalArgumentException("Path may not be empty");
        return concatImpl(toMask(base), relFile, constructor);
    }

    /**
     * Merges two paths of directories together and returns a new path via constructor parameter
     * @param base first half of the new path
     * @param relFile second half of the new path
     * @param constructor instance responsible for creating the corresponding path
     * @param <T> either a relative file or relative directory
     * @return New path, composed of both parameter paths
     */
    private <T extends FilePath> T concatImpl(DirPathMask base, RelativeFile relFile, PathSupplier<T> constructor) {
        if (relFile == null || constructor == null) throw new IllegalArgumentException("Path may not be empty");

        PathNodeList nodeList = base.concatNodes(relFile);
        return constructor.get(nodeList.getHead(), nodeList.getTail(), new FileNode(null, relFile.fileNode().getNodeName()), nodeList.length());
    }

    // =======================
    //  Concat: Dir + RelDir
    // =======================

    public AbsoluteDirectoryPath concat(AbsoluteDirectoryPath base, RelativeDirectory relDir, PathSupplier<AbsoluteDirectoryPath> constructor) {
        return concatImpl(toMask(base), relDir, constructor);
    }

    public RelativeDirectoryPath concat(RelativeDirectoryPath base, RelativeDirectory relDir, PathSupplier<RelativeDirectoryPath> constructor) {
        return concatImpl(toMask(base), relDir, constructor);
    }

    /**
     * Merges with paths by concatenating the tail with the head of the path that is being added.
     * @param base first half of the new path
     * @param relDir second half of the new path
     * @param constructor instance responsible for creating the new path
     * @param <T> Either an absolute directory or relative directory
     * @return New path, composed of both parameter paths
     */
    public <T extends DirectoryPath> T concatImpl(DirPathMask base, RelativeDirectory relDir, PathSupplier<T> constructor) {
        if (relDir == null) throw new IllegalArgumentException("Path may not be empty");

        PathNodeList nodeList = base.concatNodes(relDir);
        return constructor.get(nodeList.getHead(), nodeList.getTail(), null, nodeList.length());
    }

    // ===================
    //    Masking Methods
    // ===================

    private DirPathMask toMask(RelativeDirectoryPath relDir){
        return new DirPathMask() {
            @Override
            public PathNodeList concatNodes(RelativeFile relFile) {
                return relDir.concatNodes(relFile);
            }

            @Override
            public PathNodeList concatNodes(RelativeDirectory relFile) {
                return relDir.concatNodes(relFile);
            }

            @Override
            public String toString() {
                return relDir.toString();
            }
        };
    }


    private DirPathMask toMask(AbsoluteDirectoryPath relDir){
        return new DirPathMask() {
            @Override
            public PathNodeList concatNodes(RelativeFile relFile) {
                return relDir.concatNodes(relFile);
            }

            @Override
            public PathNodeList concatNodes(RelativeDirectory relFile) {
                return relDir.concatNodes(relFile);
            }

            @Override
            public String toString() {
                return relDir.toString();
            }
        };
    }

    // Private Interface

    private interface DirPathMask{

        PathNodeList concatNodes(RelativeFile relFile);

        PathNodeList concatNodes(RelativeDirectory relFile);
    }


    // Remove
}
