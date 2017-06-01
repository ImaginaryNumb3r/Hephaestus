package core.util.collections.iteration;

import com.sun.istack.internal.NotNull;
import core.util.collections.interfaces.Linkable;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 01.06.2017
 */
public class NodeIterator<T extends Linkable<T>> implements Iterator<T> {
    protected T _current;

    protected NodeIterator(@NotNull T current) {
        _current = current;
    }

    @Override
    public boolean hasNext() {
        return _current.hasNext();
    }

    @Override
    public T next() {
        return _current.next();
    }
}
