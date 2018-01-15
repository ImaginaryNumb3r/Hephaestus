package stream.iteration;

import functional.IterationPredicate;

import java.util.Iterator;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @author Patrick
 * @since 14.01.2018
 */
class FilterOps<in> extends IterationSource<in, in> {
    protected final IterationPredicate<in> _predicate;
    protected int _index;

    public FilterOps(Iterator<in> aggregator, IterationPredicate<in> predicate) {
        super(aggregator);
        _predicate = predicate;
        _index = -1;
    }

    public FilterOps(Iterator<in> aggregator, Predicate<in> predicate) {
        this(aggregator, (item, index) -> predicate.test(item));
    }

    public FilterOps(Iterator<in> aggregator, LongPredicate predicate) {
        this(aggregator, (item, index) -> predicate.test(index));
    }

    @Override
    protected in compute(in item) {
        return item;
    }

    @Override
    protected boolean test(in item) {
        return _predicate.test(item, _index);
    }
}
