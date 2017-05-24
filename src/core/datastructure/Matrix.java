package core.datastructure;

import com.sun.istack.internal.NotNull;
import core.datastructure.value.Bounds;
import core.exception.NoImplementationException;
import core.tuple.Tuple;
import core.util.HashCode;
import core.util.annotations.ToTest;
import core.util.collections.Maps;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible2D;
import core.util.interfaces.Collection2D;
import functional.TriFunction;
import processing.imaging.Iterator2D;
import util.hash.HashGenerator;

import java.awt.*;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/**
 * Representation of a 2D data structure
 *
 * Creator: Patrick
 * Created: 13.12.2016
 * TODO: ToString
 * TODO: Matrix operations
 * TODO: 2D Iterator Strategy, like with Trees
 */
@ToTest
public class Matrix<T> implements Collection2D<T> {
    private final T[][] _matrix;
    private final int _size; // Calculate size only once

    //<editor-fold desc="Constructors">
    /**
     * Creates a new instance of a Matrix
     * @param width of the matrix. May only be zero if height is zero as well
     * @param height of the matrix. May only be zero if width is zero as well
     * @throws NegativeArraySizeException if one of the indices is negative
     * @throws InvalidParameterException when width or height are initialized with zero, but not both
     */ @SuppressWarnings("unchecked")
    public Matrix(int width, int height) {
        areValidParameters(width, height, false);
        _matrix = (T[][])new Object[width][height];
        _size = width * height;
    }

    public Matrix(@NotNull T[][] matrix) {
        Contract.checkNull(matrix, "matrix");
        _matrix = matrix;
        _size = getHeight() * getWidth();
    }
    //</editor-fold>

    //<editor-fold desc="Construction Validation">
    /**
     * Checks parameters of the matrix amd returns true if they are valid
     * @param width of the matrix. May only be zero if height is zero as well
     * @param height of the matrix. May only be zero if width is zero as well
     * @throws NegativeArraySizeException if one of the indices is negative
     * @throws InvalidParameterException when width or height are initialized with zero, but not both
     * @return true if parameters are valid to initialize a Matrix
     */
    public boolean areValidParameters(int width, int height){
        return areValidParameters(width, height, true);
    }

    /**
     * Checks parameters of the matrix amd returns true if they are valid
     * @param bounds of the matrix, containing height and width. Values must not be zero when they are both
     * @throws NegativeArraySizeException if one of the indices is negative
     * @throws InvalidParameterException when width or height are initialized with zero, but not both
     * @return true if parameters are valid to initialize a Matrix
     */
    public boolean areValidParameters(Bounds bounds){
        return areValidParameters(bounds.getWidth(), bounds.getHeight(), true);
    }

    /**
     * Checks parameters of the matrix
     * @param suppressExceptions to decide whether to suppress the expression or not
     * @param width of the matrix. May only be zero if height is zero as well
     * @param height of the matrix. May only be zero if width is zero as well
     * @throws NegativeArraySizeException if one of the indices is negative
     * @throws InvalidParameterException when width or height are initialized with zero, but not both
     */
    private boolean areValidParameters(int width, int height, boolean suppressExceptions){
        boolean isValid = width < 0 || height < 0;
        if (!isValid && !suppressExceptions) throw new NegativeArraySizeException();
        isValid &= width == 0 && width != height;
        if (isValid && !suppressExceptions) throw new InvalidParameterException("Matrix can only be empty if it is initialized with both, width and height at 0");

        return isValid;
    }
    //</editor-fold>

    //<editor-fold desc="Accessors">
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

    public void setAt(int width, int heigth, T value){
        _matrix[width][heigth] = value;
    }
    //</editor-fold>

    //<editor-fold desc="Bound Information">
    public int size(){
        return _size;
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
    //</editor-fold>

    public void add(Accessible2D<T> accssor){
        throw new NoImplementationException();
    }

    public void subtract(Accessible2D<T> accssor){
        throw new NoImplementationException();
    }

    public void multiply(Number factor){
        throw new NoImplementationException();
    }

    private void manipulate(){
        for (T t : this) {

        }
    }

    @Override
    @ToTest
    public boolean contains(T element) {
        for (T cur : this) {
            boolean equals = Objects.equals(element, cur);
            if (equals) return true;
        }
        return false;
    }

    @Override
    @ToTest
    public boolean containsAll(Collection<T> elements) {
        HashSet<T> set = new HashSet<>(elements);

        for (T cur : this) {
            set.remove(cur);
        }

        return set.isEmpty();
    }

    @ToTest
    public void mapValues(TriFunction<Integer, Integer, T, T> function){
        Contract.checkNull(function, "function");
        for (int w = 0; w != getWidth(); ++w){
            for (int h = 0; h != getHeight(); ++h){
                T value = _matrix[h][w];
                _matrix[h][w] = function.apply(h, w, value);
            }
        }
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

    @Override
    public boolean equals(Object obj) {
        return HashCode.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .append(iterator())
                .toHashCode();
    }
}
