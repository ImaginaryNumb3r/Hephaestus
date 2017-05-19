package core.exception;

/**
 * @author Patrick
 * @since 19.05.2017
 *
 * An exception that is thrown when something has not been implemented yet.
 * This is supposed to replace the NotImplementedException and UnsupportedOperationException
 * It replaces the former one because it is an internal Oracle API
 * And it replaces the latter one, because it is not specific enough and can be used for other meanings as well
 */
public class NoImplementationException extends Exception {

    public NoImplementationException() {
    }

    public NoImplementationException(String message) {
        super(message);
    }
}
