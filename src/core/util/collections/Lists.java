package core.util.collections;

import core.exception.NoImplementationException;
import functional.BiSupplier;
import org.jetbrains.annotations.NotNull;
import core.exception.InstanceNotAllowedException;
import core.exception.ParameterNullException;
import core.util.annotations.ToTest;
import core.util.collections.iteration.ArrayIterator;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Patrick
 * @since 05.05.2017
 */
@SuppressWarnings("WeakerAccess")
@ToTest
public final class Lists {

    private Lists(){ throw new InstanceNotAllowedException(getClass());}

    //<editor-fold desc="LinkedList">
    private static <T, L extends List<T>> L toList(Iterable<T> iterable, Supplier<L> supplier){
        L list = supplier.get();
        iterable.forEach(list::add);
        return list;
    }

    public static <T> LinkedList<T> toLinkedList(@NotNull Iterable<T> iterable){
        if (iterable == null) throw new ParameterNullException("iterable");
        return toList(iterable, LinkedList::new);
    }

    public static <T> LinkedList<T> toLinkedList(@NotNull Iterator<T> iterator){
        if (iterator == null) throw new ParameterNullException("iterable");
        return toList(() -> iterator, LinkedList::new);
    }

    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(@NotNull T... array){
        if (array == null) throw new ParameterNullException("array");
        return toList(() -> ArrayIterator.from(array), LinkedList::new);
    }

    public static <T> LinkedList<T> toLinkedList(T item){
        LinkedList<T> list = new LinkedList<>();
        list.add(item);
        return list;
    }

    public static <T> LinkedList<T> toSingleLinkedList(T item){
        LinkedList<T> list = new LinkedList<>();
        list.add(item);
        return list;
    }
    //</editor-fold>

    //<editor-fold desc="ArrayList">
    public static <T> ArrayList<T> toArrayList(@NotNull Iterable<T> iterable){
        if (iterable == null) throw new ParameterNullException("iterable");
        return toList(iterable, ArrayList::new);
    }

    public static <T> ArrayList<T> toArrayList(@NotNull Iterator<T> iterator){
        if (iterator == null) throw new ParameterNullException("iterable");
        return toList(() -> iterator, ArrayList::new);
    }

    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(@NotNull T... array){
        if (array == null) throw new ParameterNullException("array");
        return toList(() -> ArrayIterator.from(array), ArrayList::new);
    }

    public static <T> ArrayList<T> toArrayList(T item){
        ArrayList<T> list = new ArrayList<>();
        list.add(item);
        return list;
    }

    public static <T> T[] toArray(Collection<T> collection, BiSupplier<T[], Integer> arrayConstructor) {
        T[] array = arrayConstructor.make(collection.size());

        int i = 0;
        for (T item : collection) {
            array[i++] = item;
        }

        return array;
    }
    //</editor-fold>

    //<editor-fold desc="Construction">
    public static <T> List<T> toList(@NotNull Iterable<T> iterable){
        return toArrayList(iterable);
    }

    public static <T> List<T> toList(@NotNull Iterator<T> iterator){
        return toArrayList(() -> iterator);
    }

    public static <T, A, R> Collector<T, A, R> linkedListCollector(@NotNull Iterable<T> iterable){
        throw new NoImplementationException();
    }

    public static <T, A, R> Collector<T, A, R> arrayListCollector(@NotNull Iterable<T> iterable){
        throw new NoImplementationException();
    }
    //</editor-fold>
}