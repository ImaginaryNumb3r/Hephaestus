package core.tuple;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.datastructure.Lazy;
import core.util.collections.GenericIterator;
import core.util.collections.GenericListIterator;
import core.util.contracts.Contract;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public interface Unit<A> extends Pattern {

    static <A> Unit<A> from(@NotNull Unit<A> unit){
        Contract.checkNull(unit, "unit");
        return new UnitImpl<>(unit);
    }

    static <A> Unit<A> from(@Nullable A a){
        return new UnitImpl<>(a);
    }

    A getA();

    // void setA(A a);

    @Override
    default ListIterator<Object> iterator(){
        return listIterator();
    }

    @Override
    default ListIterator<Object> listIterator(){
        // TODO: Create custom ListIterator and return that
        return GenericListIterator.from(getA());
    }
}
