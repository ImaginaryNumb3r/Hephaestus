package experimental.hybrid;

import java.util.Map;

/**
 * Creator: Patrick
 * Created: 28.11.2017
 * Purpose:
 */
public interface ListedMap<K, V> extends Map<K, V> {

    MappedList<K, V> toMappedList();

}
