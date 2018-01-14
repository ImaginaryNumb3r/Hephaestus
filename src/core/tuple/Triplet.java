package core.tuple;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import core.util.collections.iteration.GenericListIterator;
import core.util.collections.iteration.Iterators;
import core.util.contracts.Contract;

import java.util.ListIterator;

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

    /**
     * @apiNote When inheriting Tuple, make this a default method that is deprecated.
     * This method should only be used when referred to as a Tuple.
     * Do not call manually to improve code readability.
     */
    C getC();

    @NotNull
    @Override
    default ListIterator<Object> listIterator(){
        return Iterators.from(getA(), getB(), getC());
    }
}
