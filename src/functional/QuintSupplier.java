package functional;

/**
 * A functional interface used for creating new generic objects.
 * Equals a constructor with four parameter
 * @author Patrick
 * @since 15.11.2016
 */
public interface QuintSupplier<Return, P1, P2, P3, P4> {

    Return get(P1 para1, P2 para2, P3 para3, P4 para4);
}
