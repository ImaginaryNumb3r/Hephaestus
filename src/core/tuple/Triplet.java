package core.tuple;

import com.sun.istack.internal.Nullable;
import core.util.Generics;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class Triplet<A, B, C> extends Tuple<A, B>{
    protected C C;

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
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        Triplet<A, B, C> triplet = new Generics<Triplet<A, B, C>>().cast(o);
        boolean equals = super.equals(triplet);
        equals &= Objects.equals(triplet.C, C);

        return equals;
    }

    @Override
    protected Supplier<Object[]> makeArray() {
        return () -> new Object[]{A, B, C};
    }
}
