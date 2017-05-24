package stream;

import core.exception.InstanceNotAllowedException;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public final class Count {

    private Count(){
        throw new InstanceNotAllowedException(getClass());
    }

    /**
     * Executes the given runnable method a number of times.
     * @param count for the amount of calls to the method. Must be greater than zero
     * @param runnable the method that will be executed each time
     * @throws IllegalArgumentException if count is smaller than zero
     */
    public static void repeat(int count, Runnable runnable){
        if (count < 0) throw new IllegalArgumentException("count must not be smaller than zero");

        for (int i =0 ; i < count; ++i){
            runnable.run();
        }
    }
}
