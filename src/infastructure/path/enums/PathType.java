package infastructure.path.enums;

import java.util.HashMap;

/**
 * @author Patrick
 * @created 05.07.2016
 */
public enum PathType {
    ABSOLUTE_DIRECTORY, RELATIVE_DIRECTORY, RELATIVE_FILE, ABSOLUTE_FILE;
    private static HashMap<PathType, Boolean> _filePaths;
    private static HashMap<PathType, Boolean> _directoryPaths;

    static {
        // Initialize file paths
        _filePaths = new HashMap<>(2);
        _filePaths.put(ABSOLUTE_FILE, true);
        _filePaths.put(RELATIVE_FILE, true);

        // Initialize directory paths
        _directoryPaths = new HashMap<>(2);
        _directoryPaths.put(ABSOLUTE_DIRECTORY, true);
        _directoryPaths.put(RELATIVE_DIRECTORY, true);
    }

    public boolean isFile(){
        return _filePaths.get(this) != null;
    }

    public boolean isDirectory(){
        return _directoryPaths.get(this) != null;
    }
}