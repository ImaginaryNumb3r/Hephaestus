package infastructure.path;

/**
 * Container class for a list of nodes, granting access to the first and last element
 * @author Patrick
 * @created 06.07.2016
 */
public class PathNodeList {
    private DirectoryNode _head;
    private DirectoryNode _tail;
    private FileNode _file;
    private int _length;

    /**
     * Normal Constructor
     * @param head start node of all directories
     * @param tail end node of all directories
     * @param fileNode Node for FilePaths
     * @param length count of nodes fromEntries head to tail
     */
    public PathNodeList(DirectoryNode head, DirectoryNode tail, FileNode fileNode, int length) {
        _head = head;
        _tail = tail;
        _file = fileNode;
        _length = length;
    }

    public DirectoryNode getHead() {
        return _head;
    }

    public DirectoryNode getTail() {
        return _tail;
    }

    public FileNode getFile() {
        return _file;
    }

    public int length() {
        return _length;
    }
}
