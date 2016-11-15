package infastructure.datastructure.traverser;

import infastructure.datastructure.traverser.interfaces.TreeTraverser;
import infastructure.filetype.HDirectory;
import infastructure.filetype.HFile;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author Patrick
 * @created 28.05.2016
 * @purpose Tree Iterator which traverses over Nodes of a Tree composed of DirectoryNodes
 */
public abstract class DirectoryTraverser<T extends HDirectory> implements TreeTraverser<T> {
    /** The current directory in the file system hierarchy */
    protected T _curDir;
    /** The next directory that would be entered with a "enter". In other words, it is the "current" node in the iteration */
    protected T _destDir;
    /** A stack of Iterators from the directories which were used to reach the current level in the hierarchy */
    protected Stack<NodeIterator<T>> _prevIter;
    /** The Iterator of the current hierarchy. Is being added to the stack of previous Iterators (_prevIter) when entering a new level */
    protected NodeIterator<T> _curIter;

    /** The starting point of the traverser. When moving back you cannot go beyond this Node. */
    protected final T _root;

    public DirectoryTraverser(final T root) {
        _root = root;
        _curDir = _root ;
        _destDir = null;
        _prevIter = new Stack<>();

        //noinspection unchecked
        _curIter = (NodeIterator<T>) root.iterator();
    }

    // =====================
    //   Abstract Methods
    // =====================

    public abstract boolean addFile(HFile file);

    public abstract boolean addDirectory(HDirectory directory);

    // =====================
    //   Interface Methods
    // =====================

    @Override
    public boolean hasNext() {
        return _curIter.hasNext();
    }

    @Override
    public boolean hasPrevious() {
        return _curIter.hasPrevious();
    }

    @Override
    public void back() {
        if (!_prevIter.isEmpty()){
            _curIter = _prevIter.pop();
            _curDir = _curIter.current();
            _destDir = null;
        }
        else {
            throw new NoSuchElementException("Cannot move up beyond root");
        }
    }

    /**
     *
     * @param newIter
     */
    public void enter(NodeIterator<T> newIter) {
        _prevIter.add(_curIter);
        _curDir = _destDir;
        _destDir = null;

        _curIter = newIter;
    }

    @Override
    public T current() {
        return _destDir;
    }

    @Override
    public T next() {
        _destDir = _curIter.next();

        return _destDir;
    }

    @Override
    public T previous() {
        _destDir = _curIter.previous();

        return _destDir;
    }
}
