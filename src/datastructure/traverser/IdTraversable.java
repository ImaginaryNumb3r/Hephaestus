package datastructure.traverser;

/**
 * @author Patrick
 * @since 29.01.2017
 */
// TODO: Move to Graph Framework
public interface IdTraversable<I, V> extends Traversable<V> {

    IdTraverser<I, V> traverser();

}
