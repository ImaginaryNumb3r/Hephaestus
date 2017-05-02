package datastructure.tree.node.subtype.subtype;

import datastructure.tree.node.subtype.IdTreeNodeReader;
import datastructure.tree.node.subtype.MultiTreeNodeReader;

import java.util.Iterator;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 29.01.2017
 */
public interface MultiIdTreeNodeReader<I, V, N extends MultiIdTreeNodeReader<I, V, N>>
        extends MultiTreeNodeReader<V, N>, IdTreeNodeReader<I, V>, Iterable<N> {

    Iterable<N> children();

    Stream<N> childStream();

    default Iterator<N> iterator(){
        return children().iterator();
    }
}
