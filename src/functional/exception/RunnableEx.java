package functional.exception;

import core.exception.FunctionalException;

/**
 * @author Patrick
 * @description
 * @since 23.01.2017
 */
@FunctionalInterface
public interface RunnableEx extends Runnable{

    /**
     * Executes a method, but a runtime exception may also be thrown
     * @throws RuntimeException will have an inner exception, which needs to be specified in the implementation
     */
    @Override
    default void run(){
        try {
            tryRun();
        } catch (FunctionalException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes a method, but an exception may also be thrown
     * @throws FunctionalException will have an inner exception, which needs to be specified in the implementation
     */
    void tryRun() throws FunctionalException;

}
