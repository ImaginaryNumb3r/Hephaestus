package datastructure.tree.impl.node;

import datastructure.tree.impl.exception.NodeAlreadyExistsException;
import datastructure.tree.node.subtype.subtype.MultiIdTreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <I> Identifier of the current node
 * @param <V> Value of the current node
 */
@SuppressWarnings("WeakerAccess")
public class MultiIdTreeNodeImpl<I, V>  /* extends MultiIdTreeNodeReaderImpl<I, V> */
        implements MultiIdTreeNode<I, V, MultiIdTreeNodeImpl<I, V>> {
    //<editor-fold desc="Attributes">
    protected MultiIdTreeNodeImpl<I, V>_parent;
    protected List<MultiIdTreeNodeImpl<I, V>> _children;
    protected I _identifier;
    protected V _value;
    //</editor-fold>

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

    @Override
    public boolean hasParent() {
        return false;
    }

    public MultiIdTreeNodeReaderImpl<I, V> toReadOnly(){
        return new MultiIdTreeNodeReaderImpl<>(this);
    }

    //<editor-fold desc="Getter and Setter">
    @Override
    public MultiIdTreeNodeImpl<I, V> parent() {
        return _parent;
    }

    public void setParent(MultiIdTreeNodeImpl<I, V> parent) {
        _parent = parent;
    }

    @Override
    public List<MultiIdTreeNodeImpl<I, V>> children() {
        return _children;
    }

    /**
     * Adds a node as sub node
     * @param child the node that is to be added
     * @throws NodeAlreadyExistsException if the child already has a parent
     */
    @Override
    public void addChild(MultiIdTreeNodeImpl<I, V> child) {
        if (child._parent != null && child._parent != this){
            throw new NodeAlreadyExistsException("Parent node in node " + child.toString() + " was already setAt!");
        }

        _children.add(child);
        child._parent = this;
    }

    /**
     * Creates a new child node with the given properties as parameters
     * @param identifier of the new node
     * @param value the new node
     * @throws NodeAlreadyExistsException if the child already has a parent
     */
    public void addChild(I identifier, V value){
        addChild(new MultiIdTreeNodeImpl<>(identifier, value, this));
    }

    /**
     * Removes a child with the specified identifier and returns it
     *
     * @param identifier of the child to remove
     * @return The removed child.
     * Or null if no child with this identifier exists.
     */
    public MultiIdTreeNodeImpl<I, V> removeChild(I identifier){
        MultiIdTreeNodeImpl<I, V> childNode = null;
        for (Iterator<MultiIdTreeNodeImpl<I, V>> iter = children().iterator(); childNode == null && iter.hasNext();){
            MultiIdTreeNodeImpl<I, V> cur = iter.next();

            if (cur.getIdentifier() == identifier){
                childNode = cur;
                childNode._parent = null;
                iter.remove(); // Can be called because the list of children is a ArrayList or LinkedList
            }
        }

        return childNode;
    }

    @Override
    public Stream<MultiIdTreeNodeImpl<I, V>> childStream() {
        return children().stream();
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public I getIdentifier() {
        return _identifier;
    }

    @Override
    public void setIdentifier(I identifier) {
        _identifier = identifier;
    }

    @Override
    public V getValue() {
        return _value;
    }

    @Override
    public void setValue(V value) {
        _value = value;
    }
    //</editor-fold>

    @Override
    public String toString(){
        return "Id: " + _identifier + " | Value: " + _value;
    }
}
