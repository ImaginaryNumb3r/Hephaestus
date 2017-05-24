package core.util;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.exception.InstanceNotAllowedException;
import core.exception.ParameterNullException;
import util.hash.HashGenerator;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public final class HashCode {
    public static final int DEFAULT_MULTIPLIER_BASE = 17;
    public static final int DEFAULT_HASHCODE_BASE = 37;

    private HashCode() {
        throw new InstanceNotAllowedException(getClass());
    }

    /**
     * Compares the two objects by reference and their hash code.
     * @param primary non-nullable object for the comparison
     * @param secondary nullable object for the comparison
     * @return true if both objects are equal in terms of reference or hash code
     */
    public static boolean equals(@NotNull Object primary, @Nullable Object secondary){
        return primary == secondary || (secondary != null && primary.hashCode() == secondary.hashCode());
    }

    //<editor-fold desc="Permutate">
    public static int permutate(int originalHash, int mutator){
        return permutate(originalHash, mutator, DEFAULT_HASHCODE_BASE);
    }

    public static int permutate(int originalHash, int mutator, int base){
        return originalHash * mutator + base;
    }

    public static int permutate(int originalHash){
        return permutate(originalHash, DEFAULT_MULTIPLIER_BASE, DEFAULT_HASHCODE_BASE);
    }

    /**
     * Simple permutation called on the hashcode of the given object
     * @param object that may be nullable. In this case, it will be treated with a hashcode of 0
     * @return permutated hashcode of the given object
     */
    public static int permutate(Object object) {
        return permutate(object != null
                ? object.hashCode()
                : 0, DEFAULT_MULTIPLIER_BASE, DEFAULT_MULTIPLIER_BASE);
    }
    //</editor-fold>

    // Hard Coded from Stackoverflow
    public static int makeHashCode(@NotNull Integer a, @NotNull Integer b) {
        if (a == null || b == null) throw new ParameterNullException();
        return a.hashCode() ^ rotateLeft(b.hashCode(), 13);
    }

    public static Integer rotateLeft(int value, int count) {
        return (value << count) | (value >> (32 - count));
    }
}
