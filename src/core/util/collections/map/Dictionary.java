package core.util.collections.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Patrick
 * @description
 * @since 04.06.2017
 */
public class Dictionary<K, V> implements Map<K, V> {
    private final Map<K, V> _map;

    public Dictionary(HashMap<K, V> map) {
        _map = map;
    }

    @Override
    public int size() {
        return _map.size();
    }

    @Override
    public boolean isEmpty() {
        return _map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return _map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return _map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return _map.get(key);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void add(K key, V value){
        if (_map.put(key, value) != null) throw new RuntimeException("Key already exists!");
    }

    @Deprecated
    @Override
    public V put(K key, V value) {
        return _map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        V remove = _map.remove(key);
        if (remove == null) throw new RuntimeException("Key does not exist!");
        return remove;
    }

    @Deprecated
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        _map.putAll(m);
    }
    @Deprecated
    public void addAll(Map<? extends K, ? extends V> map) {
        map.forEach(this::add);
    }

    @Override
    public void clear() {
        _map.clear();
    }

    @Override
    public Set<K> keySet() {
        return _map.keySet();
    }

    @Override
    public Collection<V> values() {
        return _map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return _map.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return _map.equals(o);
    }

    @Override
    public int hashCode() {
        return _map.hashCode();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return _map.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        _map.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        _map.replaceAll(function);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return _map.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return _map.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return _map.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return _map.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return _map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return _map.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return _map.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return _map.merge(key, value, remappingFunction);
    }
}
