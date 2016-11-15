package infastructure.path.exceptions;

/**
 * @author Patrick
 * @created 31.07.2016
 */
public class NoParentException extends RuntimeException {

    public NoParentException() {
        super();
    }

    public NoParentException(String message) {
        super(message);
    }

    public NoParentException(String message, Throwable cause) {
        super(message, cause);
    }

}
