package infastructure.datastructure.map;

/**
 * Factory for creating a tolerant HashMap.
 * @author Patrick
 * @created 24.07.2016
 */
public class TolerantHashMapFactory<K, V>  {
    private static final int ABSOLUTE_EXTRA_DEFAULT = 5;
    private static final double PERCENT_EXTRA_DEFAULT = 1.75;
    // Constants
    private int _absoluteExtra;
    private double _percentExtra;

    public TolerantHashMapFactory() {
        this(ABSOLUTE_EXTRA_DEFAULT, PERCENT_EXTRA_DEFAULT);
    }

    public TolerantHashMapFactory(int absoluteExtra, double percentExtra) {
        _absoluteExtra = absoluteExtra;
        _percentExtra = percentExtra;
    }

    public TolerantHashMap<K, V> create(int size){
        return new TolerantHashMap<>(getCapacity(size), _absoluteExtra, _percentExtra);
    }

    private int getCapacity(int capacity){
        return (capacity * _percentExtra < capacity + _absoluteExtra)
                ? capacity + _absoluteExtra
                : (int) (capacity * _percentExtra);
    }
}
