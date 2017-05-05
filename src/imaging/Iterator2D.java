package imaging;

import core.datastructure.Matrix;

import java.util.Iterator;

/**
 * InnerIterator for 2D objects
 * InnerIterator which iterates through a matrix, line by line and returns the according pixels
 *
 * @author Patrick
 * @since 12.11.2016
 */
public class Iterator2D<T> implements Iterator<T> {
    protected Matrix<T> _matrix;
    protected int _curX;
    protected int _curY;

    //<editor-fold desc="Constructors">
    public Iterator2D(T[][] matrix) {
        _matrix = new Matrix<>(matrix);
    }

    public Iterator2D(Matrix<T> matrix) {
        _matrix = matrix;
    }
    //</editor-fold>

    /**
     * Returns true if the iteration has more pixels
     * @return true if the iteration has more pixels
     */
    @Override
    public boolean hasNext() {
        return _curY < _matrix.getWidth() && _curX < _matrix.getHeight();
    }

    /**
     * Traverses the matrix line by line and returns the according pixels
     *
     * Returns the next pixel of the matrix
     * @return the next pixel of the matrix
     */
    @Override
    public T next(){
        int x = _curX;
        int y = _curY;


        ++_curY;
        // Reset line and jump to next one
        if (_curY >= _matrix.getWidth()){
            _curY = 0;
            ++_curX;
        }

        return _matrix.get(x, y);
    }
}
