package core.util;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class HashCode {

    // Hard Coded from Stackoverflow
    public static int makeHashCode(@NotNull Integer a,@NotNull Integer b) {
        if (a == null || b == null) throw new ParameterNullException();

        return a.hashCode() ^ rotateLeft(b.hashCode(), 13);
    }

    public static Integer rotateLeft(int value, int count) {
        return (value << count) | (value >> (32 - count));
    }
}
