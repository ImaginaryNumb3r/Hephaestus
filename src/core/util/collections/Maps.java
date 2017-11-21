package core.util.collections;

import org.jetbrains.annotations.NotNull;
import core.exception.InstanceNotAllowedException;
import core.tuple.Tuple;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;
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
        throw new InstanceNotAllowedException(getClass());
    }

    //<editor-fold desc="From Keys">
    //<editor-fold desc="From Iterable">
    public static <K, V> HashMap<K, V> fromKeys(@NotNull Iterable<K> iterable, @NotNull Supplier<V> initializer){
        return fromKeys(iterable, 16, initializer);
    }

    public static <K, V> HashMap<K, V> fromKeys(@NotNull Iterable<K> iterable, int size, @NotNull Supplier<V> initializer){
        return fromKeys(iterable, size, key -> initializer.get());
    }

    public static <K, V> HashMap<K, V> fromKeys(@NotNull Iterable<K> iterable, @NotNull Function<K, V> initializer){
        return fromKeys(iterable, 16 /* Default load factory */, initializer);
    }

    public static <K, V> HashMap<K, V> fromKeys(@NotNull Iterable<K> iterable, int size, @NotNull Function<K, V> mapper){
        HashMap<K, V> map = new HashMap<>(size);
        for (K curKey : iterable) {
            map.put(curKey, mapper.apply(curKey));
        }
        return map;
    }
    //</editor-fold>

    //<editor-fold desc="From Collection">
    public static <K, V> HashMap<K, V> fromKeys(@NotNull Collection<K> collection, @NotNull Function<K, V> mapper){
        return fromKeys(collection, collection.size(), mapper);
    }

    public static <K, V> HashMap<K, V> fromKeys(@NotNull Collection<K> collection, @NotNull Supplier<V> initializer){
        return fromKeys(collection, collection.size(), ignore -> initializer.get());
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="From Values">
    //<editor-fold desc="From Iterable">

    public static <K, V> HashMap<K, V> fromValues(@NotNull Iterable<V> iterable, @NotNull Function<V, K> mapper){
        return fromValues(iterable, 16 /* Default load factory */, mapper);
    }

    public static <K, V> HashMap<K, V> fromValues(@NotNull Iterable<V> iterable, int size, @NotNull Function<V, K> mapper){
        HashMap<K, V> map = new HashMap<>(size);
        for (V curValue : iterable) {
            map.put(mapper.apply(curValue), curValue);
        }
        return map;
    }
    //</editor-fold>

    //<editor-fold desc="From Collection">
    public static <K, V> HashMap<K, V> fromValues(@NotNull Collection<V> collection, @NotNull Function<V, K> mapper){
        return fromValues((Iterable<V>) collection, mapper);
    }

    //</editor-fold>
    //</editor-fold>

    //<editor-fold desc="From Tuple">
    public static <K, V, T extends Tuple<K, V>> HashMap<K, V> fromPair(@NotNull Iterable<T> iterable){
        return fromPair(iterable, 16 /* Default load factor */);
    }

    public static <K, V, T extends Tuple<K, V>> HashMap<K, V> fromPair(@NotNull Collection<T> collection){
        return fromPair(collection, collection.size());
    }

    public static <K, V, T extends Tuple<K, V>> HashMap<K, V> fromPair(@NotNull Iterable<T> iterable, int size){
        HashMap<K, V> map = new HashMap<>(size);
        for (T keyPair : iterable) {
            K key = keyPair.getA();
            V value = keyPair.getB();
            map.put(key, value);
        }
        return map;
    }
    //</editor-fold>

    @Deprecated
    public static <T> HashMap<Character, T> from(Supplier<T> supplier, Character... chars) {
        HashMap<Character, T> map = new HashMap<>(chars.length);

        for (int i = 0; i != chars.length; ++i){
            map.put(chars[i], supplier.get());
        }

        return map;
    }
}
