package core.datastructure.value;

import java.awt.*;

/**
 * @author Patrick
 *         Created: 24.04.2016
 *         Purpose:
 */
public class Coord2D {
    private int _x;
    private int _y;

    public Coord2D(Coord2D coord){
        this(coord.getX(), coord.getY());
    }

    public Coord2D(int x, int y) {
        _x = x;
        _y = y;
    }

    public Coord2D(Rectangle rectangle){
        this(rectangle.width, rectangle.height);
    }

    public Coord2D() {
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

    public void add(Coord2D add){
        _x += add._x;
        _y += add._y;
    }

    public void invert(){
        int temp = _x;
        _x = _y;
        _y = temp;
    }

}
