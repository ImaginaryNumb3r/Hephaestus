package core.util.collections;

import core.util.collections.iteration.Iterables;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Patrick
 * @since 28.05.2017
 */
public final class Sets {

    public <T> HashSet from(T... items){
        return from(Iterables.from(items), items.length);
    }

    public <T> HashSet from(Iterable<T> iterable){
        return from(iterable, 16); // Default Capacity of HashMap, as of JDK 9.
    }

    public <T> HashSet from(Iterable<T> iterable, int size){
        HashSet<T> set = new HashSet<>(size);
        iterable.forEach(set::add);
        return set;
    }

    public <T> HashSet from(Collection<T> collection){
        HashSet<T> set = new HashSet<>(collection.size());
        set.addAll(collection);
        return set;
    }
}
