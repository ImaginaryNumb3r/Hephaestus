package stream.iteration;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 14.01.2018
 * An IterationSource is a readable node in the chain of Iteration operations.
 * It might be the node providing the original source values or mapped data.
 *
 * @implNote note that this class is not Iterable because it could have multiple iterators
 * and because Iterables are not allowed to have state.
 */
abstract class ComputationPipe<in, out> implements IterationSource<out> {
    private static final int CURSOR_START = -1;
    protected final Iterator<in> _source;
    protected int _cursorPos; // The index of the element in the backing iteration.
    private out _next;

    public ComputationPipe(Iterator<in> source) {
        _source = source;
        _cursorPos = CURSOR_START;
    }

    public out output() {
        in input;

        // Skip elements until they satisfy the test criteria.
        do {
            ++_cursorPos;

            // Once the next element is null and hasNext fails, the Iteration is finished.
            if (!_source.hasNext()){
                return null;
            }

             input = _source.next();
        } while(!test(input));

        return compute(input);
    }

    protected abstract out compute(in item);

    protected abstract boolean test(in item);

    @Override
    public boolean hasNext() {
        return _next != null || _source.hasNext();
    }

    @Override
    public out next() {
        if (_cursorPos == CURSOR_START){
            _next = output();
        }

        if (!hasNext()){
            throw new NoSuchElementException();
        }

        // Because the method "output()" might skip several items within an iteration a call to "hasNext()"
        // of the iterator has no meaning to this class. Therefore, we always process one more item than required.
        // If the next item is null and the iterator has ended, we know that the Iteration has finished.
        // However, if the next element is null and the "hasNext()" still returns true we simply received a null
        // element from the iterator and the Iteration continues (which also happens at Iteration start).

        out next = _next;
        _next = output();
        return next;
    }
}
