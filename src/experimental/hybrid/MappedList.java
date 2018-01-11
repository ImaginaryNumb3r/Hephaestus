package experimental.hybrid;

import java.util.List;

/**
 * Creator: Patrick
 * Created: 28.11.2017
 * Purpose:
 */
public interface MappedList<T, O> extends List<T> {

    ListedMap<T, O> toListedMap();

}
