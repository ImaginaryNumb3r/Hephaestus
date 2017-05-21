package core.datastructure.value;

import core.tuple.Tuple;

/**
 * @author Patrick
 * @since 21.05.2017
 *
 * Saves the height and width of a 2D collection.
 * Value class that only contains non-nullable values.
 */
public class Bounds implements Tuple<Integer, Integer> {
    private final int WIDTH;
    private final int HEIGHT;

    public Bounds(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Deprecated method, due to backwards comparability with Tuple.
     * Use only when referred to as a Tuple. Do not call manually to improve code readability.
     */
    @Deprecated
    @Override
    public Integer getA() {
        return WIDTH;
    }

    /**
     * Deprecated method, due to backwards comparability with Tuple.
     * Use only when referred to as a Tuple. Do not call manually to improve code readability.
     */
    @Deprecated
    @Override
    public Integer getB() {
        return HEIGHT;
    }
}
