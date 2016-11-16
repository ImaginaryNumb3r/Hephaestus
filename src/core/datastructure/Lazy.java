package core.datastructure;

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

    public Lazy(Supplier<T> supplier){
        if (supplier == null) throw new InvalidParameterException("Supplier may not be null");
        _supplier = supplier;
    }

    @Override
    public T get() {
        instantiate();
        return _value;
    }

    public void instantiate(){
        if (_value == null){
            synchronized (this) {
                if (_value == null) {
                    _value = _supplier.get();
                }
            }
        }
    }

    public boolean isInstantiated(){
        return _value != null;
    }


    public <S> Lazy<S> lazily(Supplier<S> lazy){
        return new Lazy<>(lazy);
    }
}