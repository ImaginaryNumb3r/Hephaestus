package infastructure.filetype.interfaces;


import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.path.DirectoryNode;
import infastructure.path.exceptions.PathsNotMatchingException;

/**
 * @author Patrick
 * @created 04.07.2016
 */
public interface Path{

    int length();

    Path concat (RelativeDirectory rel);

    Path remove (RelativeDirectory removal) throws PathsNotMatchingException;

    /**
     * Compares both paths
     * @param path the path that is to be compared
     * @return
     */
    boolean equals(Path path);

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
     * Performs a check whether this is an empty path
     * @return true if the path can be used safely in arithmetic concatenation and removal of paths
     */
    default boolean isEmpty(){
        return false;
    }
}