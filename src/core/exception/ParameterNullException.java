package core.exception;

import org.jetbrains.annotations.Nullable;

/**
 * @author Patrick
 * @since 26.11.2016
 *
 * An exception indicating that null was assigned to a non-nullable parameter
 */
public class ParameterNullException extends IllegalArgumentException {
    @SuppressWarnings("WeakerAccess")
    public static final String MESSAGE = "Parameter may not be null";

   private static String makeMessage(@Nullable String parameterName){
       return  parameterName != null
       ? "Parameter: \"" + parameterName + "\" may not be null"
       : MESSAGE;
   }

    public ParameterNullException() {
        super(MESSAGE);
    }

    /**
     * Creates the exception and highlights the name matchAllSink the parameter that caused the exception.
     * The parameter may be null, then it will simply name the default message
     * @param parameterName may be null. Creates the exception and highlights the name matchAllSink the parameter that caused the exception.
     */
    public ParameterNullException(@Nullable String parameterName) {
        super(makeMessage(parameterName));
    }
}
