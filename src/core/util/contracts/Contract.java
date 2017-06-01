package core.util.contracts;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;
import core.util.collections.iteration.Iterables;
import core.util.collections.iteration.Iterators;
import functional.exception.SupplierEx;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Creator: Patrick
 * Created: 11.12.2016
 *
 * Experimental Class
 */
@SuppressWarnings("WeakerAccess")

public final class Contract {
    private BooleanSupplier _contract;

    private Contract(BooleanSupplier contract){
        checkNulls(contract);
        _contract = contract;
    }


    // ==================╗
    //   Static Methods  ║
    // ==================╝

    /**
     * Performs a null check on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ParameterNullException if contract is violated
     */
    public static void checkNulls(Object... objects) throws ParameterNullException {
        checkNulls(Iterables.from(objects));
    }

    /**
     * Performs a checkNulls on the given objects and throws a named ParameterNullException if it is null
     * @param object argument containing the object to be tested
     * @param name name of the parameter that was tested
     * @throws ParameterNullException if contract is violated
     */
    public static void checkNull(Object object, String name) throws ParameterNullException {
        name = name == null ? "null" : name;
        if (object == null) throw new ParameterNullException(name);
    }

    /**
     * Performs a checkNulls on the given objects and throws a named ParameterNullException if it is null
     * @param object argument containing the object to be tested
     * @throws ParameterNullException if contract is violated
     */
    public static void checkNull(Object object) throws ParameterNullException {
        if (object == null) throw new ParameterNullException();
    }

    /**
     * Performs a checkNulls on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ParameterNullException if contract is violated
     */
    public static void checkNulls(Iterable<Object> objects) throws ParameterNullException {
        for (Object object : objects) {
            if (object == null) throw new ParameterNullException();
        }
    }

    /**
     * Performs a null check on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws X if contract is violated
     */
    public static <T, X extends RuntimeException> void checkAndThrow(
            SupplierEx<T, X> supplier, Object... objects) throws X{
        /* try */{
            if (objects == null) {
                supplier.tryGet(); // Throw exception of supplier
            }
            else {
                boolean isNull = false;
                for (int i = 0; !isNull && i != objects.length; ++i) {
                    if (objects[i] == null) {
                        supplier.tryGet();
                        isNull = true; // Terminate loop in case no exception is thrown
                    }
                }
            } /*
        } catch (X ex){
            throw new ContractException(ex);*/
        }
    }

    /**
     * Performs a check with a given predicate and throws the exception if the expression is true
     * No null checks are performed, but parameters may not be called with null
     * @param test expression that is to be tested
     * @param supplier that produces he Exception of the test equals to true
     * @throws X if predicate fails
     */
    public static <X extends RuntimeException> void throwIf(
            @NotNull BooleanSupplier test,@NotNull Supplier<X> supplier){
        if (test.getAsBoolean()) {
            throw supplier.get();
        }
    }

    //<editor-fold desc="Check for negative Number: Throws IllegalArgumentException">

    /**
     * Performs a check for a negative value on the given long.
     * Causes an IllegalArgumentException exception if the check fails.
     * @param number as long value
     * @throws IllegalArgumentException if number is smaller zero
     */
    public static void checkNegative(long number) {
        checkNegative((Number) number, null);
    }

    /**
     * Performs a check for a negative value on the given long.
     * Causes an IllegalArgumentException exception if the check fails.
     * @param number as long value
     * @param paraName name of the parameter that caused the exception. Will be specified in the exception message
     * @throws IllegalArgumentException if number is smaller zero
     */
    public static void checkNegative(long number, String paraName) {
        checkNegative((Number) number, paraName);
    }

    /**
     * Performs a check for a negative value on the given char value.
     * Causes an IllegalArgumentException exception if the check fails.
     * @param charValue as char value
     * @throws IllegalArgumentException if number is smaller zero
     */
    public static void checkNegative(char charValue) {
        checkNegative(charValue, null);
    }

    /**
     * Performs a check for a negative value on the given char value.
     * Causes an IllegalArgumentException exception if the check fails.
     * @param number as char value
     * @param paraName name of the parameter that caused the exception. Will be specified in the exception message
     * @throws IllegalArgumentException if number is smaller zero
     */
    public static void checkNegative(char number, String paraName) {
        checkNegative((long) number, paraName);
    }

    /**
     * Performs a check for a negative value on the double.
     * Causes an IllegalArgumentException exception if the check fails.
     * @param number as double value
     * @throws IllegalArgumentException if number is smaller zero
     */
    public static void checkNegative(double number) {
        checkNegative((Number) number, null);
    }

    /**
     * Performs a check for a negative value on the double.
     * Causes an IllegalArgumentException exception if the check fails.
     * @param number as double value
     * @param paraName name of the parameter that caused the exception. Will be specified in the exception message
     * @throws IllegalArgumentException if number is smaller zero
     */
    public static void checkNegative(double number, String paraName) {
        checkNegative((Number) number, paraName);
    }

    private static void checkNegative(Number number, String paraName) {
        boolean isNegative = number.doubleValue() < 0;
        if (isNegative){
            paraName = paraName != null
                    ? paraName = " \"" + paraName + "\""
                    : "";
            throw new IllegalArgumentException("Parameter" + paraName + " may not be null");
        }
    }

    public static <X extends Throwable> void check(boolean verifier, Supplier<X> exception) throws X {
        if (!verifier) throw exception.get();
    }

    public static <X extends Throwable> void check(BooleanSupplier verifier, Supplier<X> exception) throws X {
        if (!verifier.getAsBoolean()) throw exception.get();
    }
    //</editor-fold>

}