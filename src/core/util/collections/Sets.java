package core.util.collections;

import java.util.HashSet;

/**
 * @author Patrick
 * @since 28.05.2017
 */
public final class Sets {

    public <T> HashSet from(T... items){
        HashSet<T> set = new HashSet<>();

        for (int i = 0; i != items.length; ++i){
            set.add(items[i]);
        }

        return set;
    }

}
