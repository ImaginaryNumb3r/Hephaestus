package datastructure.tree.impl.node;

import datastructure.tree.impl.exception.NodeAlreadyExistsException;
import datastructure.tree.node.subtype.subtype.MultiIdTreeNode;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 28.05.2017
 * @param <I> Identifier of the current node
 * @param <V> Value of the current node
 * @param <N> Subnodes of the current nodes
 */
public abstract class AbstractMultiIdTreeNode<I, V, N extends AbstractMultiIdTreeNode<I, V, N>>
        implements MultiIdTreeNode<I, V, N> {
    //<editor-fold desc="Attributes">
    protected N _parent;
    protected List<N> _children;
    protected I _identifier;
    protected V _value;
    //</editor-fold>

    @Override
    public boolean hasParent() {
        return _parent != null;
    }

    //<editor-fold desc="Getter and Setter">
    @Override
    public N parent() {
        return _parent;
    }

    public void setParent(N parent) {
        _parent = parent;
    }

    @Override
    public List<N> children() {
        return _children;
    }

    /**
     * Adds a node as sub node
     * @param child the node that is to be added
     * @throws NodeAlreadyExistsException if the child already has a parent
     */
    @Override
    public void addChild(N child) {
        if (child._parent != null && child._parent != this){
            throw new NodeAlreadyExistsException("Parent node in node " + child.toString() + " was already setAt!");
        }

        _children.add(child);
        child._parent = (N) this;
    }

    /**
     * Creates a new child node with the given properties as parameters
     * @param identifier of the new node
     * @param value the new node
     * @throws NodeAlreadyExistsException if the child already has a parent
     */
    public void addChild(I identifier, V value){
        addChild(makeChild(identifier, value, (N) this));
    }

    protected abstract N makeChild(I identifier, V value, N parent);

    /**
     * Removes a child with the specified identifier and returns it
     *
     * @param identifier of the child to remove
     * @return The removed child.
     * Or null if no child with this identifier exists.
     */
    public N removeChild(I identifier){
        N childNode = null;
        for (Iterator<N> iter = children().iterator(); childNode == null && iter.hasNext();){
            N cur = iter.next();

            if (cur.getIdentifier() == identifier){
                childNode = cur;
                childNode._parent = null;
                iter.remove(); // Can be called because the list of children is a ArrayList or LinkedList
            }
        }

        return childNode;
    }

    @Override
    public Stream<N> childStream() {
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
    public Iterator<N> iterator() {
        return null;
    }
}
