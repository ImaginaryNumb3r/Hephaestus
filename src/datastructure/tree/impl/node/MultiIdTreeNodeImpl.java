package datastructure.tree.impl.node;

import java.util.LinkedList;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <I> Identifier of the current node
 * @param <V> Value of the current node
 */
@SuppressWarnings("WeakerAccess")
public class MultiIdTreeNodeImpl<I, V>  /* extends MultiIdTreeNodeReaderImpl<I, V> */
        extends AbstractMultiIdTreeNode<I, V, MultiIdTreeNodeImpl<I, V>> {

    //<editor-fold desc="Constructors">
    public MultiIdTreeNodeImpl(MultiIdTreeNodeImpl<I, V> parent) {
        _parent = parent;
        _children = new LinkedList<>();
    }

    public MultiIdTreeNodeImpl(I identifier, V value, MultiIdTreeNodeImpl<I, V> parent) {
        this(parent);
        _identifier = identifier;
        _value = value;
    }
    //</editor-fold>

    public MultiIdTreeNodeReaderImpl<I, V> toReadOnly(){
        return new MultiIdTreeNodeReaderImpl<>(this);
    }

    @Override
    public String toString(){
        return "Id: " + _identifier + " | Value: " + _value;
    }

    @Override
    protected MultiIdTreeNodeImpl<I, V> makeChild(I identifier, V value, MultiIdTreeNodeImpl<I, V> parent) {
        return new MultiIdTreeNodeImpl<>(identifier, value, parent);
    }
}
