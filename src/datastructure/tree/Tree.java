package datastructure.tree;

import datastructure.traverser.Traversable;
import datastructure.traverser.Traverser;

import java.io.Serializable;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <T> the value for individual nodes
 *
 * Base interface for all Trees
 */
@SuppressWarnings("WeakerAccess")
public interface Tree<T> extends Traversable<T>, Serializable {
}
