package core.util.collections.matrix;

import core.datastructure.value.Coord2D;
import core.util.collections.iteration.Iterables;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Creator: Patrick
 * Created: 18.08.2017
 * Purpose:
 */
public class MatrixTest {

    public static NumericMatrix<Integer> getNumericalMatrix() {
        NumericMatrix<Integer> matrix = new NumericMatrix<>(3, 3, int.class);
        int count = 0;
        for (int w = 0; w != matrix.getWidth(); ++w){
            for (int h = 0; h != matrix.getHeight(); ++h){
                matrix.setAt(w, h, ++count);
            }
        }

        return matrix;
    }

    @Test
    public void testIteration() throws Exception {
        Matrix<Integer> matrix = new MutatingMatrix<>(3, 4);

        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int count = 0;
        for (int w = 0; w != matrix.getWidth(); ++w){
            for (int h = 0; h != matrix.getHeight(); ++h){
                matrix.setAt(w, h, count++);
            }
        }

        //noinspection StatementWithEmptyBody
        for (Integer integer : matrix) {
            // Ignore
        }
    }

    @Test
    public void testCoordIterator() throws Exception {
        Matrix<Integer> matrix = new MutatingMatrix<>(3, 3);
        Set<Coord2D> coordSet = new HashSet<>();

        for (Coord2D coord : Iterables.from(matrix.coordIterator())) {
            int i = coord.hashCode();
            assert !coordSet.contains(coord);
            coordSet.add(coord);
        }

        assert coordSet.size() == 9;
    }

    @Test
    public void testToString() throws Exception {
        Matrix<Integer> matrix = getNumericalMatrix();
        final String expected = "[ 1 2 3 ]\n" + "[ 4 5 6 ]\n" + "[ 7 8 9 ]";

        assert matrix.toString().equals(expected);
    }

    @Test
    public void testToString2() throws Exception {
        Matrix<Integer> matrix = getNumericalMatrix();
        final String expected = "[ a b c ]\n" + "[ d e f ]\n" + "[ g h i ]";

        String matrixStr = matrix.toString(num -> {
            char ch = (char) ('a' + num - 1);
            return String.valueOf(ch);
        });

        assert matrixStr.equals(expected);
    }
}