package functional.exception;

import core.exception.FunctionalException;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 29.11.2016
 */
@FunctionalInterface
public interface SupplierEx<T> extends Supplier<T> {

    /**
     * Gets a result.
     * @return a result
     */
    default T get() throws RuntimeException {
        T value;

        try {
            value = tryGet();
        } catch (FunctionalException e) {
            throw new RuntimeException(e);
        }

        return value;
    }

    /**
     * Returns a result, but an exception may also be thrown
     * @return a result, but an exception may also be thrown
     * @throws FunctionalException will have an inner exception, which needs to be specified in the implementation
     */
    T tryGet() throws FunctionalException;
}