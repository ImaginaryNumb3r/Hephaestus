package stream.iteration;

import core.util.collections.iteration.Iterators;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author Patrick
 * @since 14.01.2018
 * An IterationSource is a readable node in the chain of Iteration operations.
 * It might be the node providing the original source values or mapped data.
 *
 * @implNote note that this class is not Iterable because it could have multiple iterators
 * and because Iterables are not allowed to have state.
 */
public abstract class IterationSource<in, out> implements Aggregator<in>, Provider<out> {
    private final Iterator<in> _aggregator;

    public IterationSource(Iterator<in> aggregator) {
        _aggregator = aggregator;
    }

    @Override
    public out output() {
        return compute(input());
    }

    @Override
    public boolean hasNext() {
        return _aggregator.hasNext();
    }

    @Override
    public in input() {
        return _aggregator.next();
    }

    public Iterator<in> sourceIterator(){
        return _aggregator;
    }

    public Iterator<out> sinkIterator(){
        return Iterators.map(_aggregator, this::compute);
    }

    protected abstract out compute(in item, int index);

    protected abstract out compute(in item);


    public static <in, out> IterationSource<in, out> of(Iterator<in> iterator, Function<in, out> computation){
        return new IterationSourceImpl<>(iterator, computation);
    }

    public static <in, out> IterationSource<in, out> of(Iterator<in> iterator, BiFunction<in, Integer, out> computation){
        return new IterationSourceImpl<>(iterator, computation);
    }

    /**
     * @param iterator containing the values for the Iteration
     * @return a simple source which provides the value of the iterator
     */
    public static <T> IterationSource<T, T> of(Iterator<T> iterator){
        return new IterationSourceImpl<>(iterator, UnaryOperator.identity());
    }

    private static class IterationSourceImpl<I, O> extends IterationSource<I, O>{
        private final BiFunction<I, Integer, O> _computation;

        public IterationSourceImpl(Iterator<I> aggregator, BiFunction<I, Integer, O> computation) {
            super(aggregator);
            _computation = computation;
        }

        public IterationSourceImpl(Iterator<I> aggregator, Function<I, O> computation) {
            super(aggregator);
            _computation = (value, index) -> computation.apply(value);
        }

        @Override
        protected O compute(I item, int index) {
            return _computation.apply(item, index);
        }

        @Override
        protected O compute(I item) {
            return _computation.apply(item, null);
        }
    }
}
