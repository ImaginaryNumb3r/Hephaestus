package core.exception;

/**
 * @author Patrick
 * @description
 * @since 23.05.2017
 */
public class InstanceNotAllowedException extends RuntimeException {

    public InstanceNotAllowedException(Class<?> tClass) {
        super("Class \"" + tClass.getName() +"\" cannot be instantiated");
    }
}
