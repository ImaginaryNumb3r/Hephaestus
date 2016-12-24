package core.util.contracts;

import core.exception.FunctionalException;
import core.exception.ParameterNullException;
import core.util.contracts.exceptions.ContractException;
import functional.exception.SupplierEx;

import java.util.function.BooleanSupplier;

/**
 * Creator: Patrick
 * Created: 11.12.2016
 * Purpose:
 */
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
        checkAndThrow(() -> {
            throw new ParameterNullException();
        }, objects);
    }

    /**
     * Performs a checkNull on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ContractException if contract is violated
     */
    public static void checkNull(Iterable<Object> objects) throws ContractException {
        checkAndThrow(() -> {
            throw new ParameterNullException();
        }, objects);
    }

    /**
     * Performs a checkNull on all given objects and throws a ParameterNullException if any are null
     * @param objects arguments containing objects to be tested
     * @throws ContractException if contract is violated
     */
    public static <T> void checkAndThrow(SupplierEx<T> supplier, Object... objects) throws ContractException {
        try {
            if (objects == null) supplier.tryGet();

            if (objects != null) {
                boolean isNull = false;
                for (int i = 0; !isNull && i != objects.length; ++i) {
                    if (objects[i] == null) {
                        supplier.tryGet();
                        isNull = true;
                    }
                }
            }
        } catch (FunctionalException ex){
            throw new ContractException();
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