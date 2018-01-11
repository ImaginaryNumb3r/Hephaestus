package experimental.hybrid;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;


/**
 *
 * @param <K> Key
 * @param <V> Value
 * @param <T> Element
 */
public abstract class MapListHybrid<K, V, E> {
    protected LinkedHashMap<K, V> _linkedHashMap;

    public MapListHybrid(LinkedHashMap<K, V> linkedHashMap) {
        _linkedHashMap = linkedHashMap;
    }

    protected List<E> getValues() {
        return getValues();
    }

    public int size() {
        return getValues().size();
    }

    public boolean isEmpty() {
        return getValues().isEmpty();
    }

    @NotNull
    public ListIterator<E> iterator() {
        return getValues().listIterator();
    }

    @NotNull
    public Object[] toArray() {
        return getValues().toArray();
    }

    @NotNull
    public <T> T[] toArray(@NotNull T[] array) {
        return getValues().toArray(array);
    }

    public void clear() {
        getValues().clear();
    }

    public E get(int index) {
        return getValues().get(index);
    }

}
