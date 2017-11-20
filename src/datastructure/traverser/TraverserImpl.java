package datastructure.traverser;

import core.util.annotations.ToTest;
import datastructure.tree.node.TreeNodeReader;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Creator: Patrick
 * Created: 18.11.2017
 * Purpose:
 */
@ToTest
public class TraverserImpl<T extends Iterable<T>> implements Traverser<T>{
    private Iterator<T> _iterator;
    private Stack<T> _parents;
    private T _current;

    public TraverserImpl(T root) {
        _parents = new Stack<>();
        _current = root;
    }

    @Override
    public boolean hasParent() {
        return !_parents.isEmpty();
    }

    @Override
    public void enterParent() {
        if (!hasParent()){
            throw new NoSuchElementException("Root of tree cannot traverse to parent");
        }
        _current = _parents.pop();
    }

    @Override
    public boolean hasChildren() {
        return _current.iterator().hasNext();
    }

    @Override
    public boolean hasNextChild() {
        if (_iterator == null){
            _iterator = _current.iterator();
        }

        return _iterator.hasNext();
    }

    @Override
    public TreeNodeReader<T> nextChild() {
        if (_iterator == null){
            _iterator = _current.iterator();
        }

        return () -> _iterator.next();
    }

    @Override
    public void enter() {
        _parents.add(_current);
        _current = nextChild().getValue();
        _iterator = null;
    }

    @Override
    public TreeNodeReader<T> value() {
        return () -> _current;
    }
}
