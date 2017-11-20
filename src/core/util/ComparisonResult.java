package core.util;

import java.util.Comparator;

/**
 * Creator: Patrick
 * Created: 20.11.2017
 * Purpose:
 */
public class ComparisonResult {
    public static int SMALLER = -1;
    public static int EQUAL = 0;
    public static int GREATER = 1;

    public static <T> boolean isGreater(T primary, T other, Comparator<T> comparator){
        return isSmaller(comparator.compare(primary, other));
    }

    public static <T> boolean isSmaller(T primary, T other, Comparator<T> comparator){
        return isSmaller(comparator.compare(primary, other));
    }

    public static <T> boolean isEqual(T primary, T other, Comparator<T> comparator){
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
