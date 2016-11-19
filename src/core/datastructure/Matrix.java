package core.datastructure;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 19.11.2016
 * A 2D matrix that is iterated via Column and cannot add new columns
 */
public class Matrix<T> implements Iterable<Iterable<T>> {

    @Override
    public Iterator<Iterable<T>> iterator() {
        return null;
    }
}
