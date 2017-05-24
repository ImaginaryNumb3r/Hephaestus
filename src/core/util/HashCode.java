package core.util;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.exception.ParameterNullException;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class HashCode {

    private HashCode() {
        throw new UnsupportedOperationException("Class \"HashCode\" cannot be instantiated");
    }

    /**
     * Compares the two objects by reference and their hash code.
     * @param primary non-nullable object for the comparison
     * @param secondary nullable object for the comparison
     * @return true if both objects are equal in terms of reference or hash code
     */
    public static boolean equsls(@NotNull Object primary, @Nullable Object secondary){
        return primary == secondary || (secondary != null && primary.hashCode() == secondary.hashCode());
    }

    // Hard Coded from Stackoverflow
    public static int makeHashCode(@NotNull Integer a, @NotNull Integer b) {
        if (a == null || b == null) throw new ParameterNullException();

        return a.hashCode() ^ rotateLeft(b.hashCode(), 13);
    }

    public static Integer rotateLeft(int value, int count) {
        return (value << count) | (value >> (32 - count));
    }
}
