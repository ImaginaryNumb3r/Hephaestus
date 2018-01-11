package experimental.hybrid;

import core.datastructure.Lazy;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static core.datastructure.Lazy.lazily;

/**
 * Creator: Patrick
 * Created: 28.11.2017
 * Purpose:
 */
public class ListedMapImpl<K, V> extends MapListHybrid<V, K, V> implements ListedMap<K, V> {
    private Lazy<MappedListImpl<K, V>> _mappedListLazy = lazily(() -> new MappedListImpl<>(this));

    public ListedMapImpl(MappedListImpl<K, V> ts) {
        super(ts._linkedHashMap);
    }

    @Override
    public MappedList<K, V> toMappedList() {
        return null;
    }
/*
    @Override
    protected List<V> getValues() {
        return _linkedHashMap.values();
    } */

    @Override
    public boolean containsKey(Object key) {
        return _linkedHashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {

    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return null;
    }

    @NotNull
    @Override
    public Collection<V> values() {
        return null;
    }

    @NotNull
    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
