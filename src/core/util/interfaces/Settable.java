package core.util.interfaces;

/**
 * @author Patrick
 * @since 05.05.2017
 * Interface for setting values in Arrays or other linear collections.
 */
public interface Settable<T> {

    /**
     * Sets the given value at the specified location
     * @param index of the value inside the object
     * @param value of the value inside the object
     * @throws IndexOutOfBoundsException if the index is set to an invalid position
     */
    void setAt(T value, int index);
}
