package experimental.hybrid;

import core.datastructure.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static core.datastructure.Lazy.lazily;

/**
 * Creator: Patrick
 * Created: 28.11.2017
 * Purpose:
 */
public class MappedListImpl<T, O> extends MapListHybrid<O, T, T> implements MappedList<T, O> {
    private Lazy<ListedMap<T, O>> _listedMapLazy = lazily(() -> new ListedMapImpl<>(this));

    public MappedListImpl(int loadFactor) {
        super(new LinkedHashMap<>(loadFactor));
    }

    public MappedListImpl(ListedMapImpl<T, O> listedMap) {
        super(listedMap._linkedHashMap);
    }

    @Override
    public ListedMap<T, O> toListedMap() {
        return _listedMapLazy.get();
    }

    @Override
    public boolean contains(Object object) {
        return getValues().contains(object);
    }

    @Override
    public boolean add(T t) {
        return getValues().add(t);
    }

    @Override
    public boolean remove(Object object) {
        return getValues().remove(object);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> collection) {
        return getValues().containsAll(collection);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> collection) {
        return getValues().addAll(collection);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> collection) {
        return getValues().addAll(collection);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> collection) {
        return getValues().removeAll(collection);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return false;
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
        return null;
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
        return null;
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
