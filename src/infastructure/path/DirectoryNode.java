package infastructure.path;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @created 06.07.2016
 */
public class DirectoryNode extends PathNode implements Iterable<DirectoryNode>{

    public DirectoryNode(DirectoryNode prev, String nodeName) {
        super(prev, nodeName);
    }

    @Override
    public DirectoryNode getPrev() {
        return super.getPrev();
    }

    @Override
    PathNodeList copy() {
        return copyNodes(this);
    }

    @Override
    // TODO: could be put into PathNode?
    public Iterator<DirectoryNode> iterator() {
        return new DirectoryIterator();
    }

    /**
     * Returns an iterator providing the names of the individual nodes
     * @return an iterator providing the names of the individual nodes
     */
    public Iterator<String> stringIterator(){
        Iterator<? extends PathNode> iterator = iterator();
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public String next() {
                return iterator.next()._nodeName;
            }
        };
    }
    // =================
    //     InnerIterator
    // =================

    /**
     * An iterator that returns the individual nodes
     */
    private class DirectoryIterator implements Iterator<DirectoryNode>{
        private DirectoryNode _cur; // lazy

        @Override
        public boolean hasNext() {
            boolean hasNext;
            if (_cur != null){
                hasNext = _cur.hasPrev();

            } else {
                // Empty paths do not count as elements
                hasNext = !DirectoryNode.this._nodeName.isEmpty();
            }

            return hasNext;
        }

        @Override
        public DirectoryNode next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            if (_cur == null){
                _cur = DirectoryNode.this;
            } else {
                _cur = _cur.getPrev();
            }

            return _cur;
        }
    }
}