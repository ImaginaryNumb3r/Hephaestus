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
public interface Quartet<A, B, C, D> extends Triplet<A, B, C> {

    static <A, B, C, D> Quartet from(@NotNull Quartet<A, B, C, D> quartet) {
        Contract.checkNull(quartet);
        return new QuartetImpl<>(quartet.getA(), quartet.getB(), quartet.getC(), quartet.getD());
    }

    static <A, B, C, D> Quartet from(@NotNull Triplet<A, B, C> triplet, @Nullable D d) {
        Contract.checkNull(triplet, "triplet");
        return new QuartetImpl<>(triplet.getA(), triplet.getB(), triplet.getC(), d);
    }

    static <A, B, C, D> Quartet from(@NotNull Tuple<A, B> tuple, @Nullable C c, @Nullable D d) {
        Contract.checkNull(tuple, "tuple");
        return new QuartetImpl<>(tuple.getA(), tuple.getB(), c, d);
    }

    static <A, B, C, D> Quartet from(@NotNull Unit<A> unit, @Nullable B b, @Nullable C c, @Nullable D d) {
        Contract.checkNull(unit, "unit");
        return new QuartetImpl<>(unit.getA(), b, c, d);
    }

    static <A, B, C, D> Quartet from(@Nullable A a, @Nullable B b, @Nullable C c, @Nullable D d) {
        return new QuartetImpl<>(a, b, c, d);
    }

    D getD();

    void setD(D d);

    @Override
    default ListIterator<Object> iterator(){
        return listIterator();
    }

    @Override
    default ListIterator<Object> listIterator(){
        return GenericListIterator.from(getA(), getB(), getC(), getD());
    }
}
