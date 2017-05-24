package core.tuple;

import core.datastructure.Lazy;
import core.util.HashCode;
import core.util.interfaces.IterableList;
import util.hash.HashGenerator;

import java.io.Serializable;
import java.util.*;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
@SuppressWarnings("WeakerAccess")
class UnitImpl<A> implements Serializable, IterableList<Object>, Unit<A> {
    protected A A;
    protected final Lazy<List<Object>> _values;
    protected final Lazy<Integer> _hashCode;

    // ===============
    //  Constructors
    // ===============

    public UnitImpl(Unit<A> a){
        this(a.getA());
    }

    public UnitImpl(A a) {
        A = a;
        _values = Lazy.from(makeArray());
        _hashCode = Lazy.from(this::makeHash);
    }

    // ===============
    //   Properties
    // ===============

    public A getA() {
        return A;
    }

    public void setA(A a) {
        A = a;
    }

    // ===============
    //    Methods
    // ===============

    protected Supplier<List<Object>> makeArray(){
        return () -> Collections.singletonList(A);
    }

    /**
     * Part of the equals
     * @apiNote Must be overridden by all subclasses
     * @param obj the
     * @return
     */
    protected boolean equalClass(Object obj){
        return obj instanceof Unit;
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        return equalClass(obj) && HashCode.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return _hashCode.get();
    }

    public int makeHash() {
        return new HashGenerator(getClass())
                .append(_values.get())
                .toHashCode();
    }

    @Override
    public ListIterator<Object> iterator() {
        return _values.get().listIterator();
    }

    @Override
    public ListIterator<Object> listIterator() {
        return iterator();
    }
}
