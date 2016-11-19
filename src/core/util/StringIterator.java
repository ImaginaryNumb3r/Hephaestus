package core.util;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public class StringIterator implements Iterator<Character> { // TODO: Make ListIterator
    private final CharSequence _sequence;
    private int pos = 0;

    public StringIterator(CharSequence sequence) {
        _sequence = sequence;
    }

    public boolean hasNext() {
        return pos < _sequence.length();
    }

    public Character next() {
        return _sequence.charAt(pos++);
    }
}
