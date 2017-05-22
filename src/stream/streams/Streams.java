package stream.streams;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;
import core.util.contracts.Contract;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

import static core.util.contracts.Contract.checkNull;
import static core.util.contracts.Contract.throwIf;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public class Streams<T> extends AbstractStreams<T> {

    //<editor-fold desc="Constructors">
    protected Streams(T[] array) {
        super(array);
    }

    protected Streams(List<T> list) {
        super(list);
    }

    protected Streams(Spliterator<T> spliterator) {
        super(spliterator);
    }

    protected Streams(Iterator<T> iterator, int size) {
        super(iterator, size);
    }
    //</editor-fold>

    public static <T> Stream<T> stream(T[] array){
        checkNull(array, "array");
        return new Streams<>(array).stream();
    }

    public static <T> Stream<T> stream(Iterator<T> iterator, int size){
        checkNull(iterator, "iterator");
        Contract.checkNegative(size);
        return new Streams<>(iterator, size).stream();
    }
    public static <T> Stream<T> stream(List<T> list){
        checkNull(list, "list");
        return new Streams<>(list).stream();
    }

    public static <T> Stream<T> stream(@NotNull Spliterator<T> spliterator){
        checkNull(spliterator, "spliterator");
        if (spliterator == null) throw new ParameterNullException();
        return new Streams<>(spliterator).stream();
    }

    @Override
    public boolean isParallel() {
        return false;
    }
}
