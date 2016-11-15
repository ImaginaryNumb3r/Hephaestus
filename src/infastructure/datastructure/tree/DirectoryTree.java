package infastructure.datastructure.tree;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.path.DirectoryNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

/**
 * @author Patrick
 * @created 24.07.2016
 */
public class DirectoryTree {
    private AbsoluteDirectoryTreeNode _root;

    public DirectoryTree(AbsoluteDirectory root){
        _root = new AbsoluteDirectoryTreeNode(root);
    }

    public boolean add(AbsoluteDirectory directory) {
        LinkedList<DirectoryNode> nodes = getNodes(directory);

        // Yes, can add.
        throw new NotImplementedException();
    }

    public AbsoluteDirectoryTreeNode getRoot() {
        return _root;
    }

    /**
     * Retrieves the individual nodes from a path
     * @param directory The source directory
     * @return List of nodes in ascending order
     */
    private LinkedList<DirectoryNode> getNodes(AbsoluteDirectory directory) {
        LinkedList<DirectoryNode> nodes = new LinkedList<>();

        DirectoryNode cur = directory.tailNode();
        while (cur != null && cur.hasPrev()){
            nodes.add(cur);

            cur = cur.getPrev();
        }

        return nodes;
    }

}