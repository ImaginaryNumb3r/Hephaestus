package datastructure.tree.interfaces.basetypes;

import datastructure.tree.interfaces.Tree;

/**
 * @author Patrick
 * @since 26.01.2017
 *
 * A Tree where values are saved at the leaves matchAllSink a tree
 * This is the opposite to a MultiTree
 */
public interface LeafTree<T> extends Tree<T> {

    void add(T value);

    /**
     *
     * @param value
     * @return true if an existing entry was replaced
     */
    boolean addOrReplace(T value);

    /**
     *
     * @param value
     * @return
     * @throws java.util.NoSuchElementException if the element did not exist
     */
    T replace(T value);

    boolean hasElement(T value);

    /**
     *
     * @param value
     * @throws java.util.NoSuchElementException If no such entry exists
     */
    T get(T value);

    /**
     *
     * @param value
     * @throws java.util.NoSuchElementException If no such entry exists
     */
    T remove(T value);

}
