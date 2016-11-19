package core.tuple;

import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class Quartet<A, B, C, D> extends Triplet<A, B, C> {
    protected D D;

    public Quartet(Quartet<A, B, C, D> quartet) {
        this(quartet.getA(), quartet.getB(), quartet.getC(), quartet.getD());
    }

    public Quartet(@Nullable A a, @Nullable B b, @Nullable C c, @Nullable D d) {
        super(a, b, c);
        D = d;
    }

    public D getD() {
        return D;
    }

    public void setD(D d) {
        D = d;
    }

    @Override
    protected Supplier<List<Object>> makeArray(){
        return () -> Arrays.asList(A, B, C, D);
    }

    /*@Override
    protected Quartet<A, B, C, D> convert(Object object) {
        return new Generics<Quartet<A, B, C, D>>().cast(object);
    }*/
}
