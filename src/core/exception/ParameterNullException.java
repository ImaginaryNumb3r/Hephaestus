package core.exception;

import core.util.contracts.exceptions.ContractException;

/**
 * @author Patrick
 * @since 26.11.2016
 */
public class ParameterNullException extends ContractException {
    public static final String MESSAGE = "Parameter may not be null";

    public ParameterNullException() {
        super(MESSAGE);
    }
}
