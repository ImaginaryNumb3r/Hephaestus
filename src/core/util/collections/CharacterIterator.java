package core.util.collections;

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

    public CharacterIterator(@NotNull Accessible<Character> collection, int length) {
        super(collection, length);
    }

    public CharacterIterator from(@NotNull CharSequence sequence){
        Contract.checkNull(sequence);
        return new CharacterIterator(sequence::charAt, sequence.length());
    }

    public Iterator<Character> from(@NotNull char[] sequence){
        Contract.checkNull((Object) sequence);
        return new CharacterIterator(i -> sequence[i], sequence.length);
    }
}
