package core.util.collections;

import core.exception.InstanceNotAllowedException;
import core.util.annotations.ToTest;
import core.util.collections.iteration.ArrayIterator;
import functional.BiSupplier;
import org.jetbrains.annotations.NotNull;
import stream.iteration.LinearCollector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

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

    public static <T> LinkedList<T> toLinkedList(Iterable<T> iterable){
        return toList(iterable, LinkedList::new);
    }

    public static <T> LinkedList<T> toLinkedList(Iterator<T> iterator){
        return toList(() -> iterator, LinkedList::new);
    }

    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... array){
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
    public static <T> ArrayList<T> toArrayList(Iterable<T> iterable){
        return toList(iterable, ArrayList::new);
    }

    public static <T> ArrayList<T> toArrayList(Iterator<T> iterator){
        return toList(() -> iterator, ArrayList::new);
    }

    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... array){
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

    public static <T> LinearCollector<T, LinkedList<T>> linkedListCollector(){
        return new CollectionCollector<>() {
            public Supplier<LinkedList<T>> supplier() {
                return LinkedList::new;
            }
        };
    }

    public static <T> LinearCollector<T, ArrayList<T>> arrayListCollector(){
        return new CollectionCollector<>() {
            public Supplier<ArrayList<T>> supplier() {
                return ArrayList::new;
            }
        };
    }

    private static abstract class CollectionCollector<T, C extends Collection<T>> implements LinearCollector<T, C> {

        private CollectionCollector() {
            characteristics().add(IDENTITY_FINISH);
        }

        @Override
        public abstract Supplier<C> supplier();

        @Override
        public BiConsumer<C, T> accumulator() {
            return Collection::add;
        }

        @Override
        public BinaryOperator<C> combiner() {
            return (left, right) -> {
                left.addAll(right);
                return left;
            };
        }
    }

    //</editor-fold>
}