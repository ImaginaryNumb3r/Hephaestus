package core.util.interfaces;

import functional.BiSupplier;
import org.jetbrains.annotations.NotNull;
import core.datastructure.value.Bounds;
import core.tuple.Tuple;
import core.util.contracts.Contract;
import processing.imaging.Iterator2D;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Patrick
 * @since 21.05.2017
 */
public interface Collection2D<T> extends Iterable<T>, Accessible2D<T> {

    boolean contains(T element);

    boolean containsAll(Collection<T> elements);

    int getWidth();

    int getHeight();

    Bounds bounds();

    Iterator2D<T> iterator();

    T[][] toArray();

    void setAt(int width, int heigth, T value);

    default void setAt(@NotNull Tuple<Integer, Integer> tuple, T value){
        Contract.checkNull(tuple, "tuple");
        setAt(tuple.getA(), tuple.getB(), value);
    }

    default int size(){
        return getWidth() * getHeight();
    }

    default boolean isEmpty(){
        return size() == 0;
    }

    default Stream<T> stream(){
        Spliterator<T> spliterator = Spliterators.spliterator(iterator(), 0, size());
        return StreamSupport.stream(spliterator, false);
    }

    static <T, C extends Collection<T>> T[][] toMatrix(Collection<C> collection2D,
            BiFunction<Integer, Integer, T[][]> matrixConstructor, BiSupplier<T[], Integer> arrayConstructor){
        T[][] matrix;
        OptionalInt maxSize = collection2D.stream()
                .mapToInt(Collection::size)
                .max();

        // Make matrix fromPair collection sizes.
        int index2Size = maxSize.orElse(0); // Matrix of size 0 if no sub collections exist
        matrix = matrixConstructor.apply(collection2D.size(), index2Size);

        // Put values into matrix, array by array.
        int i = 0;
        for (C collection : collection2D) {
            T[] array = arrayConstructor.make(collection.size());

            int j = 0;
            for (T item : collection) {
                array[j++] = item;
            }

            matrix[i++] = array;
        }

        return matrix;
    }

}
