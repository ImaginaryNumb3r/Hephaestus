package stream.iteration;

import core.util.collections.iteration.Iterators;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 14.01.2018
 * An IterationSource is a readable node in the chain of Iteration operations.
 * It might be the node providing the original source values or mapped data.
 *
 * @implNote note that this class is not Iterable because it could have multiple iterators
 * and because Iterables are not allowed to have state.
 */
abstract class IterationSource<in, out> implements Aggregator<in>, Provider<out> {
    private final Iterator<in> _aggregator;
    protected int _index; // The index of the element in the backing iteration.

    public IterationSource(Iterator<in> aggregator) {
        _aggregator = aggregator;
        _index = -1;
    }

    @Override
    public out output() {

        in input;

        // Skip elements until they satisfy the test criteria.
        do {
            ++_index;
            // Send null as signal if iteration has finished.
            if (!hasNext()){
                return null;
            }

             input = input();
        } while(!test(input));

        return compute(input());
    }

    protected abstract out compute(in item);

    protected abstract boolean test(in item);

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

}
