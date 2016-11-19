package core.tuple;

import com.sun.istack.internal.Nullable;

import java.util.Map;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public class KeyPair<Key, Val> extends Tuple<Key, Val> {

    public KeyPair(Tuple<Key, Val> tuple) {
        super(tuple);
    }

    public KeyPair(@Nullable Key key, @Nullable Val val) {
        super(key, val);
    }

    public void putInto(Map<Key, Val> map){
        map.put(A, B);
    }
}