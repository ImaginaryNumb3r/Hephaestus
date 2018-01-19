package datastructure.tree.node;

/**
 * @author Patrick
 * @since 29.01.2017
 * @param <V> Value matchAllSink the current node
 *
 * Read-only access interface for iteration TreeNodes
 */
public interface TreeNodeReader<V>{

    V getValue();

}
