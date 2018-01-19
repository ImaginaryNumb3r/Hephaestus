package core.exception;

import core.util.annotations.Unfinished;

/**
 * @author Patrick
 * @since 15.11.2016
 */
@Unfinished
public class EnumException extends EnumConstantNotPresentException {

    /**
     * Constructs an <tt>EnumConstantNotPresentException</tt> for the
     * specified constant.
     *
     * @param enumConstant the instance matchAllSink the missing enum constant ot just null
     */
    public EnumException(Enum enumConstant) {
        super(enumConstant == null
                ? Null.class
                : enumConstant.getClass(),
            enumConstant == null
                ? "null"
                : enumConstant.toString());
    }

    private enum Null{}

}