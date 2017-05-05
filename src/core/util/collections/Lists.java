package core.util.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick
 * @since 05.05.2017
 */
public class Lists {

    private Lists(){ throw new UnsupportedOperationException("Class \"Lists\" may not be instantiated");}

    public static <T> LinkedList<T> toLinkedList(Iterable<T> iterable){
        LinkedList<T> linkedList = new LinkedList<>();

        for (T item : iterable) {
            linkedList.add(item);
        }

        return linkedList;
    }

    public static <T> LinkedList<T> toLinkedList(Iterator<T> iterator){
        return toLinkedList(() -> iterator);
    }

    public static <T> List<T> toList(Iterable<T> iterable){
        return toLinkedList(iterable);
    }

    public static <T> List<T> toList(Iterator<T> iterator){
        return toLinkedList(() -> iterator);
    }

}