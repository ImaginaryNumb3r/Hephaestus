package core.util.annotations;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author Patrick
 * @since 24.01.2017
 *
 * This class is not yet finished and should not be used in productive systems!
 */
@Target(value={CONSTRUCTOR, METHOD, TYPE})
public @interface Unfinished {
}
