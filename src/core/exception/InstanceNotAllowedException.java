package core.exception;

import org.jetbrains.annotations.NotNull;

/**
 * @author Patrick
 * @description
 * @since 23.05.2017
 */
public class InstanceNotAllowedException extends RuntimeException {

    public InstanceNotAllowedException(@NotNull Class<?> type) {
        super("Class \"" + type.getName() +"\" cannot be instantiated");
    }
}
