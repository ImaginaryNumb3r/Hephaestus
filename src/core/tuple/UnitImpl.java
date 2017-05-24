package core.tuple;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.datastructure.Lazy;
import core.util.HashCode;
import core.util.interfaces.IterableList;

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
        return equalClass(obj) && HashCode.equsls(this, obj); /*
        boolean equals;

        try { @SuppressWarnings("unchecked")
        UnitImpl<A> unit = (UnitImpl<A>) obj;
            equals = unit != null && equals(_values.get(), unit._values.get());
        } catch (ClassCastException ex){
            equals = false;
        }

        return equals; */
    }

    /*
    protected boolean equals(@NotNull Collection<Object> thisValues, @Nullable Collection<Object> otherValues){
        boolean equals = false;

        if (otherValues != null && thisValues.size() == otherValues.size()){
            Iterator<Object> thisIter = thisValues.iterator();
            Iterator<Object> otherIter = otherValues.iterator();

            equals = true;
            while (equals && thisIter.hasNext()){
                equals = thisIter.next().equals(otherIter.next());
            }
        }

        return equals;
    } */

    @Override
    public int hashCode() {
        return _hashCode.get();
    }

    public int makeHash() {
        int hashCode = 0;
        for (Object o : this) {
            hashCode = 31 * hashCode + (o != null ? o.hashCode() : 0);
        }
        return hashCode;
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
