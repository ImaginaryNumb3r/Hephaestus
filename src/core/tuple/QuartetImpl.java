package core.tuple;

import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class QuartetImpl<A, B, C, D> extends TripletImpl<A, B, C> implements Quartet<A, B, C, D> {
    protected D D;

    public QuartetImpl(QuartetImpl<A, B, C, D> quartet) {
        this(quartet.getA(), quartet.getB(), quartet.getC(), quartet.getD());
    }

    public QuartetImpl(@Nullable A a, @Nullable B b, @Nullable C c, @Nullable D d) {
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
    protected QuartetImpl<A, B, C, D> convert(Object object) {
        return new Generics<QuartetImpl<A, B, C, D>>().cast(object);
    }*/
}