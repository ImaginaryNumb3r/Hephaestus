package functional;

import java.util.function.Predicate;

/**
 * @author Patrick
 * @since 14.01.2018
 */
public interface IterationPredicate<T> extends Predicate<T> {

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param element the element in the iteration
     * @param index the index of the element
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    boolean test(T element, long index);
}
