package datastructure.tree.interfaces;

/**
 * Creator: Patrick
 * Created: 10.11.2017
 * Purpose:
 */
public interface ReadableTreeMap<I, T> extends ReadableTree<T>{

    T get(I identifier);

    default boolean contains(I identifier){
        return get(identifier) != null;
    }

}
