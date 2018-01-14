package stream.iteration;

/**
 * @author Patrick
 * @since 14.01.2018
 * Like an Iterator, but due to clashing generics we cannot simply take Iterator.
 */
public interface Aggregator<T> {

    boolean hasNext();

    T input();

}
