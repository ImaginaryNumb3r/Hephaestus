package infastructure.path;

import infastructure.path.enums.PathType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public class Paths {
    private final HashMap<Character, Boolean> _forbiddenCharacter;
    private String _testFilesPath;

    // ===================
    //   Constructor
    // ===================

    protected Paths() {
        _testFilesPath = "Tests\\Files";

        // Initialize HashMap: Forbidden characters
        Character[] invalidChars = {'\\', '/', ':', '*', '?', '"', '<', '>', '|' };
        _forbiddenCharacter = new HashMap<>(invalidChars.length);

        for (int i = 0; i != invalidChars.length; ++i){
            _forbiddenCharacter.put(invalidChars[i], true);
        }
    }

    // ===================
    //   Factory Methods
    // ===================

    public PathType getPathType(String path){
        PathType pathType = null;

        if (isAbsolutePath(path)){

            pathType = isFile(path)
                    ? PathType.ABSOLUTE_FILE
                    : PathType.ABSOLUTE_DIRECTORY;
        }
        else if (isRelativePath(path)){

            pathType = isFile(path)
                    ? PathType.RELATIVE_FILE
                    : PathType.RELATIVE_DIRECTORY;
        }

        return pathType;
    }

    // TODO
    private boolean isRelativePath(String path) {
        throw new NotImplementedException();
    }

    public boolean isAbsolutePath(String path){
        boolean isAbsolute = false;

        if (!path.isEmpty()){

            // normalize and split string into separate entries
            String replace = path.replace('/', '\\');
            LinkedList<String> strings = splitPath(replace);

            // get first element
            String first = strings.getFirst().isEmpty()
                    ? strings.get(1)
                    : strings.getFirst();

            isAbsolute = first.endsWith(":");
        }

        return isAbsolute;
    }

    protected boolean isValidPath(String path){
        boolean canCreate;
        File file = new File(path);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Temp.txt"))){
            writer.write(2);
            canCreate = file.createNewFile();
            file.deleteOnExit();
        } catch (IOException e) {
            canCreate = false;
        }

        return canCreate;
    }

    /**
     * Returns a
     * @return a path relative to the lastModified files directory
     */
    protected String toTestPath(String path){
        LinkedList<String> strings = splitPath(_testFilesPath);
        strings.addAll(splitPath(path));

        // TODO:
        return _testFilesPath + path;
    }

    protected LinkedList<String> splitPath(String path){
        LinkedList<String> nodes = new LinkedList<>();
        String[] split = path.split("\\\\");

        Collections.addAll(nodes, split);

        return nodes;
    }

    /**
     * Checks if a file can be found at the location
     * @param path location of the file
     * @return true if path references a File
     */
    public boolean isFile(String path){
        return new File(path).exists();
    }

    // =================
    //    Singleton
    // =================

    public static Paths instance(){
        return PathsInstanceHolder.INSTANCE;
    }

    private static class PathsInstanceHolder {
        private static final Paths INSTANCE = new Paths();
    }
}
