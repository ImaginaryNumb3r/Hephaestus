package stream.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

/**
 * @author Patrick
 * @since 19.11.2016
 */
public class CharStream extends Streams<Character>{

    public CharStream(CharSequence sequence) {
        super(getSpliterator(sequence));
    }

    public CharStream(Character[] array) {
        super(array);
    }

    public CharStream(List<Character> list) {
        super(list);
    }

    public CharStream(Spliterator<Character> spliterator) {
        super(spliterator);
    }

    /**
     * Requires low level implementation
     * @param sequence
     * @return
     */
    protected static Spliterator<Character> getSpliterator(CharSequence sequence){
        Character[] array = new Character[sequence.length()];
        for (int i = 0; i != sequence.length(); ++i){
            array[i] = sequence.charAt(i);
        }

        return Arrays.spliterator(array);
    }
}