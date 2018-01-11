package experimental.hybrid;

import core.exception.NoImplementationException;
import core.util.collections.iteration.Iterators;
import org.jetbrains.annotations.NotNull;
import stream.Count;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Creator: Patrick
 * Created: 01.12.2017
 * Purpose:
 */
public class ListProxy<T> implements List<T> {
    private Collection<T> _collection;

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        throw new NoImplementationException();
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        assertBounds(index);
        Iterator<T> iterator = iterator();
        Count.repeat(index, iterator::next);

        return Iterators.from(iterator.next(), ignoreLast -> iterator.next() );
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        assertBounds(fromIndex, toIndex);
        if (fromIndex > toIndex) throw new IndexOutOfBoundsException();

        ListIterator<T> iterator = listIterator(fromIndex);
        final int count = toIndex - fromIndex;
        ArrayList<T> list = new ArrayList<>();

        Count.repeat(count, () -> list.add(iterator.next()));
        return list;
    }

    private void assertBounds(int index){
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void assertBounds(int... indices){
        for (int index : indices) {
            assertBounds(index);
        }
    }

    //<editor-fold desc="Delegated">
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
    public Iterator<T> iterator() {
        return _collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return _collection.toArray();
    }

    @Override
    public <T1> T1[] toArray(@NotNull T1[] a) {
        return _collection.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return _collection.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return _collection.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return _collection.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return _collection.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return _collection.removeAll(c);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return _collection.removeIf(filter);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return _collection.retainAll(c);
    }

    @Override
    public void clear() {
        _collection.clear();
    }

    @Override
    public boolean equals(Object o) {
        return _collection.equals(o);
    }

    @Override
    public int hashCode() {
        return _collection.hashCode();
    }

    @Override
    public Spliterator<T> spliterator() {
        return _collection.spliterator();
    }

    @Override
    public Stream<T> stream() {
        return _collection.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return _collection.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        _collection.forEach(action);
    }
    //</editor-fold>
}
