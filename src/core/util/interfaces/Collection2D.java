package core.util.interfaces;

import core.datastructure.value.Bounds;
import core.util.collections.Lists;
import processing.imaging.Iterator2D;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Patrick
 * @description
 * @since 21.05.2017
 */
    public interface Collection2D<T> extends Iterable<T>, Accessible2D<T> {

    boolean contains(T element);

    int getWidth();

    int getHeight();

    Bounds bounds();

    Iterator2D<T> iterator();

    T[][] toArray();

    default int size(){
        return getWidth() * getHeight();
    }

    default boolean isEmpty(){
        return getHeight() == 0 || getWidth() == 0;
    }

    default Stream<T> stream(){
        Spliterator<T> spliterator = Spliterators.spliterator(iterator(), 0, size());
        return StreamSupport.stream(spliterator, false);
    }

}
