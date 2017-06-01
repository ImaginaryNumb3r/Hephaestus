package core.tuple;

import com.sun.istack.internal.Nullable;
import core.util.collections.iteration.GenericListIterator;
import core.util.contracts.Contract;

import java.util.ListIterator;

/**
 * @author Patrick
 * @since 16.11.2016
 *
 * A simple generic value pair
 */
public interface Tuple<A, B> extends Unit<A>{

    static <A, B> Tuple<A, B> from(Tuple<A, B> tuple){
        Contract.checkNull(tuple, "tuple");
        return new TupleImpl<>(tuple);
    }

    static <A, B> Tuple<A, B> from(Unit<A> unit, @Nullable B b){
        Contract.checkNull(unit, "unit");
        return new TupleImpl<>(unit.getA(), b);
    }

    static <A, B> Tuple<A, B> from(@Nullable A a, @Nullable B b){
        return new TupleImpl<>(a, b);
    }

    /**
     * @apiNote When inheriting Tuple, make this a default method that is deprecated.
     * This method should only be used when referred to as a Tuple.
     * Do not call manually to improve code readability.
     */
    B getB();

    @Override
    default ListIterator<Object> listIterator(){
        return GenericListIterator.from(getA(), getB());
     }
}