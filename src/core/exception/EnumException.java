package core.exception;

/**
 * @author Patrick
 * @since 15.11.2016
 */
public class EnumException extends EnumConstantNotPresentException {

    /**
     * Constructs an <tt>EnumConstantNotPresentException</tt> for the
     * specified constant.
     *
     * @param enumConstant the instance of the missing enum constant
     */
    public EnumException(Enum enumConstant) {
        super(enumConstant.getClass(), enumConstant.toString());
    }
}