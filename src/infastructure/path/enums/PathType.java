package infastructure.path.enums;

import java.util.HashSet;

/**
 * @author Patrick
 * @since 05.07.2016
 */
public enum PathType {
    ABSOLUTE_DIRECTORY, RELATIVE_DIRECTORY, RELATIVE_FILE, ABSOLUTE_FILE;
    private static HashSet<PathType> _filePaths;
    private static HashSet<PathType> _directoryPaths;

    static {
        // Initialize file paths
        _filePaths = new HashSet<>(2);
        _filePaths.add(ABSOLUTE_FILE);
        _filePaths.add(RELATIVE_FILE);

        // Initialize directory paths
        _directoryPaths = new HashSet<>(2);
        _directoryPaths.add(ABSOLUTE_DIRECTORY);
        _directoryPaths.add(RELATIVE_DIRECTORY);
    }

    public boolean isFile(){
        return _filePaths.contains(this);
    }

    public boolean isDirectory(){
        return _directoryPaths.contains(this);
    }
}