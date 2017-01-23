package core.util.contracts.exceptions;

/**
 * Creator: Patrick
 * Created: 11.12.2016
 * Purpose: Is thrown when a contract is validated
 */
public class ContractException extends RuntimeException {

    public ContractException() {
    }

    public ContractException(String message) {
        super(message);
    }

    public ContractException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContractException(Throwable cause) {
        super(cause);
    }

    public ContractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
