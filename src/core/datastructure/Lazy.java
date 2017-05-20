package core.datastructure;

import com.sun.istack.internal.NotNull;

import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 15.11.2016
 *
 * Interface for lazy instances of a given value, delivered by a supplier.
 * An assigned value to this class needs to be permanent and will never change after its creation.
 * Accessing this class is guaranteed to be thread safe.
 * The implementation is expected to eliminate the cost of synchronization once the value is instanced.
 */
public interface Lazy<T> extends Supplier<T> {

    /**
     * Atomically returns the already instance as determined in the supplier.
     * @return the already instance as determined in the supplier
     */
    T get();

    /**
     * Atomically loads the value and makes it ready for retrieval from get()
     */
    void instantiate();

    /**
     * Checks if the value behind the LazyImpl has already been loaded
     * @return true if the value behind the lazy has already been loaded
     */
    boolean isInstantiated();

    /**
     * Creates a new lazy loaded instance, which creates a new object as declared with a supplier when demanded.
     * @param supplier which creates the instance of value that will be accessed
     * @param <S> Type of the created instance
     * @return LazyImpl instance of the provided supplier
     * @throws core.exception.ParameterNullException if param supplier is null
     */
    static <S> Lazy<S> from(@NotNull Supplier<S> supplier){
        return LazyImpl.from(supplier);
    }

}
