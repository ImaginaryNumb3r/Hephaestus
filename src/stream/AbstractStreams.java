package stream;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public abstract class AbstractStreams<T> {
    protected final Spliterator<T> _spliterator;

    //<editor-fold desc="Constructors">
    public AbstractStreams(@NotNull  T[] array){
        this(Spliterators.spliterator(array, Spliterator.ORDERED));
    }

    public AbstractStreams(@NotNull List<T> list){
        this(list.spliterator());
    }

    public AbstractStreams(@NotNull Spliterator<T> spliterator) {
        _spliterator = spliterator;
    }
    //</editor-fold>

    public Stream<T> stream(){
        return StreamSupport.stream(_spliterator, isParallel());
    }

    protected abstract boolean isParallel();
}
