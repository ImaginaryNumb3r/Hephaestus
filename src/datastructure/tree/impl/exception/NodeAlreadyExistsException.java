package datastructure.tree.impl.exception;

/**
 * @author Patrick
 * @since 29.01.2017
 */
public class NodeAlreadyExistsException extends RuntimeException {

    public NodeAlreadyExistsException() {
    }

    public NodeAlreadyExistsException(String message) {
        super(message);
    }

    public NodeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public NodeAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
