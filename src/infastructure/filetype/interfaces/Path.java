package infastructure.filetype.interfaces;


import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.DirectoryNode;
import infastructure.path.PathFactory;
import infastructure.path.exceptions.PathsNotMatchingException;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 04.07.2016
 */
public interface Path{

    // TODO:
        // - boolean StartsWith()
        // - boolean EndsWith()
        // - Uri toUri()
        // - isSubPath()

    int length();

    Path concat (RelativeDirectory rel);

    Path remove (RelativeDirectory removal) throws PathsNotMatchingException;

    /**
     * Returns an iterator that provides the name of the individual nodes
     * @return an iterator that provides the name of the individual nodes
     */
    default Iterator<String> stringIterator(){
        return headNode().stringIterator();
    }

    /**
     * Compares both paths
     * @param path the path that is to be compared
     * @return true if both pats are functionally the same
     */
    boolean equals(Path path);

    /**
     * Compares the path with the given string
     * @param path the path that is to be compared
     * @return true if both pats are functionally the same
     */
    boolean equals(String path);

    /**
     * Returns the first element of a path, pointing to the next node and subsequently every other node in the path
     * @return Starting node of the path
     */
    DirectoryNode headNode();

    /**
     * Returns the last node in the path
     * @return the last node in the path
     */
    DirectoryNode tailNode();

    /**
     * Returns name of the last Node
     * @return name of the last Node
     */
    String getName();

    /**
     * Returns an exact copy of this path
     * @return an exact copy of this path
     */
    Path copy();

    /**
     * Performs a checkNulls whether this is an empty path
     * @return true if the path can be used safely in arithmetic concatenation and removal of paths
     */
    default boolean isEmpty(){
        return false;
    }

    boolean isSubPath(RelativeDirectory relDir);

    int hashCode();
}