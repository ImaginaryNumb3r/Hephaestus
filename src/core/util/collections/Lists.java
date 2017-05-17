package core.util.collections;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick
 * @since 05.05.2017
 */
@SuppressWarnings("WeakerAccess")
public class Lists {

    private Lists(){ throw new UnsupportedOperationException("Class \"Lists\" may not be instantiated");}

    public static <T> LinkedList<T> toLinkedList(@NotNull Iterable<T> iterable){
        if (iterable == null) throw new ParameterNullException("iterable");

        LinkedList<T> linkedList = new LinkedList<>();
        iterable.forEach(linkedList::add);
        return linkedList;
    }

    public static <T> LinkedList<T> toLinkedList(@NotNull Iterator<T> iterator){
        return toLinkedList(() -> iterator);
    }

    public static <T> List<T> toList(@NotNull Iterable<T> iterable){
        return toLinkedList(iterable);
    }

    public static <T> List<T> toList(@NotNull Iterator<T> iterator){
        return toLinkedList(() -> iterator);
    }
}