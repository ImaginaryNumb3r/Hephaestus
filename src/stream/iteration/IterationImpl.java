package stream.iteration;

import core.util.contracts.Contract;
import functional.IterationPredicate;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author Patrick
 * @since 14.01.2018
 */
class IterationImpl<T> implements Iteration<T> {
    private final Iterator<T> _aggregator;
    protected int _index; // = 0;

    public IterationImpl(Iterator<T> aggregator) {
        _aggregator = aggregator;
    }

    //<editor-fold desc="Computation Method">
    @Override
    public <R> Iteration<R> map(Function<T, R> mapper) {
        MappingOps<T, R> mappingOps = new MappingOps<>(_aggregator, mapper);
        return Iteration.of(mappingOps);
    }

    @Override
    public <R> Iteration<R> mapIndices(BiFunction<T, Integer, R> mapper) {
        MappingOps<T, R> mappingOps = new MappingOps<>(_aggregator, mapper);
        return Iteration.of(mappingOps);
    }

    @Override
    public Iteration<T> start(int start) {
        Contract.checkNegative(start, "start");

        LongPredicate predicate = index -> index >= start;
        FilterOps<T> filterOps = new FilterOps<>(_aggregator, predicate);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> limit(int end) {
        Contract.checkNegative(end, "end");

        LongPredicate predicate = index -> index <= end;
        FilterOps<T> filterOps = new FilterOps<>(_aggregator, predicate);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> doWhile(IterationPredicate<T> filter) {
        Contract.checkNull(filter, "filter");

        WhileOps<T> filterOps = new WhileOps<>(_aggregator, filter, true);
        return Iteration.of(filterOps);    }

    @Override
    public Iteration<T> doWhile(Predicate<T> filter) {
        Contract.checkNull(filter, "filter");

        WhileOps<T> filterOps = new WhileOps<>(_aggregator, filter, true);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> filter(Predicate<T> predicate) {
        Contract.checkNull(predicate, "end");

        FilterOps<T> filterOps = new FilterOps<>(_aggregator, predicate);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> filter(IterationPredicate<T> predicate) {
        Contract.checkNull(predicate, "end");

        FilterOps<T> filterOps = new FilterOps<>(_aggregator, predicate);
        return Iteration.of(filterOps);
    }
    //</editor-fold>

    //<editor-fold desc="Termination Operation">
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
        // Get collection from collector.
        A intermediate = collector.supplier().get();

        // Add all values to collection.
        _aggregator.forEachRemaining(item -> collector.accumulator().accept(intermediate, item));

        // If the intermediate collection is the expected end result, cast intermediate. Otherwise call finisher.
        return !collector.characteristics().contains(IDENTITY_FINISH)
                ? collector.finisher().apply(intermediate)
                : (R) intermediate;

    }

    public <R> R collect(LinearCollector<T, R> collector) {
        return collect((Collector<T, R, R>) collector);
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
        while (_aggregator.hasNext()){
            T next = _aggregator.next();
            ++_index;
        }

        return _index;
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
    //</editor-fold>

    @Override
    public boolean executed() {
        return !_aggregator.hasNext();
    }
}
