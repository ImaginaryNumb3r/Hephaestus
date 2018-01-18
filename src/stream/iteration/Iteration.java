package stream.iteration;

import core.util.collections.iteration.Iterators;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Patrick
 * @since 13.01.2018
 * @apiNote An Iteration is a simpler version of a stream with a fixed size that is unknown.
 * This allows for efficient data processing for Iterators when no reducing or flattening functions are required.
 *
 * In other words, an Iteration is simply an abstraction of a for-each loop that provides several utility methods
 * but still leaves the bulk of the logic left to the user.
 * The aim of an Iteration is not to solve a problem functionally, but to reduce overhead and code footprint.
 *
 * A ListIteration class focusing around ListIterators could be considered.
 * However, at this point you will probably use a Stream and the Java api for it.
 */
public interface Iteration<T> extends Computation<T>, Termination<T> {

    /**
     * Returns true if the Iteration can be executed. Otherwise an {@code IllegalStateException} will be thrown.
     * @return true if the Iteration has been used previously. Otherwise false.
     */
    boolean executed();

    // TODO: Make as delegation of Iterator.
    static <T> Iteration<T> of(Iterator<T> iterator){
        return new IterationImpl<>(iterator);
    }

    // TODO: Make Aggregator directly from array.
    static <T> Iteration<T> of(T[] objects){
        return new IterationImpl<>(Iterators.from(objects));
    }

    void forEach(Consumer<? super T> action);
}
