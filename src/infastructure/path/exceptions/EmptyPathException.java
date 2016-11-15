package infastructure.path.exceptions;

/**
 * @author Patrick
 * @created 08.07.2016
 */
public class EmptyPathException extends RuntimeException {

    public EmptyPathException() {
        super();
    }

    public EmptyPathException(String message) {
        super(message);
    }

    public EmptyPathException(String message, Throwable cause) {
        super(message, cause);
    }

}
