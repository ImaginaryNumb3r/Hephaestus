package datastructure.tree.node;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 29.01.2017
 * @param <V> Value of the current node
 *
 * Read-only access interface for simple TreeNodes
 */
public interface TreeNodeReader<V>{

    V getValue();
}
