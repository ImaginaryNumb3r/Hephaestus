package datastructure.traverser.exceptions;

/**
 * @author Patrick
 * @since 26.01.2017
 *
 * Is thrown if a value in a tree does not exist
 */
public class NoValueException extends RuntimeException {

    public NoValueException() {
    }

    public NoValueException(String message) {
        super(message);
    }

    public NoValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoValueException(Throwable cause) {
        super(cause);
    }

    public NoValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
