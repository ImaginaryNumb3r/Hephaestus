package core.tuple;

import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class TripletImpl<A, B, C> extends TupleImpl<A, B> implements Triplet<A, B, C> {
    protected C C;

    public TripletImpl(TripletImpl<A, B, C> triplet){
        this(triplet.getA(), triplet.getB(), triplet.getC());
    }

    public TripletImpl(@Nullable A a, @Nullable B b, @Nullable C c) {
        super(a, b);
        C = c;
    }

    public C getC() {
        return C;
    }

    public void setC(C c) {
        C = c;
    }

    @Override
    protected Supplier<List<Object>> makeArray(){
        return () -> Arrays.asList(A, B, C);
    }
}
