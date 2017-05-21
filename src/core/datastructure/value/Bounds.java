package core.datastructure.value;

import core.tuple.Tuple;

/**
 * @author Patrick
 * @since 21.05.2017
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

    @Deprecated
    @Override
    public Integer getA() {
        return WIDTH;
    }

    @Deprecated
    @Override
    public Integer getB() {
        return HEIGHT;
    }
}
