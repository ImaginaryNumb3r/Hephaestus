package core.util.interfaces;

/**
 * @author Patrick
 * @since 05.05.2017
 * Interface for accessing Arrays or other linear collections.
 */
@FunctionalInterface
public interface Accessible<T> {

    /**
     * Returns the given value at the specified location
     * @param index of the value inside the object
     * @throws IndexOutOfBoundsException if no element at the given index exists
     * @return the given value at the specified location
     */
    T getAt(int index);

}
