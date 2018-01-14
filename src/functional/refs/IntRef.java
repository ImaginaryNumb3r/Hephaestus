package functional.refs;

import util.hash.HashGenerator;

/**
 * @author Patrick
 * @since 27.05.2017
 */
// TODO: Consider removal or make experimental. Not really a good practice. Or is it?
public class IntRef {
    private int _value;

    public IntRef(int value) {
        _value = value;
    }

    public IntRef(){
        _value = 0;
    }

    /**
     * Increments the value by 1
     */
    public void inc(){
        ++_value;
    }

    public void add(int value){
        _value += value;
    }


    public int getValue(){
        return _value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntRef && equals(((IntRef) obj)._value);
    }

    public boolean equals(int value) {
        return _value == value;
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .append(_value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return Integer.toString(_value);
    }
}
