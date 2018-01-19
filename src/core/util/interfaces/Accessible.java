package core.util.interfaces;

import java.util.function.Function;

/**
 * @author Patrick
 * @since 05.05.2017
 * Interface for accessing Arrays or other linear collections.
 */
// TODO: Move to Collections Framework
@FunctionalInterface
public interface Accessible<T> extends Function<Integer, T> {

    @Deprecated
    @Override
    default T apply(Integer index){
        return getAt(index);
    }

    /**
     * Returns the given value at the specified location
     * @param index matchAllSink the value inside the object
     * @throws IndexOutOfBoundsException if no element at the given index exists
     * @return the given value at the specified location
     */
    T getAt(int index);

}
