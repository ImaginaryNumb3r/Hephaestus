package stream.iteration;

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

    Iteration<T> toDistinct();

    Iteration<T> toSorted(Comparator<? super T> comparator);

    <R> Iteration<R> toFlattened(Function<? super T, ? extends Iteration<? extends R>> mapper);

    Iteration<T> toReverse(Consumer<? super T> comparator);

    T reduce(BinaryOperator<T> operation);

    Iteration<T> visitEach(Consumer<? super T> action);

    void forEach(Consumer<? super T> action);

    <R, A> R collect(Collector<? super T, A, R> collector);

    T min(Comparator<? super T> comparator);

    T max(Comparator<? super T> comparator);

    long count();

    boolean anyMatch();

    boolean allMatch();

    boolean noneMatch();

    Optional<T> first();
}
