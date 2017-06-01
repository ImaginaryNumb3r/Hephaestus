package core.util.collections.iteration;

import com.sun.istack.internal.NotNull;
import core.util.HashCode;
import core.util.collections.interfaces.Linkable;
import util.hash.HashGenerator;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public class NodeIterator<T, L extends Linkable<T, L>> implements Iterator<T> {
    protected final L _start;
    protected L _current;

    protected NodeIterator(@NotNull L startNode) {
        _start = startNode;
        _current = null;
    }

    @Override
    public boolean hasNext() {
        return _current != null
                ? _current.hasNext()
                : _start.hasNext();
    }

    @Override
    public T next() {
        L next = _current != null
                ? _current.tryNext()
                : _start.tryNext();
        _current = next;
        return next.value();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NodeIterator && HashCode.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .append(_start)
                .toHashCode();
    }
}
