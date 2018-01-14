package functional;

/**
 * A functional interface used for creating new generic objects.
 * Equals a constructor with hree parameter
 * @author Patrick
 * @since 16.07.2016
 */
// TODO: Consider removal. It's not a good practice and you should make an own interface if you need that many arguments.
@FunctionalInterface
public interface QuadSupplier<Return, P1, P2, P3> {

    Return get(P1 para1, P2 para2, P3 para3);
}
