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

}
