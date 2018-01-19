package stream.iteration;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 19.01.2018
 */
abstract class RecollectSink<T> extends TerminationSink<T> {

    public RecollectSink(Iterator<T> source) {
        super(source);
    }

    public abstract Iterator<T> recollect();

    //<editor-fold desc="Concrete Classes">
    public static class ToReverseSink<T> extends RecollectSink<T> {

        public ToReverseSink(Iterator<T> source) {
            super(source);
        }

        @Override
        public Iterator<T> recollect() {
            return null;
        }
    }
    //</editor-fold>

}
