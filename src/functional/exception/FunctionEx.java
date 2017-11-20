package functional.exception;


import java.util.function.Function;

/**
 * Creator: Patrick
 * Created: 20.11.2017
 * Purpose:
 */

public interface FunctionEx<T, R, X extends Exception> extends Function<T, R> {

    R tryApply(T t) throws X;

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @throws FunctionalMappingException if an exception of type X occurs.
     *         Will contain exception of type X as inner exception
     * @return the function result
     */
    @Override
    default R apply(T t){
        try {
            return tryApply(t);
        } catch (Exception ex) {
            throw new FunctionalMappingException(ex);
        }
    }
}
