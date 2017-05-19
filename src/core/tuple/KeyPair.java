package core.tuple;

import com.sun.istack.internal.Nullable;
import core.util.contracts.Contract;

import java.util.Map;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public interface KeyPair<Key, Val> extends Tuple<Key, Val> {

    static <Key, Val> KeyPair from (Tuple<Key, Val> tuple) {
        Contract.checkNull(tuple, "tuple");
        return new KeyPairImpl<>(tuple);
    }

    static <Key, Val> KeyPair from(@Nullable Key key, @Nullable Val val) {
        return new KeyPairImpl<>(key, val);
    }

    void putInto(Map<Key, Val> map);
}