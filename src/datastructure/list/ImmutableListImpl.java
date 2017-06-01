package datastructure.list;

import java.util.*;

/**
 * @author Patrick
 * @since 26.05.2017
 */
public class ImmutableListImpl<T> implements ImmutableList<T> {
    private final List<T> _collection;

    public ImmutableListImpl(List<T> collection) {
        _collection = collection;
    }

    public ImmutableListImpl() {
        this(new LinkedList<>());
    }

    @Override
    public int size() {
        return _collection.size();
    }

    @Override
    public boolean isEmpty() {
        return _collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return _collection.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return _collection.containsAll(c);
    }

    @Override
    public T get(int index) {
        return _collection.get(index);
    }

    @Override
    public ListIterator<T> listIterator() {
        return _collection.listIterator();
    }
}
