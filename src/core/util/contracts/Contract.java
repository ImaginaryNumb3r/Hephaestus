package core.util.contracts;

import core.exception.FunctionalException;
import core.exception.ParameterNullException;
import core.util.collections.Iterators;
import core.util.contracts.exceptions.ContractException;
import functional.exception.SupplierEx;

import java.util.function.BooleanSupplier;

/**
 * Creator: Patrick
 * Created: 11.12.2016
 *
 * Experimental Class
 */
@SuppressWarnings("WeakerAccess")
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
    public static <T> void checkAndThrow(SupplierEx<T> supplier, Object... objects) throws ContractException {
        try {
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
            }
        } catch (FunctionalException ex){
            throw new ContractException(ex);
        }
    }

    /**
     * Performs a checkNull on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ContractException if contract is violated
     */
    public static void checkAndThrow(String message, Object... objects) throws ContractException {
        checkAndThrow(() -> {throw new FunctionalException(message);}, objects);
    }
}