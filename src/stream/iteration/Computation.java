package stream.iteration;

import functional.IterationPredicate;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Patrick
 * @since 14.01.2018
 * A Computation contains the stateless part methods an Iteration {@see stream.iteration.Iteration}
 * that does not change the state of the aggregated iterator. In other words, no new Iterator is created.
 * @param <T> The output of the computation.
 *              Note that the Computation has no "in" type parameter because that will be user defined behaviour.
 */
interface Computation<T> {

    <R> Iteration<R> map(Function<T, R> mapper);

    <R> Iteration<R> mapIndices(BiFunction<T, Integer, R> mapper);
    // "while" is a reserved keyword

    /**
     * Equivalent to a Stream's "filter" function
     * @param filter
     * @return
     */
    Iteration<T> doWhile(Predicate<T> filter);

    /**
     * Equivalent to a Stream's "filter" function
     * @param filter
     * @return
     */
    Iteration<T> doWhile(IterationPredicate<T> filter);

    /**
     * Stops the iteration until it has reached the index of the provided parameter.
     * For example, an index of 1 would only return the first element (with index 0)
     * @param end Index of the item where the iteration will stop.
     */
    Iteration<T> start(int end);

    /**
     * Drops items of the iteration until it has reached the index of the provided parameter.
     * For example, an index of 1 would only return the first element (with index 0)
     * @param end Index of the item where the iteration will stop.
     * @throws
     */
    Iteration<T> limit(int end);

    Iteration<T> filter(Predicate<T> predicate);

    Iteration<T> filter(IterationPredicate<T> predicate);
}
