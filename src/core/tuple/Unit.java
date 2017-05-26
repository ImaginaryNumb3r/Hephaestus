package core.tuple;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.util.collections.iterating.GenericListIterator;
import core.util.contracts.Contract;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public interface Unit<A> extends Structure {

    static <A> Unit<A> from(@NotNull Unit<A> unit){
        Contract.checkNull(unit, "unit");
        return new UnitImpl<>(unit);
    }

    static <A> Unit<A> from(@Nullable A a){
        return new UnitImpl<>(a);
    }

    /**
     * @apiNote When inheriting Tuple, make this a default method that is deprecated.
     * This method should only be used when referred to as a Tuple.
     * Do not call manually to improve code readability.
     */
    A getA();

    @Override
    default ListIterator<Object> listIterator(){
        // TODO: Create custom ListIterator and return that
        return GenericListIterator.from(getA());
    }
}
