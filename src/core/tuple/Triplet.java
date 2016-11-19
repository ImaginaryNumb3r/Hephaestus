package core.tuple;

import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class Triplet<A, B, C> extends Tuple<A, B>{
    protected C C;

    public Triplet(Triplet<A, B, C> triplet){
        this(triplet.getA(), triplet.getB(), triplet.getC());
    }

    public Triplet(@Nullable A a, @Nullable B b, @Nullable C c) {
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

    /*@Override
    protected Triplet<A, B, C> convert(Object object) {
        return new Generics<Triplet<A, B, C>>().cast(object);
    }*/
}
