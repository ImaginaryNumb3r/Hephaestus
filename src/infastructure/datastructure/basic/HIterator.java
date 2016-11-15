package infastructure.datastructure.basic;

import java.util.Iterator;

/**
 * @author Patrick
 * @created 02.07.2016
 */
public interface HIterator<T> extends Iterator<T> {

    T current();

}
