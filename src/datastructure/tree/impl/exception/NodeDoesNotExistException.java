package datastructure.tree.impl.exception;

/**
 * @author Patrick
 * @description
 * @since 29.01.2017
 */
public class NodeDoesNotExistException extends RuntimeException {

    public NodeDoesNotExistException() {
    }

    public NodeDoesNotExistException(String message) {
        super(message);
    }

    public NodeDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public NodeDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
