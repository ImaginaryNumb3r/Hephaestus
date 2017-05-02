package datastructure.tree.impl.node;

import datastructure.tree.node.subtype.subtype.MultiIdTreeNodeReader;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 02.05.2017
 */
@SuppressWarnings("WeakerAccess")
public class MultiIdTreeNodeReaderImpl<I, V> implements MultiIdTreeNodeReader<I, V, MultiIdTreeNodeReaderImpl<I, V>> {
    //<editor-fold desc="Attributes">
    protected MultiIdTreeNodeReaderImpl<I, V>_parent;
    protected List<MultiIdTreeNodeReaderImpl<I, V>> _children;
    protected I _identifier;
    protected V _value;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public MultiIdTreeNodeReaderImpl(MultiIdTreeNodeReaderImpl<I, V> parent) {
        _parent = parent;
        _children = new LinkedList<>();
    }

    public MultiIdTreeNodeReaderImpl(MultiIdTreeNodeImpl<I, V> root) {
        _parent = root.hasParent()
                ? new MultiIdTreeNodeReaderImpl<>(root._parent)
                : null;
        _identifier = root._identifier;
        _value = root._value;
        _children = root._children.stream()
                .map((Function<MultiIdTreeNodeImpl<I, V>, MultiIdTreeNodeReaderImpl<I, V>>) MultiIdTreeNodeReaderImpl::new)
                .collect(Collectors.toList());
    }

    public MultiIdTreeNodeReaderImpl(I identifier, V value, MultiIdTreeNodeReaderImpl<I, V> parent) {
        this(parent);
        _identifier = identifier;
        _value = value;
    }
    //</editor-fold>

    @Override
    public V getValue() {
        return _value;
    }

    @Override
    public I getIdentifier() {
        return _identifier;
    }

    @Override
    public Iterable<MultiIdTreeNodeReaderImpl<I, V>> children() {
        return _children;
    }

    @Override
    public boolean hasChildren() {
        return !_children.isEmpty();
    }

    @Override
    public MultiIdTreeNodeReaderImpl<I, V> parent() {
        return _parent;
    }

    @Override
    public Stream<MultiIdTreeNodeReaderImpl<I, V>> childStream() {
        return _children.stream();
    }
}
