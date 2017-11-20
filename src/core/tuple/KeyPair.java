package core.tuple;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import core.util.contracts.Contract;

import java.util.Map;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public interface KeyPair<Key, Val> extends Tuple<Key, Val> {

    static <Key, Val> KeyPair from (@NotNull Tuple<Key, Val> tuple) {
        Contract.checkNull(tuple, "tuple");
        return new KeyPairImpl<>(tuple);
    }

    static <Key, Val> KeyPair from(@Nullable Key key, @Nullable Val val) {
        return new KeyPairImpl<>(key, val);
    }

    /**
     * Returns the key of the KeyPair
     * @return the key of the KeyPair
     */
    default Key getKey(){
        return getA();
    }

    /**
     * Returns the value of the KeyPair
     * @return the value of the KeyPair
     */
    default Val getValue(){
        return getB();
    }

    /**
     * Puts the value into the map, at the position of the given key.
     * @param map that has the key-value pair to be inserted
     */
    void putInto(@NotNull Map<Key, Val> map);

    /**
     * Deprecated method, due to backwards comparability with Tuple.
     * Use only when referred to as a Tuple. Do not call manually to improve code readability.
     */
    @Deprecated
    @Override
    Key getA();

    /**
     * Deprecated method, due to backwards comparability with Tuple.
     * Use only when referred to as a Tuple. Do not call manually to improve code readability.
     */
    @Deprecated
    @Override
    Val getB();
}