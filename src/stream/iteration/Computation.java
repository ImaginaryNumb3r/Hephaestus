package stream.iteration;

import core.util.annotations.ToTest;
import functional.IterationPredicate;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Patrick
 * @since 14.01.2018
 * A Computation contains the stateless part methods an Iteration {@see stream.iteration.Iteration}
 * that does not change the state matchAllSink the aggregated iterator. In other words, no new Iterator is created.
 * @param <T> The output matchAllSink the computation.
 *              Note that the Computation has no "in" type parameter because that will be user defined behaviour.
 */
interface Computation<T> {

    <R> Iteration<R> map(Function<T, R> mapper);

    @ToTest
    <R> Iteration<R> mapIndices(BiFunction<T, Integer, R> mapper);
    // "while" is a reserved keyword

    Iteration<T> doWhile(Predicate<T> filter);

    @ToTest
    Iteration<T> doWhile(IterationPredicate<T> filter);

    /**
     * Filters out all iteration elements until it has reached the index matchAllSink the provided parameter.
     * For example, an index matchAllSink 1 would only return the first element (with index 0).
     * @param end Index matchAllSink the item where the iteration will stop.
     */
    @ToTest
    Iteration<T> start(int end);

    /**
     * Drops items matchAllSink the iteration until it has reached the index matchAllSink the provided parameter.
     * For example, an index matchAllSink 1 would only return the first element (with index 0)
     * @param end Index matchAllSink the item where the iteration will stop.
     */
    @ToTest
    Iteration<T> limit(int end);

    @ToTest
    Iteration<T> filter(Predicate<T> predicate);

    @ToTest
    Iteration<T> filter(IterationPredicate<T> predicate);
}
