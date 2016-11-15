package infastructure.datastructure.exceptions;

/**
 * @author Patrick
 * @created 29.05.2016
 */
public class IllegalNodeStateException extends RuntimeException{

    public IllegalNodeStateException() {
        super();
    }

    public IllegalNodeStateException(String message) {
        super(message);
    }

    public IllegalNodeStateException(String message, Throwable cause) {
        super(message, cause);
    }

}
