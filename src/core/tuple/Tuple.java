package core.tuple;

import com.sun.istack.internal.Nullable;
import core.datastructure.Lazy;
import core.util.Generics;
import core.util.interfaces.ListIterable;

import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.StreamSupport;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class Tuple<A, B> implements Serializable, ListIterable<Object> {
    protected A A;
    protected B B;
    protected Lazy<Object[]> _values;

    public Tuple(@Nullable A a, @Nullable B b) {
        A = a;
        B = b;
        _values = new Lazy<>(makeArray());
    }

    //<editor-fold desc="Properties">
    public A getA() {
        return A;
    }

    public void setA(A a) {
        A = a;
    }

    public B getB() {
        return B;
    }

    public void setB(B b) {
        B = b;
    }
    //</editor-fold>

    //<editor-fold desc="Methods">
    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        Tuple<A, B> tuple = new Generics<Tuple<A, B>>().cast(o);
        return tuple != null && equals(tuple);
    }

    public boolean equals(Tuple<A, B> tuple){
        boolean equals = false;

        if (tuple != null){
            HashSet<Object> valueSets = new HashSet<>(Arrays.asList(_values.get()));
            equals = Arrays.asList(tuple._values.get())
                    .stream()
                    .allMatch(valueSets::contains);
        }

        return equals;
    }

    protected Supplier<Object[]> makeArray(){
        return () -> new Object[]{A, B};
    }


    @Override
    public int hashCode() {
        int result = A != null ? A.hashCode() : 0;
        result = 31 * result + (B != null ? B.hashCode() : 0);
        return result;
    }

    @Override
    public ListIterator<Object> iterator() {
        return Arrays.asList(_values.get()).listIterator();
    }
    //</editor-fold>
}