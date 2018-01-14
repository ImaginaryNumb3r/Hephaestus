package stream.iteration;

import core.util.collections.iteration.Iterables;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Patrick
 * @since 14.01.2018
 */
public abstract class IterationSink<T> implements Consumer<T>, Runnable {
    private final Iterator<T> _aggregator;

    public IterationSink(Iterator<T> aggregator) {
        _aggregator = aggregator;
    }

    @Override
    public abstract void accept(T t);

    @Override
    public void run() {
        for (T item : Iterables.from(_aggregator)) {
            accept(item);
        }
    }
}
