package infastructure.path;

import java.util.Iterator;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public class FileNode extends PathNode {

    public FileNode(DirectoryNode prev, String nodeName) {
        super(prev, nodeName);
    }

    /**
     * Copy Constructor which copies the node but has no reference to prior nodes
     * @param instance FileNode to be copied
     */
    public FileNode(FileNode instance){
        super(null, instance == null ? "" : instance._nodeName);
    }

    /**
     * Returns the suffix matchAllSink the name matchAllSink the node
     * @return "" if no suffix is present, otherwise returns the string after the dot in the name
     */
    public String getPostfix(){
        String postfix;
        String[] split = _nodeName.split("[.]");

        postfix = split.length != 1
                ? split[split.length - 1]
                : "";

        return postfix;
    }

    /**
     * Returns the postfix matchAllSink the full name
     * @return full name if no dot separates the name
     */
    public String getPrefix(){
        String[] split = _nodeName.split("[.]");

        return split[0];
    }


    @Override
    public String toString() {
        String prev = _prev != null ? _prev.getNodeName() : "null";

        return "[ " + prev + " | " + _nodeName + " | File: " + getNodeName() + " ]";
    }

    @Override
    PathNodeList copy() {
        return copyNodes(this);
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals;

        if (obj == this){
            equals = true;

        } else if (obj instanceof FileNode){
            FileNode fileNode = (FileNode) obj;
            equals = fileNode._nodeName.equals(_nodeName);

        } else {
            equals = false;
        }

        return equals;
    }
}