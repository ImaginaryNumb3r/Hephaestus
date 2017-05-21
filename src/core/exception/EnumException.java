package core.exception;

import core.util.annotations.Unfinished;

/**
 * @author Patrick
 * @since 15.11.2016
 */
@Unfinished
// TODO: Conceptual problem: Does not work with null as parameter
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

    /**
     * Constructs an <tt>EnumConstantNotPresentException</tt> for the
     * specified constant.
     *
     * @param enumConstant the instance of the missing enum constant
     */
    public EnumException(Enum enumConstant, Class<? extends Enum> enumClass) {
        super(enumClass, enumToString(enumConstant));
    }

    private static String enumToString(Enum enumeration){
        return enumeration == null
                ? "null"
                : enumeration.toString();
    }
}