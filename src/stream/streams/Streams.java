package stream.streams;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public class Streams<T> extends AbstractStreams<T> {
    public Streams(T[] array) {
        super(array);
    }

    public Streams(List<T> list) {
        super(list);
    }

    public Streams(Spliterator<T> spliterator) {
        super(spliterator);
    }

    public static <T> Stream<T> stream(T[] array){
        if (array == null) throw new IllegalArgumentException("paramter may not be null");
        return new Streams<>(array).stream();
    }

    public static <T> Stream<T> stream(List<T> list){
        if (list == null) throw new IllegalArgumentException("paramter may not be null");
        return new Streams<>(list).stream();
    }

    public static <T> Stream<T> stream(Spliterator<T> spliterator){
        if (spliterator == null) throw new IllegalArgumentException("paramter may not be null");
        return new Streams<>(spliterator).stream();
    }

    @Override
    public boolean isParallel() {
        return false;
    }
}
