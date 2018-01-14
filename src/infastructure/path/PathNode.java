package infastructure.path;

import java.util.Iterator;

/**
 * @author Patrick
 * @created 04.07.2016
 * @purpose Works as a single node in a file and represents a directory or a file
 */
// TODO: Remove prev and output getPrev only in DirectoryNode
abstract class PathNode {
    protected String _nodeName;
    protected DirectoryNode _prev;

    /**
     * Copy Constructor
     * @param prev the tryPrevious instance which will be linked to the new node
     * @param nodeName name of the current node, representing the name of a file or directory
     */
    public PathNode(DirectoryNode prev, String nodeName) {
        _nodeName = nodeName;
        _prev = prev;
    }

    // Getter

    public String getNodeName() {
        return _nodeName;
    }

    /**
     * Returns the very first element in this list
     * @return null if the node list consists of only the tail
     */
    public DirectoryNode getHead() {
        DirectoryNode head = null;

        for (DirectoryNode previous : _prev) {
            head = previous;
        }

        return head;
    }

    public DirectoryNode getPrev() {
        return _prev;
    }


    // Setter

    void setNodeName(String nodeName) {
        _nodeName = nodeName;
    }

    public void setPrev(DirectoryNode prev) {
        _prev = prev;
    }

    // Other


    public boolean hasPrev() {
        return _prev != null;
    }

    @Override
    public String toString() {
        String prev = _prev != null ? _prev.getNodeName() : "null";

        return "[ " + prev + " | " + _nodeName + " ]";
    }

    abstract PathNodeList copy();

    protected PathNodeList copyNodes(DirectoryNode lastNode){
        DirectoryNode tail = new DirectoryNode(null, lastNode._nodeName);
        DirectoryNode cur = tail;
        int count = 1;

        if (lastNode.hasPrev()){
            for (DirectoryNode node : lastNode.getPrev()) {
                DirectoryNode newNode = new DirectoryNode(null, node._nodeName);
                cur.setPrev(newNode);

                cur = newNode;
                ++count;
            }
        }

        DirectoryNode head = cur;

        return new PathNodeList(head, tail, null, count);
    }

    protected PathNodeList copyNodes(FileNode lastNode){
        FileNode file = new FileNode(null, lastNode._nodeName);
        int count = 1;

        DirectoryNode head = null;
        PathNode cur = file;
        for (DirectoryNode node : lastNode.getPrev()) {
            DirectoryNode newNode = new DirectoryNode(null, node._nodeName);
            cur.setPrev(newNode);

            cur = newNode;
            head = newNode;
            ++count;
        }

        return new PathNodeList(head, file.getPrev() ,file, count);
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;

        if (obj == this){
            equals = true;
        } else if (obj instanceof PathNode){
            PathNode node = (PathNode) obj;
            equals = node._nodeName.equals(_nodeName);

        } else {
            equals = false;
        }

        return equals;
    }
}