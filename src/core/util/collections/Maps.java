package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.tuple.Tuple;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 22.05.2017
 */
public final class Maps {

    /**
     * @throws UnsupportedOperationException Cannot be instantiated
     */
    private Maps(){
        throw new UnsupportedOperationException("Cannot instantiate class \"Iterators\"");
    }

    public static <K, V> HashMap<K, V> from(@NotNull Iterable<K> iterable, @NotNull Supplier<V> initializer){
        return from(iterable, 0, initializer);
    }

    public static <K, V> HashMap<K, V> from(@NotNull Iterable<K> iterable, int size, @NotNull Supplier<V> initializer){
        HashMap<K, V> map = new HashMap<>(size);
        for (K cur : iterable) {
            map.put(cur, initializer.get());
        }
        return map;
    }

    public static <K, V> HashMap<K, V> from(@NotNull Collection<K> collection, @NotNull Supplier<V> initializer){
        return from(collection, collection.size(), initializer);
    }

    public static <K, V, T extends Tuple<K, V>> HashMap<K, V> from(@NotNull Iterable<T> iterable){
        return from(iterable, 0);
    }

    public static <K, V, T extends Tuple<K, V>> HashMap<K, V> from(@NotNull Collection<T> collection){
        return from(collection, collection.size());
    }

    public static <K, V, T extends Tuple<K, V>> HashMap<K, V> from(@NotNull Iterable<T> iterable, int size){
        HashMap<K, V> map = new HashMap<>(size);
        for (T keyPair : iterable) {
            K key = keyPair.getA();
            V value = keyPair.getB();
            map.put(key, value);
        }
        return map;
    }
}
