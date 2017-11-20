package datastructure.tree.interfaces;

/**
 * Creator: Patrick
 * Created: 10.11.2017
 * Purpose:
 */
public interface TreeMap<I, T> extends ReadableTreeMap<I, T>, Tree<T> {

    T put(I identifier, T value);
}
