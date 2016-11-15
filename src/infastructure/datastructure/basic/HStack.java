package infastructure.datastructure.basic;

import java.util.*;

/**
 * @author Patrick
 * @created 05.08.2016
 */
public class HStack<T> implements Collection<T> {
    private StackNode _top;
    private StackNode _root;
    private int _size;

    public HStack() {
        _top = null;
        _root = null;
        _size = 0;
    }


    // Interface Methods

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size != 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean contains = false;

        if (asType(o) != null){
            for (Iterator<T> iter = iterator(); !contains && iter.hasNext();){
                contains = iter.next().equals(o);
            }
        }

        return contains;
    }

    private final T asType (Object object){
        T type;

        try {
            //noinspection unchecked
            type = (T) object;
        } catch (ClassCastException ex){
            type = null;
        }

        return type;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T element) {
        _top = new StackNode(_top, element);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean removed = false;

        if (asType(o) != null){
            for (Iterator<T> iter = iterator(); !removed && iter.hasNext();){
                if (iter.next().equals(o)){
                    removed = true;
                    iter.remove();
                }
            }
        }

        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean validObjects = true;
        int containCount = 0;

        HashMap<T, Boolean> map = new HashMap<>(c.size());

        T type; // Fill Map
        for (Iterator<?> iter = c.iterator(); validObjects && iter.hasNext();){
            Object cur = iter.next();

            if  ((type = asType(cur)) != null){
                map.put(type, true);

            } else {
                validObjects = false;
            }
        }

        for (T cur : this) {
            if (map.get(cur) != null) {
                ++containCount;
            }
        }

        return validObjects && containCount == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T type : c) {
            add(type);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        _size = 0;
    }

    private class StackIterator implements Iterator<T>{
        private StackNode _cur;

        @Override
        public boolean hasNext() {
            if (_cur == null){
                _cur = _top;
            }

            return _cur != null;
        }

        @Override
        public T next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }

            _cur = _cur._prev;
            return _cur._value;
        }
    }

    /**
     * Inner Class representing a node in the stack
     */
    private class StackNode{
        private StackNode _prev;
        private T _value;

        public StackNode(StackNode prev, T value) {
            _prev = prev;
            _value = value;
        }
    }
}