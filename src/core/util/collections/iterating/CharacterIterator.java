package core.util.collections.iterating;

import com.sun.istack.internal.NotNull;
import core.util.contracts.Contract;
import core.util.interfaces.Accessible;

import java.util.Iterator;

/**
 * @author Patrick
 * @since 19.11.2016
 */
@SuppressWarnings("WeakerAccess")
public class CharacterIterator extends GenericIterator<Character> { // TODO: Make ListIterator

    protected CharacterIterator(@NotNull Accessible<Character> collection, int length) {
        super(collection, length);
    }

    public CharacterIterator(@NotNull CharSequence sequence) {
        super(sequence::charAt, sequence.length());
    }

    public CharacterIterator(@NotNull char[] chars) {
        super(i -> chars[i], chars.length);
    }


    public CharacterIterator from(@NotNull CharSequence sequence){
        Contract.checkNull(sequence);
        return new CharacterIterator(sequence);
    }

    public Iterator<Character> from(@NotNull char[] chars){
        Contract.checkNull((Object) chars);
        return new CharacterIterator(chars);
    }
}
