package core.util.collections;

import core.util.collections.iteration.Iterables;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Patrick
 * @since 28.05.2017
 */
public final class Sets {

    public static <T> Set<T> from(T... items){
        return from(Iterables.from(items), items.length);
    }

    public static <T> Set<T> from(Iterable<T> iterable){
        return from(iterable, 16); // Default Capacity of HashMap, as of JDK 9.
    }

    public static <T> Set<T> from(Iterable<T> iterable, int size){
        HashSet<T> set = new HashSet<>(size);
        iterable.forEach(set::add);
        return set;
    }

    public static <T> Set<T> from(Collection<T> collection){
        HashSet<T> set = new HashSet<>(collection.size());
        set.addAll(collection);
        return set;
    }
}
