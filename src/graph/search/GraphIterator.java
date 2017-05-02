package graph.search;

import com.sun.istack.internal.NotNull;
import core.exception.EnumException;
import core.util.contracts.Contract;

import java.util.*;

/**
 * @author Patrick
 * @since 01.05.2017
 */
@SuppressWarnings("WeakerAccess")
public class GraphIterator<N extends Iterable<N>> implements Iterator<N> {
    protected final NodeCollection<N> _collection;
    protected final N _root;
    protected N _current;


    protected GraphIterator(NodeCollection<N> collection, N root){
        _collection = collection;
        _root = root;
        _current = _root;

        collection.enqueue(_current);
    }

    public static <T extends Iterable<T>> GraphIterator<T> from
            (T source, @NotNull GraphSearchStrategy strategy){
        Contract.checkNull(strategy);
        NodeCollection<T> collection;

        switch (strategy){
            case BREADTH_FIRST:
                collection = NodeCollection.toCollection(new LinkedList<>());
                break;
            case DEPTH_FIRST:
                collection = NodeCollection.toCollection(new Stack<>());
                break;
            default: throw new EnumException(strategy, strategy.getClass());
        }

        return new GraphIterator<>(collection, source);
    }

    @Override
    public boolean hasNext() {
        return !_collection.isEmpty();
    }

    @Override
    // TODO: Depth First doesn't work properly yet
    public N next() {
        if (!hasNext()) throw new NoSuchElementException();

        N node = _collection.next();
        node.forEach(_collection::enqueue);

        return node;
    }

    protected interface NodeCollection<T>{

        void enqueue(T item);

        T next();

        boolean isEmpty();

        static <T> NodeCollection<T> toCollection(Queue<T> queue){
            return new NodeCollection<T>() {
                public void enqueue(T item) {
                    queue.add(item);
                }
                public T next() {
                    return queue.remove();
                }
                public boolean isEmpty(){
                    return queue.isEmpty();
                }
            };
        }

        static <T> NodeCollection<T> toCollection(Stack<T> stack) {
            return new NodeCollection<T>() {
                public void enqueue(T item) {
                    stack.push(item);
                }
                public T next() {
                    return stack.pop();
                }
                public boolean isEmpty(){
                    return stack.isEmpty();
                }
            };
        }
    }
}