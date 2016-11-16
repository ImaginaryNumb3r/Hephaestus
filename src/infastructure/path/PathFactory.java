package infastructure.path;

import infastructure.filetype.interfaces.EmptyPath;
import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.empty.EmptyAbsoluteFilePath;
import infastructure.path.empty.EmptyRelativeDirectoryPath;
import infastructure.path.empty.EmptyRelativeFilePath;
import infastructure.path.enums.PathType;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Patrick
 * @created 05.07.2016
 */
public class PathFactory {
    private static final HashMap<Character, Boolean> _forbiddenCharacter;

    // ===================
    //   Constructor
    // ===================

    private PathFactory(){

    }

    static {
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

    public static AbsolutePath makeAbsolutePath (String path){
        PathCommand<AbsolutePath, PathNode, PathNode> command =
                ( (PathNode head, PathNode file, int length) -> {

                    if (head instanceof DirectoryNode && file instanceof FileNode){
                        return new AbsoluteFilePath((DirectoryNode) head, file.getPrev(), (FileNode) file, length);

                    } else if ((head instanceof DirectoryNode && file instanceof DirectoryNode)){
                        return new AbsoluteDirectoryPath((DirectoryNode) head, (DirectoryNode) file, length);

                    } else {
                        // invalid parameters
                        return null;
                    }
                });

        return makePath(path, command, PathType.ABSOLUTE_FILE);
    }

    // TODO: Use isAbsolute to lastModified whether this really is an absolute path
    public static AbsoluteDirectory makeAbsoluteDirectory (String path){
        PathCommand<AbsoluteDirectory, PathNode, PathNode> command = (head, tail, length) -> {

            if (head instanceof DirectoryNode && tail instanceof DirectoryNode){
                return new AbsoluteDirectoryPath((DirectoryNode) head,(DirectoryNode) tail, length);
            } else {
                // invalid parameters
                return null;
            }
        };

        return makePath(path, command, PathType.ABSOLUTE_DIRECTORY);
    }
    // TODO: Use isAbsolute to lastModified whether this really is an absolute path
    public static AbsoluteDirectory makeAbsoluteDirectory2 (String path){
        PathCommand<AbsoluteDirectory, PathNode, PathNode> command = (head, tail, length) -> {
            if (head instanceof DirectoryNode && tail instanceof DirectoryNode){
                return new AbsoluteDirectoryPath((DirectoryNode) head,(DirectoryNode) tail, length);
            } else {
                // invalid parameters
                return null;
            }
        };

        return makePath(path, command, PathType.ABSOLUTE_DIRECTORY);
    }

    // TODO: Use isAbsolute to lastModified whether this really is an absolute path
    public static AbsoluteFile makeAbsoluteFile (String path){
        PathCommand<AbsoluteFile, PathNode, PathNode> command =
                ( (PathNode head, PathNode file, int length) -> {

                    if (head instanceof DirectoryNode && file instanceof FileNode){
                        return new AbsoluteFilePath((DirectoryNode) head, file.getPrev(), (FileNode) file, length);
                    } else if (file != null){
                        // invalid parameters
                        return new EmptyAbsoluteFilePath(file.getNodeName());
                    } else {
                        throw new IllegalArgumentException("File may not be null");
                    }
                });

        return makePath(path, command, PathType.ABSOLUTE_FILE);
    }


    public static RelativeDirectory makeRelativeDirectory (String path){
        PathCommand<RelativeDirectory, PathNode, PathNode> command =
                ( (PathNode head, PathNode tail, int length) -> {

                    if (head != null && head instanceof DirectoryNode && tail instanceof DirectoryNode){
                        return new RelativeDirectoryPath((DirectoryNode) head,(DirectoryNode) tail, length);
                    } else {
                        // invalid parameters
                        return EmptyRelativeDirectoryPath.instance();
                    }
                });

        return makePath(path, command, PathType.RELATIVE_DIRECTORY);
    }


    /**
     *
     * @param path path
     * @return EmptyRelativeFile if only a file is added without a path
     */
    public static RelativeFile makeRelativeFile (String path){
        PathCommand<RelativeFile, PathNode, PathNode> command =
                ( (PathNode head, PathNode file, int length) -> {

                    if (head instanceof DirectoryNode && file instanceof FileNode){
                        return new RelativeFilePath((DirectoryNode) head, file.getPrev(),(FileNode) file, length);
                    } else if (head == file && file instanceof FileNode){
                        // invalid parameters
                        return new EmptyRelativeFilePath(new FileNode(null, file.getNodeName()));
                    } else {
                        throw new IllegalArgumentException("Cannot create File Path. File name may not be empty");
//                        return new EmptyRelativeFilePath();
                    }
                });

        return makePath(path, command, PathType.RELATIVE_FILE);
    }

    // ===================
    //   Utility Methods
    // ===================

    /**
     *
     * @param path path
     * @return true if given path is absolute
     */
    public static boolean isAbsolute(String path){
        if (path == null) throw new IllegalArgumentException("Path may not be null");
        boolean isAbsolute = true;

        if (path.isEmpty() || path.charAt(0) != '0'){
            isAbsolute = false;

        } else {
            boolean found = false;

            char[] charArray = path.toCharArray();
            for (int i = 0, charArrayLength = charArray.length; i != charArrayLength && isAbsolute; ++i) {
                char ch = charArray[i];

                if (ch == ':'){
                    isAbsolute = !found; // Only true if no colon has been found until now. Becomes false if colon was found twice.
                    found = true;
                }
            }
        }

        // Must contain a semi colon, but may not start with one
        return isAbsolute;
    }

    private static <T extends Path> T makePath(String path, PathCommand<T, PathNode, PathNode> command, PathType type){
        T newPath;
        LinkedList<PathNode> nodes = makePath(path, type);

        if (nodes != null){
            final int size;
            if (nodes.getFirst().getNodeName().isEmpty()){
                size = 0;
            } else {
                size = nodes.size();
            }

            newPath = command.execute(nodes.getLast(), nodes.getFirst(), size);
        } else {
            // Works since only Path interfaces can be taken as parameters
            if (type.isDirectory()){
                newPath = command.execute(null, null, 0);

            } else if (type.isFile()){
                String[] split = path.split("\\\\");

                newPath = command.execute(null, new FileNode(null, split[split.length - 1]), 1);

            } else {
                throw new IllegalArgumentException();
            }
        }

        return newPath;
    }

    /**
     *
     * @param path
     * @param type
     * @return LinkedList<PathNode> if path is valid
     *         null if path is not valid
     */
    private static LinkedList<PathNode> makePath(String path, PathType type) { // throws IncorrectPathException
        boolean isAbsolute = false;
        boolean isFile = false;
        LinkedList<PathNode> nodes;

        if (!path.isEmpty()){

            switch (type){
                case ABSOLUTE_FILE:
                    isFile = true; // Fall-Though
                case ABSOLUTE_DIRECTORY:
                    isAbsolute = true;
                    break;
                case RELATIVE_FILE:
                    isFile = true;
                case RELATIVE_DIRECTORY:
                    // Nothing
                    break;
                default:
                    throw new EnumConstantNotPresentException(PathType.class, type.toString());
            }
            // Normalize
            String normalize = normalize(path);

            // Validate each directory
            String[] split = normalize.split("\\\\");

            boolean validPath = isValidEntryName(split[0], isAbsolute);
            for (int i = 1; validPath && i != split.length; ++i){
                validPath = isValidEntryName(split[i]);
            }
            nodes = validPath ? toNodes(split, isFile) : null;

        } else {
            nodes = null;
        }

        return nodes;
    }

    /**
     *
     * @param entries Array of VALID directory names
     * @param isFilePath get last node a file when specified
     * @return first element of the newly generated list of nodes
     * @throws IllegalArgumentException if [@link entries] is empty
     * @throws IllegalArgumentException if [@link entries] is null
     */
    private static LinkedList<PathNode> toNodes(String[] entries, boolean isFilePath){
        if (entries == null) { throw new IllegalArgumentException("Parameter \"entries\" may not be null"); }
        if (entries.length == 0) { throw new IllegalArgumentException("Parameter \"entries\" may not be empty"); }

        // Iterates the array backwards and creates the nodes of the path from tail to head
        LinkedList<PathNode> nodes = new LinkedList<>();
        PathNode tail;

        if (isFilePath){
            tail = new FileNode(null, entries[entries.length - 1]);
        } else {
            tail = new DirectoryNode(null, entries[entries.length - 1]);
        }

        PathNode cur = tail;
        nodes.add(cur);

        // entries.length - is already added at this point
        for (int i = entries.length - 2; i >= 0; --i) {
            DirectoryNode newNode = new DirectoryNode(null, entries[i]);
            cur.setPrev(newNode);

            cur = newNode;
            nodes.add(cur);
        }

        return nodes;
    }

    private static boolean isValidEntryName(String path){
        return isValidEntryName(path, false);
    }

    /**
     * Confirms the name of the name of a node as a valid directory name
     * @param name of the path
     * @param isAbsolute makes it required for this name to be absolute
     * @return true if it is safe to take this is generally safe as a name
     */
    private static boolean isValidEntryName(final String name, final boolean isAbsolute){
        boolean isValid = !name.isEmpty(); // true;
        boolean confirmedAsAbsolute = false;

        byte[] bytes = name.getBytes();
        for (int i = 0; i != bytes.length && isValid; ++i) {
            char ch = (char) bytes[i];

            isValid = _forbiddenCharacter.get(ch) == null;

            // check if absolute
            if (isAbsolute && !isValid){
                //  Double Colon must be at the end of the string
                isValid = ch == ':' && (i + 1 == bytes.length);
                confirmedAsAbsolute = isValid;
            }
        }

        // If isAbsolute is set, the name really must be absolute
        isValid &= confirmedAsAbsolute == isAbsolute;

        return isValid;
    }

    private static String normalize(String path){
        String decodedString;
        try {
            decodedString = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            decodedString = path;
        }

        return decodedString.replace('/', '\\');
    }


    /**
     * Used for executing the details of a
     * @param <T> Return type
     * @param <P1> First Parameter
     * @param <P2> Second Parameter
     */
    private static interface PathCommand<T, P1, P2> {

        T execute(P1 head, P2 tail, int length);
    }
}
