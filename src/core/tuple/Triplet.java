package core.tuple;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.util.collections.GenericListIterator;
import core.util.contracts.Contract;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public interface Triplet<A, B, C> extends Tuple<A, B> {

    static <A, B, C> Triplet from (@NotNull Triplet<A, B, C> triplet){
        Contract.checkNull(triplet, "triplet");
        return new TripletImpl<>(triplet.getA(), triplet.getB(), triplet.getC());
    }

    static <A, B, C> Triplet from (@NotNull Tuple<A, B> tuple, @Nullable C c){
        Contract.checkNull(tuple, "tuple");
        return new TripletImpl<>(tuple.getA(), tuple.getB(), c);
    }

    static <A, B, C> Triplet from (@NotNull Unit<A> unit, @Nullable B b , @Nullable C c){
        Contract.checkNull(unit, "unit");
        return new TripletImpl<>(unit.getA(), b, c);
    }

    static <A, B, C> Triplet<A, B, C> from (@Nullable A a, @Nullable B b, @Nullable C c) {
        return new TripletImpl<>(a, b, c);
    }

    C getC();

    @Override
    default ListIterator<Object> listIterator(){
        return GenericListIterator.from(getA(), getB(), getC());
    }
}
