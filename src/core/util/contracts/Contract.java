package core.util.contracts;

import com.sun.istack.internal.NotNull;
import core.exception.ParameterNullException;
import core.util.collections.iterating.Iterators;
import core.util.contracts.exceptions.ContractException;
import functional.exception.SupplierEx;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

/**
 * Creator: Patrick
 * Created: 11.12.2016
 *
 * Experimental Class
 */
@SuppressWarnings("WeakerAccess")

// TODO: Consider removing everything from Contract, other than checkNull
public class Contract implements AutoCloseable {
    private BooleanSupplier _contract;

    public Contract(BooleanSupplier contract){
        checkNull(contract);
        _contract = contract;
    }

    /**
     * Performs the contract checkNull
     * @throws ContractException if contract is violated
     */
    @Override
    public void close() throws ContractException {
        if (_contract.getAsBoolean()) throw new ContractException("Contract has been violated");
    }


    // ==================╗
    //   Static Methods  ║
    // ==================╝

    /**
     * Performs a checkNull on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ContractException if contract is violated
     */
    public static void checkNull(Object... objects) throws ContractException {
        checkNull(Iterators.asIterable(objects));
    }

    /**
     * Performs a checkNull on the given objects and throws a named ParameterNullException if it is null
     * @param object argument containing the object to be tested
     * @param name name of the parameter that was tested
     * @throws ContractException if contract is violated
     */
    public static void checkNull(Object object, String name) throws ContractException {
        name = name == null ? "null" : name;
        if (object == null) throw new ParameterNullException(name);
    }

    /**
     * Performs a checkNull on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ContractException if contract is violated
     */
    public static void checkNull(Iterable<Object> objects) throws ContractException {
        for (Object object : objects) {
            if (object == null) throw new ParameterNullException();
        }
    }

    /**
     * Performs a null check on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ContractException if contract is violated
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
    //</editor-fold>

}