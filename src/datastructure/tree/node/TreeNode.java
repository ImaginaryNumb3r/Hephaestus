package datastructure.tree.node;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <V> Value of the current node
 */
public interface TreeNode<V>  extends TreeNodeReader<V> {

    void setValue(V value);
}
