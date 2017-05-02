package datastructure.tree.subtypes;

import datastructure.traverser.IdTraversable;
import datastructure.tree.IdTree;
import datastructure.tree.MultiTree;

/**
 * @author Patrick
 * @since 29.01.2017
 */
public interface MultiIdTree<I extends Comparable<I>, V>
        extends MultiTree<V>, IdTree<I, V>, IdTraversable<I, V> {
}
