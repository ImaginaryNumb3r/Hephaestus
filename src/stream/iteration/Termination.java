package stream.iteration;

import core.util.annotations.ToTest;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 14.01.2018
 * A Termination contains the finishing methods of an Iteration {@see stream.iteration.Iteration}
 * and iterates the aggregated iterator.
 * In other words, it will use the backing iterator and might allocate a new backing collection.
 */
public interface Termination<T> {

    @ToTest
    Iteration<T> toDistinct();

    @ToTest
    Iteration<T> toSorted(Comparator<? super T> comparator);

    @ToTest
    <R> Iteration<R> toFlattened(Function<? super T, ? extends Iteration<? extends R>> mapper);

    @ToTest
    Iteration<T> toReverse(Consumer<? super T> comparator);

    @ToTest
    T reduce(BinaryOperator<T> operation);

    @ToTest
    Iteration<T> visitEach(Consumer<? super T> action);

    @ToTest
    void forEach(Consumer<? super T> action);

    <R, A> R collect(Collector<? super T, A, R> collector);

    @ToTest
    <R> R collect(LinearCollector<T, R> collector);

    @ToTest
    T min(Comparator<? super T> comparator);

    @ToTest
    T max(Comparator<? super T> comparator);

    long count();

    @ToTest
    boolean anyMatch();

    @ToTest
    boolean allMatch();

    @ToTest
    boolean noneMatch();

    @ToTest
    Optional<T> first();
}
