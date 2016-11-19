package core.tuple;

import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class Tuple<A, B> extends Unit<A>{
    protected B B;

    public Tuple(Tuple<A, B> tuple){
        this(tuple.getA(), tuple.getB());
    }

    public Tuple(@Nullable A a, @Nullable B b) {
        super(a);
        B = b;
    }

    public B getB() {
        return B;
    }

    public void setB(B b) {
        B = b;
    }

    @Override
    protected Supplier<List<Object>> makeArray(){
        return () -> Arrays.asList(A, B);
    }

    /*@Override
    protected Tuple<A, B> convert(Object object) {
        return new Generics<Tuple<A, B>>().cast(object);
    }*/
}