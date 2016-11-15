package infastructure.datastructure.map;

import java.util.HashMap;

/**
 * A HashMap with a tolerance in capacity
 * This HashMap is to be allocated with higher capacity than usual to allow minimal growth in size
 * Moreover, this HashMap implementation knows when its optimal capacity is surpassed
 * @author Patrick
 * @created 24.07.2016
 */
public class TolerantHashMap<K, V> extends HashMap<K, V> {
    private int _absoluteExtra;
    private double _percentExtra;
    private int _elements;
    private int _totalCapacity;

    // ================
    //   Constructors
    // ================

    /**
     * Should NEVER be called directly
     *
     * @param  initialCapacity  the initial capacity
     * @param  loadFactor       the load factor
     * @param  absoluteExtra    The minimum extra capacity
     * @param  percentExtra     The extra capacity in percent
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is non-positive
     */
    public TolerantHashMap(int initialCapacity, float loadFactor, int absoluteExtra, double percentExtra) {
        super(initialCapacity, loadFactor);
        initialize(initialCapacity, absoluteExtra, percentExtra);
    }


    /**
     * Should NEVER be called directly
     *
     * @param  initialCapacity  the initial capacity
     * @param  absoluteExtra    The minimum extra capacity
     * @param  percentExtra     The extra capacity in percent
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is non-positive
     */
    public TolerantHashMap(int initialCapacity, int absoluteExtra, double percentExtra) {
        super(initialCapacity);
        initialize(initialCapacity, absoluteExtra, percentExtra);
    }

    private void initialize(int initialCapacity, int absoluteExtra, double percentExtra){
        _absoluteExtra = absoluteExtra;
        _percentExtra = percentExtra;
        _totalCapacity = initialCapacity;
        _elements = 0;
    }

    // ============
    //   Static
    // ============

    public  int getAbsoluteExtra() {
        return _absoluteExtra;
    }

    public  void setAbsoluteExtra(int absoluteExtra) {
        _absoluteExtra = absoluteExtra;
    }

    public  double getPercentExtra() {
        return _percentExtra;
    }

    public  void setPercentExtra(double percentExtra) {
        _percentExtra = percentExtra;
    }

    // ============
    //   Methods
    // ============

    /**
     * Returns the number of elements currently being stored
     * @return the number of elements currently being stored
     */
    public int elements(){
        return _elements;
    }


    @Override
    public V put(K key, V value) {
        ++_elements;
        return super.put(key, value);
    }

    /**
     * Checks whether this HashMap should be reloaded
     * @return true if capacity is surpassed
     */
    public boolean isCapacityReached(){
        return _elements >= _totalCapacity;
    }

    /**
     * Returns the total capacity.
     * In other words, it returns the initial capacity that was used passed to the constructor during creation.
     * @return the total capacity
     */
    public int totalCapacity(){
        return _totalCapacity;
    }
}