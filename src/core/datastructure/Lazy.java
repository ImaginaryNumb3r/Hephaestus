package core.datastructure;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 15.11.2016
 */
public class Lazy<T> implements LazySupplier<T>, Serializable {
    private Supplier<T> _supplier;
    private T _value;

    /**
     * Creates a new instance, which creates a new object as declared with a supplier when demanded.
     * @param supplier which creates the instance of value that will be accessed
     */
    public Lazy(@NotNull Supplier<T> supplier){
        if (supplier == null) throw new InvalidParameterException("Supplier may not be null");
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
     * Checks if the value behind the Lazy has already been loaded
     * @return true if the value behind the lazy has already been loaded
     */
    @Override
    public boolean isInstantiated(){
        return _value != null;
    }


    public <S> Lazy<S> lazily(Supplier<S> lazy){
        return new Lazy<>(lazy);
    }
}