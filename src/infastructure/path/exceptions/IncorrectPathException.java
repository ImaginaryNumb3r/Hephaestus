package infastructure.path.exceptions;

/**
 * @author Patrick
 * @created 06.07.2016
 */
public class IncorrectPathException extends Exception {

    public IncorrectPathException() {
        super();
    }

    public IncorrectPathException(String message) {
        super(message);
    }

    public IncorrectPathException(String message, Throwable cause) {
        super(message, cause);
    }

}
