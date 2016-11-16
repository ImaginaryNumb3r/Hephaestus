package functional;

/**
 * A functional interface used for creating new generic objects.
 * Equals a constructor with hree parameter
 * @author Patrick
 * @since 16.07.2016
 */
@FunctionalInterface
public interface QuadSupplier<Return, P1, P2, P3> {

    Return get(P1 para1, P2 para2, P3 para3);
}
