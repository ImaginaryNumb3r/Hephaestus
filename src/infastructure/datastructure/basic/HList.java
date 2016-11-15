package infastructure.datastructure.basic;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Patrick
 * @created 02.07.2016
 */
public class HList<T> implements List<T> {
    private Node<T> _head;
    private Node<T> _tail;
    private int _size;

    // ==================
    //   Constructors
    // ==================

    public HList() {
        _head = null; // lazy
        _tail = null; // lazy
        _size = 0;
    }

    // ==================
    //   Constructors
    // ==================

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean contains = false;
        for (Iterator<T> iter = this.iterator(); !contains && iter.hasNext(); ) {
            T cur = iter.next();

            if (o == cur || cur.equals(o)){
                contains = true;
            }
        }

        return contains;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[_size];

        int i = 0;
        for (T t : this) {
            array[i++] = t;
        }

        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T1> T1[] toArray(T1[] a) {
        a = (T1[]) Array.newInstance(a.getClass().getComponentType(), _size);

        int i = 0;
        Object[] result = a;
        for (Node<T> node = _head; node != null; node = node.getNext()){
            result[i++] = node.getValue();
        }

        if (a.length > _size){
            a[_size] = null;
        }


        return a;
    }

    @Override
    public boolean add(T value) {
        ++_size;
        if (_head == null){
            _head = new Node<>(null, value, null);
            _tail = _head;
        } else {
            Node<T> newNode = new Node<>(null, value, _head);
            _head.setPrev(newNode);
            _head = newNode;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        --_size;
        boolean removed = false;
        for (Iterator<T> iter = iterator(); !removed && iter.hasNext();){
            T cur = iter.next();

            if (cur == o || cur.equals(o)){
                iter.remove();
                removed = true;
            }
        }

        return removed;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeAll(Collection<?> elements) {
        HashMap<Object, Boolean> removeMap = new HashMap<>(elements.size());

        for (Object element : elements) {
            removeMap.put(element, true);
        }

        int removed = 0;
        for (Iterator<T> iter = iterator(); (removed < removeMap.size()) && iter.hasNext();){
            T cur = iter.next();

            if (removeMap.get(cur)){
                iter.remove();
                ++removed;
            }
        }

        _size =- removed;
        return removed == removeMap.size();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        _head = null;
        _tail = null;
        _size = 0;
    }

    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        return node.getValue();
    }

    @Override
    public T set(int index, T value) {
        Node<T> node = getNode(index);
        T prevValue = node.getValue();
        node.setValue(value);

        return prevValue;
    }

    /**
     * Helper Method
     * @param index
     * @return
     */
    private Node<T> getNode(int index){
        if (index < 0){
            throw new IndexOutOfBoundsException();
        }

        ListIterator<T> iter = listIterator();
        Node<T> node = iter.nextNode();
        for (int i = 0; i != index; ++i){
            node = iter.nextNode();
        }

        if (node == null){
            throw new NoSuchElementException();
        }


        return node;
    }

    @Override
    public void add(int index, T element) {
        Node<T> node = getNode(index);
        Node<T> nextNode = node.getNext();
        Node<T> newNode = new Node<>(node, element, nextNode);

        node.setNext(newNode);
        nextNode.setPrev(newNode);
    }

    @Override
    public T remove(int index) {
        Node<T> node = getNode(index);
        Node prev = node.getPrevious();
        Node next = node.getNext();


        return node.getValue();
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        int i = 0;

        for (Iterator<T> iterator = this.iterator(); (index == -1) && iterator.hasNext(); ) {
            T element = iterator.next();
            if (o == element || element.equals(o)) {
                index = i;
            }
            ++i;
        }

        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        int i = 0;

        for (T element : this) {
            if (o == element || element.equals(o)){
                index = i;
            }
            ++i;
        }

        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0){
            throw new IndexOutOfBoundsException();
        }
        ListIterator<T> iter = new ListIterator<>();

        for (int i = 0; i != index; ++i){
            iter.next();
        }


        return iter;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    // =================
    //   Inner Classes
    // =================

    /**
     * Inner node for concatenating values
     */
    private class Node<T>{
        private Node<T> _prev;
        private T _value;
        private Node<T> _next;

        public Node(Node<T> prev, T value, Node<T> next) {
            _prev = prev;
            _value = value;
            _next = next;
        }

        public void delete(){
            if (hasPrevious()){
                getPrevious().setNext(getNext());
            }
            if (hasNext()){
                getNext().setPrev(getPrevious());
            }
        }

        public Node<T> getPrevious() {
            return _prev;
        }

        public void setPrev(Node<T> prev) {
            _prev = prev;
        }

        public T getValue() {
            return _value;
        }

        public void setValue(T value) {
            _value = value;
        }

        public Node<T> getNext() {
            return _next;
        }

        public void setNext(Node<T> next) {
            _next = next;
        }

        public boolean hasPrevious(){
            return _prev != null;
        }

        public boolean hasNext(){
            return _next != null;
        }

        @Override
        public String toString() {
            return "[ " + _prev + " | " + _value + " | " + _next + " ]";
        }
    }

    /**
     * ListIterator for iterating through the list
     */
    private class ListIterator<T> implements HListIterator<T>{
        private Node<T> _current;
        private int _index;


        public ListIterator() {
            _current = null;
            _index = 0;
        }

        @Override
        public boolean hasNext() {
            return _current == null || _current.hasNext();
        }

        @Override
        public T next() {
            return nextNode().getValue();
        }

        @Override
        public boolean hasPrevious() {
            return _current != null && _current.hasPrevious();
        }

        @Override
        public T previous() {
            if (_current == null){
                throw new NoSuchElementException();
            }

            if (_current.hasPrevious()){
                --_index;
                _current = _current.getPrevious();
            } else {
                throw new NoSuchElementException();
            }

            return _current.getValue();
        }

        @Override
        public int nextIndex() {
            int index = _index;
            if (hasNext()){
                ++_index;
            }

            return index;
        }

        @Override
        public int previousIndex() {
            int index = _index;
            if (hasPrevious()){
                --_index;
            }

            return index;
        }

        @Override
        public void remove() {
            --_size;
            _current.delete();
            /*
            Node prev = _current.getPrevious();
            Node next = _current.getPrev();

            prev.setNext(next);
            next.setNext(prev); */
        }

        @Override
        public void set(T o) {

        }

        @Override
        public void add(T o) {

        }

        public Node<T> nextNode() {
            if (_current == null) {
                _current = (Node<T>) _head;

                if (_current == null) {
                    throw new NoSuchElementException();
                }
            } else {

                if (_current.hasNext()) {
                    ++_index;
                    _current = _current.getNext();
                } else {
                    throw new NoSuchElementException();
                }
            }

            return _current;
        }

        @Override
        public String toString() {
            String toString;

            if(_current == null){
                toString = "null";
            } else {
                toString = _current.toString();
            }

            return toString;
        }

        @Override
        public T current() {
            return _current.getValue();
        }
    }
}