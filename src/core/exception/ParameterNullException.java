package core.exception;

/**
 * @author Patrick
 * @since 26.11.2016
 */
public class ParameterNullException extends IllegalArgumentException {
    public static final String MESSAGE = "Parameter may not be null";

    public ParameterNullException() {
        super(MESSAGE);
    }
}
