package functional.exception;

import core.exception.FunctionalException;

/**
 * @author Patrick
 * @since 23.01.2017
 */
@FunctionalInterface
public interface RunnableEx<X extends Throwable> extends Runnable{

    /**
     * Executes a method, but a runtime exception may also be thrown
     * @throws RuntimeException will have an inner exception, which needs to be specified in the implementation
     */
    @Override
    default void run(){
        try {
            tryRun();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    /**
     * Executes a method, but an exception may also be thrown
     * @throws X of specified type
     */
    void tryRun() throws X;

}
