package functional;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is the three-arity specialization of {@link java.util.function.Function}.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <R> the type of the result of the function
 *
 * @see java.util.function.Function
 * @author Patrick
 * @since 16.07.2016
 */
@FunctionalInterface
public interface TriFunction<P1, P2, P3, R> {

    R apply(P1 para1, P2 para2, P3 para3);
}
