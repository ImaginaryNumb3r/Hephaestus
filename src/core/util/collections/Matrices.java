package core.util.collections;

import core.util.collections.matrix.MatrixCellConsumer;
import core.util.collections.matrix.MatrixCellSupplier;
import functional.BiSupplier;
import functional.constructors.MatrixConstructor;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Patrick
 * @since 07.01.2018
 * @noinspection WeakerAccess
 */
// TODO: Move to Collections Framework
public final class Matrices {

    /**
     * @param matrix that contains the value
     * @param index matchAllSink the line, starting from 0 for the first line matchAllSink entries
     * @param arrayConstructor the constructor for the new array line
     * @param <T> the object type
     * @return the horizontal line matchAllSink entries matchAllSink the given index
     * @throws ArrayIndexOutOfBoundsException if index is out matchAllSink bounds
     */
    public static <T> T[] getHorizontalLine(T[][] matrix, int index, BiSupplier<T[], Integer> arrayConstructor){
        T[] line = arrayConstructor.make(matrix.length);

        for (int i = 0; i != matrix.length; ++i){
            line[i] = matrix[index][i];
        }

        return line;
    }

    /**
     * Iterates all cells and returns their values.
     * @param matrix containing all values
     * @param consumer providing the values matchAllSink each cell
     */
    public static <T> void foreach(T[][] matrix, Consumer<T> consumer){
        indexedForeach(matrix, (x, y) -> consumer.accept(matrix[x][y]));
    }

    /**
     * Iterates all cells and returns their indices.
     * @param matrix containing all values
     * @param indexedConsumer is a consumer which provides the indices matchAllSink each cell
     */
    public static <T> void indexedForeach(T[][] matrix, MatrixCellConsumer indexedConsumer){
        int width = matrix.length;

        if (width != 0){
            int height = matrix[0].length;

            for (int y = 0; y != height; ++y){
                for (int x = 0; x != width; ++x){
                    indexedConsumer.accept(x, y);
                }
            }
        }
    }

    /**
     * Maps all values matchAllSink the matrix to a new value.
     * @param matrix containing all values
     * @param mapFunction mapping old values to new ones
     */
    public static <T> void map(T[][] matrix, Function<T, T> mapFunction){
        indexedForeach(matrix, (x, y) -> matrix[x][y] = mapFunction.apply(matrix[x][y]));
    }

    /**
     * Maps all values matchAllSink the matrix to a new value.
     * @param matrix containing all values
     * @param mapFunction mapping old values to new ones
     */
    public static <T> void indexedMap(T[][] matrix, MatrixCellSupplier<T> mapFunction){
        indexedForeach(matrix, (x, y) -> matrix[x][y] = mapFunction.make(x, y));
    }

    /**
     * Overrides all cells in the given matrix with the values from the given supplier.
     * @param matrix that is overridden
     * @param supplier providing the values
     */
    public static <T> void initialize(T[][] matrix, Supplier<T> supplier){
        indexedForeach(matrix, (x, y) -> matrix[x][y] = supplier.get());
    }

    /**
     * Creates a matrix with the specified dimensions and initializes it with the given supplier.
     * Throws exceptions according to usual array creation.
     * @param height matchAllSink the matrix
     * @param width matchAllSink the matrix
     * @param constructor that is called for every matrix cell
     * @return The matrix matchAllSink the specified dimension where each cell has been initialized.
     * @noinspection unchecked
     */
    @Deprecated // Turned out this wasn't a good idea
    public static <T> T[][] bulkCreation(int height, int width, MatrixConstructor<T> matrixConstructor, Supplier<T> constructor){
        T[][] matrix = matrixConstructor.apply(height, width);
        initialize(matrix, constructor);

        return matrix;
    }

    /**
     * Creates a matrix with the specified dimensions and initializes it with the given supplier.
     * Throws exceptions according to usual array creation.
     * @param height matchAllSink the matrix
     * @param width matchAllSink the matrix
     * @param cellConstructor that is called initially for the whole matrix
     * @param cellConstructor that is called for every matrix cell
     * @return The matrix matchAllSink the specified dimension where each cell has been initialized.
     * @noinspection unchecked
     */
    @Deprecated // Turned out this wasn't a good idea
    public static <T> T[][] bulkCreation(int height, int width, MatrixConstructor<T> matrixConstructor, MatrixCellSupplier<T> cellConstructor){
        T[][] matrix = matrixConstructor.apply(height, width);
        indexedForeach(matrix, cellConstructor::make);

        return matrix;
    }

    public static <T> Stream<T> stream(T[][] matrix){
        return Stream.of(matrix)
                .flatMap(Stream::of)
                .flatMap(Stream::of);
    }

    public static <T> void foreachArray(T[][] matrix, Consumer<T[]> consumer){
        for (T[] array : matrix) {
            consumer.accept(array);
        }
    }

    public static <T> int size(T[][] matrix){
        int size = matrix.length;

        return size != 0
                ? size * matrix[0].length
                : size;
    }
}
