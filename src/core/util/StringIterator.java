package core.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public class StringIterator implements Iterator<Character> { // TODO: Make ListIterator
    private final CharSequence _sequence;
    private int _pos = 0;

    public StringIterator(CharSequence sequence) {
        _sequence = sequence;
    }

    @Override
    public boolean hasNext() {
        return _pos < _sequence.length();
    }

    @Override
    public Character next() {
        if (!hasNext()) throw new NoSuchElementException();
        return _sequence.charAt(_pos++);
    }
}
