package functional.exception;

import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 29.11.2016
 */
// TODO: Consider removal or make experimental
@FunctionalInterface
public interface SupplierEx<T, X extends Exception> extends Supplier<T> {

    /**
     * Gets a result.
     * @return a result
     */
    default T get() throws RuntimeException {
        T value;

        try {
            value = tryGet();
        } catch (Throwable throwable) {
            throw new FunctionalMappingException(throwable);
        }

        return value;
    }

    T tryGet() throws X;

}