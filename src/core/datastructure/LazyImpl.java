package core.datastructure;

import com.sun.istack.internal.NotNull;
import core.util.contracts.Contract;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 15.11.2016
 *
 * Creates a lazy instance of a value, delivered by a supplier.
 * An assigned value to this class is permanent and will never change.
 * Accessing this class is thread safe. However, the overhead of synchronization is gone once the value is instantiated.
 */
class LazyImpl<T> implements Lazy<T>, Serializable {
    private Supplier<T> _supplier;
    private T _value;

    /**
     * Creates a new instance, which creates a new object as declared with a supplier when demanded.
     * @param supplier which creates the instance of value that will be accessed
     */
    protected LazyImpl(@NotNull Supplier<T> supplier){
        _supplier = supplier;
    }

    /**
     * Atomically returns the already instance as determined in the supplier.
     * @return the already instance as determined in the supplier
     */
    @Override
    public T get() {
        instantiate();
        return _value;
    }

    /**
     * Atomically loads the value
     */
    @Override
    public void instantiate(){
        if (_value == null){
            synchronized (this) {
                if (_value == null) {
                    _value = _supplier.get();
                }
            }
        }
    }

    /**
     * Checks if the value behind the LazyImpl has already been loaded
     * @return true if the value behind the lazy has already been loaded
     */
    @Override
    public boolean isInstantiated(){
        return _value != null;
    }


    /**
     * Creates a new lazy loaded instance, which creates a new object as declared with a supplier when demanded.
     * @param supplier which creates the instance of value that will be accessed
     * @param <S> Type of the created instance
     * @return LazyImpl instance of the provided supplier
     * @throws core.exception.ParameterNullException if param supplier is null
     */
    public static <S> LazyImpl<S> from(@NotNull Supplier<S> supplier){
        Contract.checkNull(supplier, "supplier");
        return new LazyImpl<>(supplier);
    }
}