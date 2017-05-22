package infastructure.datastructure.traverser;

import infastructure.datastructure.traverser.interfaces.ListSplitter;
import infastructure.filetype.HDirectory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Patrick
 * @created 29.05.2016
 */
public abstract class NodeIterator<T extends HDirectory> implements ListIterator<T>, ListSplitter<T> {
    /** The current Node of the iteration */
    protected Node _curNode;
    /** list that is currently being iterated */
    protected List<T> _nodes;
    /** Source list that is fallen back in case the InnerIterator is setAt to reset */
    protected final List<T> _init;

    private boolean _isSplit;
    private int _index;

    public NodeIterator(List<T> init) {
        _init = copyList(init);
        reset();
    }

    private List<T> copyList(List<T> list){
        List<T> copy = new LinkedList<>();

        for (T node : list) {
            copy.add(node);
        }

        return copy;
    }

    @Override
    public boolean hasNext() {
        return _curNode == null || _curNode.hasNext();
    }

    @Override
    public T next() {
        ++_index;

        if (_curNode != null){
            _curNode = _curNode.getNext();
        } else {
            // TODO: Make own LinkedList
            // _curNode = new Node(null, _init.get(0), _init.get(1));
        }

        return _curNode.getValue();
    }

    @Override
    public boolean hasPrevious() {
        return _curNode == null || _curNode.hasPrevious();
    }

    @Override
    public T previous() {
        --_index;
        _curNode = _curNode.getPrevious();
        return _curNode.getValue();
    }

    public T current() {
        return _curNode.getValue();
    }

    public void reset() {
        _curNode = null;
        _nodes = _init;
        _isSplit = false;
        _index = 0;
    }


    public int size(){
        int size;
        if (_isSplit){
            size = _index + 1;
        } else {
            size = _nodes.size();
        }

        return size;
    }

    public void addNode(Node node) {
        throw new NotImplementedException();
    }

    @Override
    public int nextIndex() {
        throw new NotImplementedException();
    }

    @Override
    public int previousIndex() {
        throw new NotImplementedException();
    }

    @Override
    public void remove() {
        throw new NotImplementedException();
    }

    public void set(T value) {
        throw new NotImplementedException();
    }

    public void add(T value) {
        throw new NotImplementedException();
    }

    public List<T> split() {
        throw new NotImplementedException();
    }

    /******************
     * Node Inner Class
     ******************/
    private class Node{
        private Node _next;
        private T _value;
        private Node _previous;

        public Node(Node previous, T value, Node next) {
            _previous = previous;
            _value = value;
            _next = next;
        }

        public Node getNext() {
            return _next;
        }

        public void setNext(Node next) {
            _next = next;
        }

        public T getValue() {
            return _value;
        }

        public void setValue(T value) {
            _value = value;
        }

        public Node getPrevious() {
            return _previous;
        }

        public void setPrevious(Node previous) {
            _previous = previous;
        }

        public boolean hasNext(){
            return _next != null;
        }

        public boolean hasPrevious(){
            return _previous != null;
        }
    }
}