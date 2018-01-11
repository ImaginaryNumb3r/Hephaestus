package functional;

import java.util.function.Function;

/**
 * @author Patrick
 * @since 10.01.2018
 */

public class IdentityFunction<T> implements Function<T, T> {

    @Override
    public T apply(T t) {
        return t;
    }
}
