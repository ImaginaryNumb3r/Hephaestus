package functional;

/**
 * A functional interface used for creating new generic objects.
 * Equals a constructor with two parameter
 * @author Patrick
 * @created 16.07.2016
 */
@FunctionalInterface
public interface TriSupplier<Return, P1, P2> {

    Return make(P1 para1, P2 para2);
}
