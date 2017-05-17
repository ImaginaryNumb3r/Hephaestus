package core.exception;

import core.util.contracts.exceptions.ContractException;

/**
 * @author Patrick
 * @since 26.11.2016
 */
public class ParameterNullException extends ContractException {
    public static final String MESSAGE = "Parameter may not be null";

   private static String makeMessage(String parameterName){
       return  parameterName != null
       ? "Parameter: \"" + parameterName + "\" may not be null"
       : MESSAGE;
   }

    public ParameterNullException() {
        super(MESSAGE);
    }

    public ParameterNullException(String parameterName) {
        super(makeMessage(parameterName));
    }
}
