package imaging;

import java.awt.*;

/**
 * @author Patrick
 * @since 10.11.2016
 * Mutable Coordinate
 * TODO: Replace with Tuple
 */
public class Coord {
    private int _x;
    private int _y;

    public Coord(Coord coord){
        this(coord.getX(), coord.getY());
    }

    public Coord(int x, int y) {
        _x = x;
        _y = y;
    }

    public Coord(Rectangle rectangle){
        this(rectangle.width, rectangle.height);
    }

    public Coord() {
        this(0,0);
    }

    public int getX() {
        return _x;
    }

    public void setX(int x) {
        _x = x;
    }

    public int getY() {
        return _y;
    }

    public void setY(int y) {
        _y = y;
    }

    public int total(){
        return _x + _y;
    }

    public void incY(){
        ++_y;
    }

    public void incX(){
        ++_x;
    }

    @Override
    public String toString() {
        return "X: " + _x + " | Y: " + _y;
    }

    public void mult(int mult){
        _x *= mult;
        _y *= mult;
    }

    public void add(Coord add){
        _x += add._x;
        _y += add._y;
    }

    public void invert(){
        int temp = _x;
        _x = _y;
        _y = temp;
    }
}