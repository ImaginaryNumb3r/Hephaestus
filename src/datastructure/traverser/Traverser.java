package datastructure.traverser;

import datastructure.traverser.exceptions.NoValueException;
import datastructure.tree.node.TreeNodeReader;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <T> value of the node
 */
// TODO: Move to Graph Framework
public interface Traverser<T> {

    /**
     * Returns true if current node is a subnode in a higher hierarchy
     * @return true if current node is a subnode in a higher hierarchy
     */
    boolean hasParent();

    /**
     * Moves cursor to the upper node
     * @throws java.util.NoSuchElementException if no Parent for this node exists
     */
    void enterParent();

    /**
     * Returns true if the current node has any sub nodes
     * @return true if the current node has any sub nodes
     */
    boolean hasChildren();

    /**
     * Returns true if there is one more child in this node that can be entered
     * @return true if there is one more child in this node that can be entered
     */
    boolean hasNextChild();

    /**
     * Returns the value of the input child node.
     * @return the value of the input child node
     */
    TreeNodeReader<T> nextChild();

    /**
     * Enters the child node that has previously iterated over
     * @throws java.util.NoSuchElementException if no child exists or none was selected
     */
    void enter();

    /**
     * Returns the value of the current node. This can always be accessed with
     * @return the value of the current node
     * @throws NoValueException if no value for the current node exists
     */
    TreeNodeReader<T> value();

    /**
     * Returns true if the node has a value and is not just a
     * connecting node withing a tree that only saves information in leaves
     * @return true if the node has a value and can be accessed
     */
    default boolean hasValue(){
        return value() != null;
    }

    static <T extends Iterable<T>> Traverser<T> of(T root){
        return new TraverserImpl<>(root);
    }

}
