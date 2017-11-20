package functional.exception;

/**
 * Creator: Patrick
 * Created: 20.11.2017
 * Purpose:
 */
public class FunctionalMappingException extends RuntimeException{

    public FunctionalMappingException() {
        super();
    }

    public FunctionalMappingException(String message) {
        super(message);
    }

    public FunctionalMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionalMappingException(Throwable cause) {
        super(cause);
    }

    protected FunctionalMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
