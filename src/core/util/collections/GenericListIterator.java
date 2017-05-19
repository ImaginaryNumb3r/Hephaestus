package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.util.annotations.ToTest;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible;

/**
 * @author Patrick
 * @description
 * @since 19.05.2017
 */
@ToTest
public class GenericListIterator<T> extends AbstractListIterator<T> {

    GenericListIterator(@NotNull Accessible<T> accessible, int length) {
        super(accessible, length);
    }

    public static <T> GenericListIterator<T> from(@NotNull Accessible<T> accessible, int length) {
        Contract.checkNull(accessible, "accessible");
        if (length < 0) throw new IllegalArgumentException("Length may not be smaller than zero");
        return new GenericListIterator<>(accessible, length);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(T t) {
        throw new UnsupportedOperationException();
    }
}
