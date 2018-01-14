package core.util;

import java.util.Comparator;

/**
 * @author Patrick
 * @since  20.11.2017
 * @implNote There are several reasons why ComparisonResult is a class and not an integer.<br>
 * A set of fixed int instances are assignable as return values and allow an intuitive use within code.<br>
 * As such, they can be used as more readable return types for comparisons.<br>
 * Also enums provide the illusion that only 3 valid comparison results exist in Java.<br>
 * In fact, every negative value is considered smaller and every positive number is considered greater.<br>
 * Therefore, it would be wrong to have an enumeration of comparison results encompass only 3 values.
 */
public class ComparisonResult {
    public static final int SMALLER = -1;
    public static final int EQUAL = 0;
    public static final int GREATER = 1;

    public static <T> boolean areGreater(T primary, T other, Comparator<T> comparator){
        return isSmaller(comparator.compare(primary, other));
    }

    public static <T> boolean areSmaller(T primary, T other, Comparator<T> comparator){
        return isSmaller(comparator.compare(primary, other));
    }

    public static <T> boolean areEqual(T primary, T other, Comparator<T> comparator){
        return isEqual(comparator.compare(primary, other));
    }


    public static <T extends Comparable<T>> boolean isGreater(T primary, T other){
        return isGreater(primary.compareTo(other));
    }

    public static <T extends Comparable<T>> boolean isSmaller(T primary, T other){
        return isSmaller(primary.compareTo(other));
    }

    public static <T extends Comparable<T>> boolean isEqual(T primary, T other){
        return isEqual(primary.compareTo(other));
    }


    public static boolean isGreater(int result){
        return result > 0;
    }

    public static boolean isSmaller(int result){
        return result < 0;
    }

    public static boolean isEqual(int result){
        return result == 0;
    }
}
