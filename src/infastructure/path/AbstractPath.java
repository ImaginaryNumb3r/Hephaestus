package infastructure.path;

import com.sun.istack.internal.NotNull;
import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.RelativePath;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.exceptions.PathsNotMatchingException;

import java.util.Iterator;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public abstract class AbstractPath implements Path {
    protected DirectoryNode _head;
    protected DirectoryNode _tail;
    protected int _nodeCount;
    protected String _path; // Lazy

    public AbstractPath(DirectoryNode head, DirectoryNode tail, int nodeCount) {
        _tail = tail;
        _head = head;
        _nodeCount = nodeCount;
    }

    // ==================
    //    Path Methods
    // ==================

    @Override
    public int length(){
        return _nodeCount;
    }

    public DirectoryNode headNode() {
        return _head;
    }

    @Override
    public DirectoryNode tailNode() {
        return _tail;
    }

    @Override
    public String getName() {
        return _tail.getNodeName();
    }

    // ==================
    //     Methods
    // ==================

    @Override
    public String toString() {
        return toPath();
    }


    /**
     * Prints this path as string
     * @return path as string
     */
    protected final String toPath() {
         if (_path == null || _path.isEmpty()){
            _path  = _tail.getNodeName();

            if (_tail.hasPrev()){
                for (DirectoryNode node : _tail.getPrev()) {
                    _path = node.getNodeName() + "\\" + _path;
                }
            }
        }

        return _path;
    }

    // ====================
    //   Utility Methods
    // ====================

    /**
     * Merges two paths together by concatenating their nodes
     * @param rel relative path or empty path
     * @return PathNodeList, granting immediate access to the first and last node, as well as the file and the total length.
     *         copy of "this" path if "rel" is an empty path
     */
    protected PathNodeList concatNodes(RelativePath rel) {
        if (rel == null) throw new IllegalArgumentException("Path may not be null!");
        PathNodeList nodeList;

        if (!rel.isEmpty()){

            PathNodeList secondHalf = rel.tailNode().copy();
            DirectoryNode secondHalfHead = secondHalf.getHead();
            DirectoryNode secondHalfTail = secondHalf.getTail();

            PathNodeList firstHalf = tailNode().copy();
            DirectoryNode firstHalfHead = firstHalf.getHead();
            DirectoryNode firstHalfTail = firstHalf.getTail();


            secondHalfHead.setPrev(firstHalfTail);
            int totalNodes = firstHalf.length() + secondHalf.length();

            nodeList = new PathNodeList(firstHalfHead, secondHalfTail, null, totalNodes);
        } else {
            nodeList = tailNode().copy();
        }

        return nodeList;

    }

    /**
     * Returns tail node of the new absolute path.
     * Used when an absolute path is subtracted by another absolute path
     *
     * @param baseNode base node
     * @param removalNode node serving as marker for removing
     * @return null           if an empty path was given as removal parameter
     *         null           if both path are equal
     *         DirectoryNode  for the tail of the new absolute Path
     *
     * @throws PathsNotMatchingException if removalPath is no subset of basePath
     */
    protected DirectoryNode getNewHead(DirectoryNode baseNode, DirectoryNode removalNode) throws PathsNotMatchingException {
        if (baseNode == null || removalNode == null) throw new IllegalArgumentException("Parameters may not be Null");
        DirectoryNode newHead;

        // Another loop

        // Step 1: Get first matching node

        DirectoryNode prev;
        boolean matches;
        // Search for match until found. Escape via exception if paths don't match.
        do {

            prev = null;
            DirectoryNode curBase = baseNode;
            matches = false;

            while (!matches && curBase.hasPrev()){
                if (curBase.equals(removalNode)){
                    matches = true;
                } else {
                    prev = curBase;
                    curBase = curBase.getPrev();
                }
            }

            // Step 2: Validate followup paths

            DirectoryNode curRemoval = removalNode;
            while (matches && curBase.hasPrev() && curRemoval.hasPrev()){
                matches = curBase.equals(curRemoval);

                curBase = curBase.getPrev();
                curRemoval = curRemoval.getPrev();
            }

            matches = curBase.equals(curRemoval);

            // Step 3: Repeat if necessary
            if (!matches){
                if (baseNode.hasPrev()){
                    baseNode = baseNode.getPrev();
                } else {
                    throw new PathsNotMatchingException();
                }
            }

            // Repeat until a match was found
        } while (!matches);

        newHead = prev;

        return newHead;
    }

    /**
     * Returns tail node of the new absolute path.
     * Used when an absolute path is subtracted by a relative path
     *
     * @param baseIterator iterator at the last position from an absolute path
     * @param removalIterator iterator at the last position of a relative path
     * @return null           if an empty path was given as removal parameter
     *         DirectoryNode  for the tail of the new absolute Path
     *
     * @throws PathsNotMatchingException if removalPath is no subset of basePath
     */
    @NotNull
    // TODO: Do not return null
    protected DirectoryNode getNewTail(Iterator<DirectoryNode> baseIterator, Iterator<DirectoryNode> removalIterator) throws PathsNotMatchingException {
        if (baseIterator == null || removalIterator == null) throw new IllegalArgumentException("Path may not be null!");

        boolean inSync = true;
        boolean hasNext = baseIterator.hasNext() && removalIterator.hasNext();

        DirectoryNode curBase = null;
        while (hasNext && inSync){
            curBase = baseIterator.next();
            DirectoryNode curRemoval = removalIterator.next();

            inSync = curBase.getNodeName().equals(curRemoval.getNodeName());
            hasNext = baseIterator.hasNext() && removalIterator.hasNext();

            if (!hasNext){
                if (baseIterator.hasNext()){
                    curBase = baseIterator.next();
                }

                // removalIterator must have been completely iterated through
                inSync = !removalIterator.hasNext();
            }
        }

        if (!inSync) {
            throw new PathsNotMatchingException();
        }

        return curBase;
    }


    // Path

    @Override
    public boolean equals(Path path) {
        boolean equals = false;

        if (path != null && !path.isEmpty()){

            // Only compare, if paths also have the same filename
            Iterator<DirectoryNode> iter1 = tailNode().iterator();
            Iterator<DirectoryNode> iter2 = path.tailNode().iterator();

            equals = iter1.hasNext() && iter2.hasNext();
            boolean hasNext = equals;
            boolean inSync = equals;
            // Compare both paths node by node
            while (hasNext){
                DirectoryNode node1 = iter1.next();
                DirectoryNode node2 = iter2.next();

                inSync = node1.equals(node2);
                hasNext = inSync && iter1.hasNext() && iter2.hasNext();
            }

            equals = inSync;
        }

        return equals;
    }
}
