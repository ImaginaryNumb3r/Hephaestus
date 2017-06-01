package core.util.collections.iteration;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public final class Iterables {

    public static <T> Iterable<T> from(Iterator<T> iterator){
        return () -> iterator;
    }

    public static <T> Iterable<T> from(T... objects){
        return () -> Iterators.from(objects);
    }

    public static <T> boolean equals(Iterable<T> iterable1, Iterable<T> iterable2){
        return Iterators.equals(iterable1.iterator(), iterable2.iterator());
    }
}
