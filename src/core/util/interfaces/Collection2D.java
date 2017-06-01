package core.util.interfaces;

import com.sun.istack.internal.NotNull;
import core.datastructure.value.Bounds;
import core.tuple.Tuple;
import core.util.contracts.Contract;
import processing.imaging.Iterator2D;

import java.util.Collection;
import java.util.Spliterator;
import java.util.Spliterators;
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
}
