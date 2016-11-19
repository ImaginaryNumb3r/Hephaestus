package core.tuple;

import core.datastructure.Lazy;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class Unit<A> implements Pattern{
    protected A A;
    protected final Lazy<List<Object>> _values;

    // ===============
    //  Constructors
    // ===============

    public Unit(Unit<A> a){
        this(a.getA());
    }

    public Unit(A a) {
        A = a;
        _values = new Lazy<>(makeArray());
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

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object obj) {
        boolean equals;

        try { @SuppressWarnings("unchecked")
            Unit<A> unit = (Unit<A>) obj;
            equals = unit != null && equals(_values.get(), unit._values.get());
        } catch (ClassCastException ex){
            equals = false;
        }

        return equals;
    }

    protected boolean equals(List<Object> thisValues, List<Object> otherValues){
        boolean equals = false;

        if (otherValues.size() == thisValues.size()){
            HashSet<Object> valueSets = new HashSet<>(otherValues);
            equals = thisValues
                    .stream()
                    .allMatch(valueSets::contains);
        }

        return equals;
    }


    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Object o : this) {
            hashCode = 31 * hashCode + (o != null ? o.hashCode() : 0);
        }
        return hashCode;
    }

    @Override
    public ListIterator<Object> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<Object> listIterator() {
        return _values.get().listIterator();
    }
}