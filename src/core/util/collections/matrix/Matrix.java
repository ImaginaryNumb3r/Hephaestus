package core.util.collections.matrix;

import core.datastructure.value.Coord2D;
import core.util.interfaces.Collection2D;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 09.12.2017
 * A read-only matrix interface.
 */
// TODO: Move to Collections Framework
public interface Matrix<T> extends Collection2D<T> {

    Iterator<Coord2D> coordIterator();

    String toString(Function<T, String> mapper);

    default <T> Stream<T> stream(T[][] matrix){
        return Stream.of(matrix)
                .flatMap(Stream::of)
                .flatMap(Stream::of);
    }
}
