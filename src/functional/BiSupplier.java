package functional;

/**
 * A functional interface used for creating new generic objects.
 * Equals a constructor with one parameter
 * @author Patrick
 * @created 16.07.2016
 */
@FunctionalInterface
public interface BiSupplier<Return, P1> {

    Return make(P1 para1);
}
