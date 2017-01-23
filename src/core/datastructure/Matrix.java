package core.datastructure;

import core.tuple.Tuple;
import imaging.Iterator2D;

import java.security.InvalidParameterException;
import java.util.Iterator;

/**
 * Representation of a 2D data structure
 *
 * Creator: Patrick
 * Created: 13.12.2016
 * TODO: ToString
 */
public class Matrix<T> implements Iterable<T>{
    private final T[][] _matrix;

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
        if (width < 0 || height < 0) throw new NegativeArraySizeException();
        if (width == 0 && width != height) throw new InvalidParameterException("Matrix can only be empty if it is initialized with both, width and height at 0");

        _matrix = (T[][])new Object[width][height];
    }

    public Matrix(T[][] matrix) {
        _matrix = matrix;
    }

    /**
     * @param width horizontal index for the value
     * @param heigth vertical index for the value
     * @return value at the spcified location
     * @throws ArrayIndexOutOfBoundsException if either one of the parameters is smaller than zero or greater the maximum length
     */
    public T get(int width, int heigth){
        if (width < 0 || heigth < 0) throw new ArrayIndexOutOfBoundsException();

        return _matrix[width][heigth];
    }


    /**
     * @param tuple for accessing the value at the given location
     * @return value at the spcified location
     * @throws ArrayIndexOutOfBoundsException if either one of the parameters is smaller than zero or greater the maximum length
     */
    public T get(Tuple<Integer, Integer> tuple){
        return get(tuple.getA(), tuple.getB());
    }

    public void set(int width, int heigth, T value){
        _matrix[width][heigth] = value;
    }

    public void Set(Tuple<Integer, Integer> tuple, T value){
        set(tuple.getA(), tuple.getB(), value);
    }


    public int getWidth(){
        return _matrix.length;
    }

    public int getHeight(){
        return _matrix.length != 0
                ? _matrix[0].length
                : 0;
    }

    @Override
    public Iterator<T> iterator() {
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
