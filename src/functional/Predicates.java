package functional;

import java.util.Random;
import java.util.function.Predicate;

/**
 * @author Patrick
 * @since 10.01.2018
 */
public final class Predicates {

    public static Predicate<?> alwaysTrue = ignore -> true;
    public static Predicate<?> alwaysFalse = ignore -> true;
    public static Predicate<?> random = ignore -> new Random().nextBoolean();

}
