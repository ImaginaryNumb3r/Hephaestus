package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;
import core.util.annotations.ToTest;
import core.util.contracts.Contract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 05.05.2017
 */
@SuppressWarnings("WeakerAccess")
@ToTest
public class Lists {

    private Lists(){ throw new UnsupportedOperationException("Class \"Lists\" may not be instantiated");}

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
        return toList(() -> new ArrayIterator<>(array), LinkedList::new);
    }
    //</editor-fold>

    public static <T> List<T> toList(@NotNull Iterable<T> iterable){
        return toArrayList(iterable);
    }

    public static <T> List<T> toList(@NotNull Iterator<T> iterator){
        return toArrayList(() -> iterator);
    }

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
        return toList(() -> new ArrayIterator<>(array), ArrayList::new);
    }
    //</editor-fold>

}