package imaging;

import java.awt.*;
import java.util.Iterator;

/**
 * @author Patrick
 * @since 12.11.2016
 */
public class RectangleIterator implements Iterator<Coord>, Iterable<Coord> {
    private final int X;
    private final int Y;
    private final int WIDTH;
    private final int HEIGHT;
    private int _curX;
    private int _curY;

    public RectangleIterator(int x, int y, int width, int height) {
        X = x;
        Y = y;
        WIDTH = width;
        HEIGHT = height;
        _curX = x;
        _curY = y;
    }

    public RectangleIterator(Rectangle rectangle){
        this(rectangle.x, rectangle.y, (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public boolean hasNext() {
        return _curY < WIDTH + Y && _curX < HEIGHT + X;
    }

    @Override
    public Coord next() {
        Coord coord = new Coord(_curX, _curY);

        ++_curY;
        if (_curY >= WIDTH + Y){
            _curY = Y;
            ++_curX;
        }
        return coord;
    }

    @Override
    public Iterator<Coord> iterator() {
        return this;
    }
}
