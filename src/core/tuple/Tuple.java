package core.tuple;

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

    B getB();

    void setB(B b);

    @Override
    default ListIterator<Object> iterator(){
        return listIterator();
    }

    @Override
    default ListIterator<Object> listIterator(){
        return GenericListIterator.from(getA(), getB());
     }
}