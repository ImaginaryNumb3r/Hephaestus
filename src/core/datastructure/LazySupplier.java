package core.datastructure;

import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 15.11.2016
 */
interface LazySupplier<T> extends Supplier<T> {

    T get();

    void instantiate();

    boolean isInstantiated();

}
