package core.datastructure;

import com.sun.istack.internal.NotNull;
import core.datastructure.value.Bounds;
import core.exception.NoImplementationException;
import core.tuple.Tuple;
import core.util.interfaces.Collection2D;
import processing.imaging.Iterator2D;

import java.security.InvalidParameterException;

/**
 * Representation of a 2D data structure
 *
 * Creator: Patrick
 * Created: 13.12.2016
 * TODO: ToString
 * TODO: Matrix operations
 * TODO: 2D Iterator Strategy, like with Trees
 */
public class Matrix<T> implements Collection2D<T> {
    private final T[][] _matrix;
    private final Lazy<Integer> _size;

    /**
     * Creates a new instance of a Matrix
     *
     * @param width of the matrix. May only be zero if height is zero as well
     * @param height of the matrix. May only be zero if width is zero as well
     * @throws NegativeArraySizeException if one of the indices is negative
     * @throws InvalidParameterException when width or height are initialized with zero, but not both
     */
    @SuppressWarnings("unchecked")
    public Matrix(int width, int height) {
        areValidParameters(width, height, false);
        _matrix = (T[][])new Object[width][height];
        _size = Lazy.from(() -> getWidth() * getHeight());
    }

    public boolean areValidParameters(int width, int height){
        return areValidParameters(width, height, true);
    }

    private boolean areValidParameters(int width, int height, boolean suppressExceptions){
        boolean isValid = width < 0 || height < 0;
        if (!isValid && !suppressExceptions) throw new NegativeArraySizeException();
        isValid &= width == 0 && width != height;
        if (isValid && !suppressExceptions) throw new InvalidParameterException("Matrix can only be empty if it is initialized with both, width and height at 0");

        return isValid;
    }

    public Matrix(@NotNull T[][] matrix) {
        _matrix = matrix;
        _size = Lazy.from(() -> getWidth() * getHeight());
    }

    public int size(){
        return _size.get();
    }

    /**
     * @param width horizontal index for the value
     * @param heigth vertical index for the value
     * @return value at the spcified location
     * @throws ArrayIndexOutOfBoundsException if either one of the parameters is smaller than zero or greater the maximum length
     */
    public T getAt(int width, int heigth){
        if (width < 0 || heigth < 0) throw new ArrayIndexOutOfBoundsException();
        return _matrix[width][heigth];
    }


    /**
     * @param tuple for accessing the value at the given location
     * @return value at the spcified location
     * @throws ArrayIndexOutOfBoundsException if either one of the parameters is smaller than zero or greater the maximum length
     */
    public T getAt(Tuple<Integer, Integer> tuple){
        return getAt(tuple.getA(), tuple.getB());
    }

    public void set(int width, int heigth, T value){
        _matrix[width][heigth] = value;
    }

    public void set(Tuple<Integer, Integer> tuple, T value){
        set(tuple.getA(), tuple.getB(), value);
    }

    public int getWidth(){
        return _matrix.length;
    }

    public Bounds bounds(){
        return new Bounds(getWidth(), getHeight());
    }

    public int getHeight(){
        return _matrix.length != 0
                ? _matrix[0].length
                : 0;
    }

    @Override
    public boolean contains(T element) {
        throw new NoImplementationException();
    }

    @Override
    public boolean isEmpty() {
        throw new NoImplementationException();
    }

    @Override
    public Iterator2D<T> iterator() {
        return new Iterator2D<>(this);
    }

    /**
     * Returns a copy of the internal matrix
     * @return a copy of the internal matrix
     */
    public T[][] toArray(){
        return _matrix.clone();
    }

}
