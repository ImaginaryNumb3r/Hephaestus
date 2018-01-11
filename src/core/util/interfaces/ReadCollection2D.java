package core.util.interfaces;

import core.datastructure.value.Bounds;
import core.tuple.Tuple;
import core.util.contracts.Contract;
import functional.BiSupplier;
import org.jetbrains.annotations.NotNull;
import processing.imaging.Iterator2D;

import java.util.Collection;
import java.util.OptionalInt;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Creator: Patrick
 * Created: 09.12.2017
 * Purpose:
 */
public interface ReadCollection2D<T> extends Iterable<T>, Accessible2D<T> {

    boolean contains(T element);

    boolean containsAll(Collection<T> elements);

    int getWidth();

    int getHeight();

    Bounds bounds();

    Iterator2D<T> iterator();

    T[][] toArray();

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


}
