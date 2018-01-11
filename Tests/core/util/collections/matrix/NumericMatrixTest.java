package core.util.collections.matrix;

import org.junit.Test;

/**
 * Creator: Patrick
 * Created: 09.12.2017
 * Purpose:
 */
public class NumericMatrixTest {

    @Test
    public void testDeterminate() {
        NumericMatrix<Integer> matrix = MatrixTest.getNumericalMatrix();
        double determinate = matrix.determinate();
        double expected = 0;

        assert determinate == expected;
        assert matrix.isQuadratic();

        // Test empty Matrix
        NumericMatrix<Integer> emptyMatrix = new NumericMatrix<>(0, 0, int.class);
        determinate = emptyMatrix.determinate();
        expected = 0;

        assert determinate == expected;
        assert emptyMatrix.isQuadratic();
    }
}