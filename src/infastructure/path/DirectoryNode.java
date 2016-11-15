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


    // =================
    //     Iterator
    // =================

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