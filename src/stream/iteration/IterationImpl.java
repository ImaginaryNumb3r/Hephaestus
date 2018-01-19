package stream.iteration;

import core.exception.NoImplementationException;
import core.util.ComparisonResult;
import core.util.contracts.Contract;
import functional.IterationPredicate;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;

import static core.util.ComparisonResult.SMALLER;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author Patrick
 * @since 14.01.2018
 */
class IterationImpl<T> implements Iteration<T> {
    private final Iterator<T> _source;
    protected int _index; // = 0;

    public IterationImpl(Iterator<T> source) {
        _source = source;
    }

    //<editor-fold desc="Computation Method">
    @Override
    public <R> Iteration<R> map(Function<T, R> mapper) {
        MappingOps<T, R> mappingOps = new MappingOps<>(_source, mapper);
        return Iteration.of(mappingOps);
    }

    @Override
    public <R> Iteration<R> mapIndices(BiFunction<T, Integer, R> mapper) {
        MappingOps<T, R> mappingOps = new MappingOps<>(_source, mapper);
        return Iteration.of(mappingOps);
    }

    @Override
    public Iteration<T> start(int start) {
        Contract.checkNegative(start, "start");

        LongPredicate predicate = index -> index >= start;
        FilterOps<T> filterOps = new FilterOps<>(_source, predicate);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> limit(int end) {
        Contract.checkNegative(end, "end");

        LongPredicate predicate = index -> index <= end;
        FilterOps<T> filterOps = new FilterOps<>(_source, predicate);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> doWhile(IterationPredicate<T> filter) {
        Contract.checkNull(filter, "filter");

        WhileOps<T> filterOps = new WhileOps<>(_source, filter, true);
        return Iteration.of(filterOps);    }

    @Override
    public Iteration<T> doWhile(Predicate<T> filter) {
        Contract.checkNull(filter, "filter");

        WhileOps<T> filterOps = new WhileOps<>(_source, filter, true);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> filter(Predicate<T> predicate) {
        Contract.checkNull(predicate, "end");

        FilterOps<T> filterOps = new FilterOps<>(_source, predicate);
        return Iteration.of(filterOps);
    }

    @Override
    public Iteration<T> filter(IterationPredicate<T> predicate) {
        Contract.checkNull(predicate, "end");

        FilterOps<T> filterOps = new FilterOps<>(_source, predicate);
        return Iteration.of(filterOps);
    }
    //</editor-fold>

    //<editor-fold desc="Termination Operation">
    @Override
    public Iteration<T> toDistinct() {
        // Make distinct via HashSet;
        HashSet<T> collection = collect(HashSet::new);
        return Iteration.of(collection.iterator());
    }

    @Override
    public Iteration<T> toSorted(Comparator<T> comparator) {
        // Sort with priority queue.
        PriorityQueue<T> collection = collect(() -> new PriorityQueue<>(comparator));
        return Iteration.of(collection.iterator());
    }

    private <C extends Collection<T>> C collect(Supplier<C> supplier){
        LinearCollector<T, C> collector = LinearCollector.of(supplier);
        return collect(collector);
    }

    @Override
    public <R> Iteration<R> toFlattened(Function<T, ? extends Iterable<? extends R>> mapper) {
        ArrayList<T> list = new ArrayList<>();
        throw new NoImplementationException();

        /*
        while (_source.hasNext()){
            T cur = _source.next();
            Iteration<? extends R> apply = mapper.apply(cur);
        }

        return Iteration.of(); */
    }

    @Override
    public Iteration<T> toReverse(Consumer<T> comparator) {


        return null;
    }

    @Override
    public T reduce(BinaryOperator<T> operation) {
        return null;
    }

    @Override
    public Iteration<T> visitEach(Consumer<T> action) {
        return null;
    }

    @Override
    public void forEach(Consumer<T> action) {
        while (_source.hasNext()){
            action.accept(_source.next());
        }
    }

    @Override
    public <R, A> R collect(Collector<T, A, R> collector) {
        // Get collection from collector.
        A intermediate = collector.supplier().get();

        // Add all values to collection.
        _source.forEachRemaining(item -> collector.accumulator().accept(intermediate, item));

        // If the intermediate collection is the expected end result, cast intermediate. Otherwise call finisher.
        return !collector.characteristics().contains(IDENTITY_FINISH)
                ? collector.finisher().apply(intermediate)
                : (R) intermediate;

    }

    public <R> R collect(LinearCollector<T, R> collector) {
        return collect((Collector<T, R, R>) collector);
    }

    @Override
    public Optional<T> min(Comparator< T> comparator) {
        return desiredExtreme(comparator, ComparisonResult::isSmaller);
    }

    public Optional<T> desiredExtreme(Comparator<T> comparator, IntPredicate predicate) {
        if (_source.hasNext()){
            return Optional.empty();
        }

        T desired = _source.next();
        while (_source.hasNext()){
            T cur = _source.next();
            int result = comparator.compare(cur, desired);

            if (predicate.test(result)){
                desired = cur;
            }
        }

        return Optional.of(desired);
    }

    @Override
    public Optional<T> max(Comparator<T> comparator) {
        return desiredExtreme(comparator, ComparisonResult::isGreater);
    }

    @Override
    public long count() {
        while (_source.hasNext()){
            _source.next();
            ++_index;
        }

        return _index;
    }

    //<editor-fold desc="Matching Sinks">
    @Override
    public boolean anyMatch(Predicate<T> predicate) {
        MatchSink.MatchAnySink<T> matchAnySink = new MatchSink.MatchAnySink<>(_source, predicate);
        return matchAnySink.doesMatch();
    }

    @Override
    public boolean anyMatch(IterationPredicate<T> predicate) {
        MatchSink.MatchAnySink<T> matchAnySink = new MatchSink.MatchAnySink<>(_source, predicate);
        return matchAnySink.doesMatch();
    }

    @Override
    public boolean allMatch(Predicate<T> predicate) {
        MatchSink.MatchAllSink<T> matchAllSink = new MatchSink.MatchAllSink<>(_source, predicate);
        return matchAllSink.doesMatch();
    }

    @Override
    public boolean allMatch(IterationPredicate<T> predicate) {
        MatchSink.MatchAllSink<T> matchAllSink = new MatchSink.MatchAllSink<>(_source, predicate);
        return matchAllSink.doesMatch();
    }

    @Override
    public boolean noneMatch(Predicate<T> predicate) {
        MatchSink.MatchAllSink<T> matchNoneSink = new MatchSink.MatchAllSink<>(_source, predicate);
        return !matchNoneSink.doesMatch();
    }

    @Override
    public boolean noneMatch(IterationPredicate<T> predicate) {
        MatchSink.MatchAllSink<T> matchNoneSink = new MatchSink.MatchAllSink<>(_source, predicate);
        return !matchNoneSink.doesMatch();
    }
    //</editor-fold>

    @Override
    public Optional<T> first() {
        return _source.hasNext()
                ? Optional.of(_source.next())
                : Optional.empty();
    }
    //</editor-fold>

    @Override
    public boolean executed() {
        return !_source.hasNext();
    }
}
