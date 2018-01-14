package stream.iteration;

import functional.IterationPredicate;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collector;

/**
 * @author Patrick
 * @since 14.01.2018
 */
public class IterationImpl<T> implements Iteration<T> {
    private final Iterator<T> _aggregator;

    public IterationImpl(Iterator<T> aggregator) {
        _aggregator = aggregator;
    }

    @Override
    public <R> Iteration<R> map(Function<T, R> mapper) {
        IterationSource<T, R> source = IterationSource.of(_aggregator, mapper);
        return new IterationImpl<>(source.sinkIterator());
    }

    @Override
    public <R> Iteration<R> mapIndices(BiFunction<T, Integer, R> mapper) {
        // TODO: Make own object for every iteration. Otherwise it won't end well...
        IterationSource<T, R> source = IterationSource.of(_aggregator, mapper);
        return new IterationImpl<>(source.sinkIterator());
    }

    @Override
    public boolean executed() {
        return false;
    }

    @Override
    public Iteration<T> toDistinct() {
        return null;
    }

    @Override
    public Iteration<T> toSorted(Comparator<? super T> comparator) {
        return null;
    }

    @Override
    public <R> Iteration<R> toFlattened(Function<? super T, ? extends Iteration<? extends R>> mapper) {
        return null;
    }

    @Override
    public Iteration<T> toReverse(Consumer<? super T> comparator) {
        return null;
    }

    @Override
    public T reduce(BinaryOperator<T> operation) {
        return null;
    }

    @Override
    public Iteration<T> visitEach(Consumer<? super T> action) {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return null;
    }

    @Override
    public T min(Comparator<? super T> comparator) {
        return null;
    }

    @Override
    public T max(Comparator<? super T> comparator) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean anyMatch() {
        return false;
    }

    @Override
    public boolean allMatch() {
        return false;
    }

    @Override
    public boolean noneMatch() {
        return false;
    }

    @Override
    public Optional<T> first() {
        return null;
    }

    @Override
    public Iteration<T> doWhile(Predicate<T> filter) {
        return null;
    }

    @Override
    public Iteration<T> doWhile(IterationPredicate<T> filter) {
        return null;
    }

    @Override
    public Iteration<T> start(int end) {
        return null;
    }

    @Override
    public Iteration<T> limit(int end) {
        return null;
    }
}
