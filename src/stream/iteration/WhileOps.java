package stream.iteration;

import functional.IterationPredicate;

import java.util.Iterator;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @author Patrick
 * @since 15.01.2018
 */
final class WhileOps<T> extends FilterOps<T> {
    private final boolean _startAccepting;
    private boolean _stopIteration;

    public WhileOps(Iterator<T> aggregator, IterationPredicate<T> predicate, boolean startAccepting) {
        super(aggregator, predicate);
        _startAccepting = startAccepting;
        _stopIteration = false;
    }

    public WhileOps(Iterator<T> aggregator, Predicate<T> predicate, boolean startAccepting) {
        this(aggregator, (item, index) -> predicate.test(item), startAccepting);
    }

    public WhileOps(Iterator<T> aggregator, LongPredicate predicate, boolean startAccepting) {
        this(aggregator, (item, index) -> predicate.test(index), startAccepting);
    }

    @Override
    protected boolean test(T item) {
        // Take item by default until a condition is satisfied.
        boolean takeItem = _startAccepting;

        // If iteration is ongoing, check if the predicate is violated.
        if (!_stopIteration) {
            // Predicate must conform to the initial boolean acceptance value.
            _stopIteration = takeItem != _predicate.test(item, _index);
        } else {
            // Once the iteration is stopped no more values may pass this the test.
            takeItem = false;
        }

        return takeItem;
    }
}
