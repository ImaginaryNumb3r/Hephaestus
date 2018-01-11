package core.util.collections;

import core.exception.NoImplementationException;
import core.util.annotations.ToTest;
import org.jetbrains.annotations.NotNull;
import core.exception.InstanceNotAllowedException;
import core.tuple.Tuple;

import java.util.*;
import java.util.function.*;

/**
 * @author Patrick
 * @since 22.05.2017
 */
@ToTest
public final class Maps {

    /**
     * @throws InstanceNotAllowedException Cannot be instantiated
     */
    private Maps(){
        throw new InstanceNotAllowedException(getClass());
    }

    //<editor-fold desc="Factory Methods">
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
    //</editor-fold>

    //<editor-fold desc="Set Oriented Joins">
    /**
     * @param left map of values
     * @param right map of values
     * @param <V> Values that will be collected in the returning Set
     * @return A Set of all values in both maps.
     *         Because more than one value can be associated with a key, this cannot be represented as a map.
     */
    public static <K, V> Set<V> fullJoin(Map<K, V> left, Map<K, V> right){
        Set<V> intersection = new HashSet<>(left.values());
        intersection.addAll(left.values());
        intersection.addAll(right.values());

        return intersection;
    }

    /**
     * Equivalent to a left join or right join.
     * @param primary map of values
     * @param secondary map of values
     * @param <V> Values that will be collected in the returning Set
     * @return A Map of all values of the primary set along with all secondary values that share a key with the primary map.
     *         Because more than one value can be associated with a key, this cannot be represented as a map.
     */
    public static <K, V> Set<V> mappedJoin(Map<K, V> primary, Map<K, V> secondary){
        Set<V> intersection = new HashSet<>(primary.values());
        // Add all values from the right map if they have a corresponding key in the left map.
        customJoin(secondary, primary, primary::containsKey, intersection::add);

        return intersection;
    }

    /**
     * @param left map of values
     * @param right map of values
     * @param <V> Values that will be collected in the returning Set
     * @return A Set of all values that have an equivalent key in the other set.<br>
     *         Because more than one value can be associated with a key, this cannot be represented as a map.
     */
    public static <K, V> Set<V> innerJoin(Map<K, V> left, Map<K, V> right){
        Set<V> intersection = new HashSet<>(Math.min(left.size(), right.size()));
        customJoin(left, right, right::containsKey, intersection::add);
        customJoin(right, left, left::containsKey, intersection::add);

        return intersection;
    }
    //</editor-fold>

    public static <K, V> Map<K, V> mappedOuterJoin(Map<K, V> primary, Map<K, V> secondary){
        HashMap<K, V> disjoint = new HashMap<>();
        customJoin(primary, secondary, value -> !primary.containsKey(value), disjoint::put);
        customJoin(secondary, primary, value -> !secondary.containsKey(value), disjoint::put);

        // TODO: Also make Maps.fromEntries();
        return disjoint;
    }

    public static <K, V> Map<K, V> fullOuterJoin(Map<K, V> left, Map<K, V> right){
        HashMap<K, V> disjoint = new HashMap<>();
        customJoin(left, right, value -> !left.containsKey(value), disjoint::put);
        customJoin(right, left, value -> !right.containsKey(value), disjoint::put);

        return disjoint;
    }

    public static <K, V> Map<K, V> mappedInnerJoin(Map<K, V> primary, Map<K, V> secondary){
        HashMap<K, V> intersection = new HashMap<>();

        // Add all secondary values so they get overridden by equal values from the primary map.
        customJoin(secondary, primary, value -> !secondary.containsKey(value), intersection::put);
        customJoin(primary, secondary, value -> !primary.containsKey(value), intersection::put);

        return intersection;
    }

    public static <K, V> Map<K, V> mappedFullJoin(Map<K, V> primary, Map<K, V> secondary){
        HashMap<K, V> combined = new HashMap<>(Math.max(primary.size(), secondary.size()));

        // Add all secondary values so they get overridden by equal values from the primary map.
        combined.putAll(secondary);
        combined.putAll(primary);

        return combined;
    }

    public static <K, V> Map<K, V> innerJoinAll(Map<K, V>... maps){
        throw new NoImplementationException();
    }

    public static <K, V> Map<K, V> outerJoinAll(Map<K, V>... maps){
        throw new NoImplementationException();
    }

    /**
     * Custom join of two maps.
     * @apiNote It should be noted that a call to this method will only iterate the reference map and not both maps.
     * @param referenceMap The map that is being iterated.
     * @param valueMap The map whose values will be passed to the consumer.
     * @param predicate To test whether to perform the action or not
     * @param action The consumer that is used for the computation on a key-pair basis.
     */
    private static <K, V> void customJoin(Map<K, V> referenceMap, Map<K, V> valueMap,
                                          Predicate<K> predicate, Consumer<V> action){
        customJoin(referenceMap, valueMap, predicate, (key, value) -> action.accept(value));
    }

    /**
     * Custom join of two maps.
     * @apiNote It should be noted that a call to this method will only iterate the reference map and not both maps.
     * @param referenceMap The map that is being iterated.
     * @param valueMap The map whose values will be passed to the consumer.
     * @param predicate To test whether to perform the action or not
     * @param action The consumer that is used for the computation on a key-pair basis.
     */
    private static <K, V> void customJoin(Map<K, V> referenceMap, Map<K, V> valueMap,
                                          Predicate<K> predicate, BiConsumer<K, V> action){
        for (Map.Entry<K, V> entry : referenceMap.entrySet()) {
            K key = entry.getKey();
            if (predicate.test(key)){
                action.accept(key, valueMap.get(key));
            }
        }
    }

    @Deprecated
    public static <T> HashMap<Character, T> from(Supplier<T> supplier, Character... chars) {
        HashMap<Character, T> map = new HashMap<>(chars.length);

        for (int i = 0; i != chars.length; ++i){
            map.put(chars[i], supplier.get());
        }

        return map;
    }
}
