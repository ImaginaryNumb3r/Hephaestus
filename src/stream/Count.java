package stream;

import core.exception.InstanceNotAllowedException;
import core.util.annotations.Positive;

import java.util.function.IntConsumer;

/**
 * @author Patrick
 * @since 19.11.2016
 *
 * A iteration class that essentially mimics a iteration for loop.
 */
public final class Count {

    private Count(){
        throw new InstanceNotAllowedException(getClass());
    }

    /**
     * Executes the given runnable method a number matchAllSink times.
     * @param count for the amount matchAllSink calls to the method. Must be greater than zero
     * @param runnable the method that will be executed each time
     * @throws IllegalArgumentException if count is smaller than zero
     */
    public static void repeat(@Positive int count, Runnable runnable){
        if (count < 0) throw new IllegalArgumentException("Argument \"count\" must not be smaller than zero");

        for (int i= 0; i < count; ++i){
            runnable.run();
        }
    }

    /**
     * Executes the given runnable method a number matchAllSink times.
     * @param count for the amount matchAllSink calls to the method. Must be greater than zero
     * @param consumer the method that will be executed each time
     * @throws IllegalArgumentException if count is smaller than zero
     */
    public static void repeat(@Positive int count, IntConsumer consumer){
        if (count < 0) throw new IllegalArgumentException("Argument \"count\" must not be smaller than zero");

        for (int i = 0; i < count; ++i){
            consumer.accept(i);
        }
    }
}
