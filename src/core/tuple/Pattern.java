package core.tuple;

import core.util.interfaces.IterableList;

import java.io.Serializable;

/**
 * @author Patrick
 * @since 17.11.2016
 */
// TODO: Create separate mutable and readonly versions of all pattern descendants
interface Pattern extends Serializable, IterableList<Object> {


    int hashCode();

    boolean equals(Object obj);

}
