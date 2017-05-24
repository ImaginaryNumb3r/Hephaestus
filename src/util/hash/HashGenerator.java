package util.hash;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;

import static core.util.HashCode.DEFAULT_HASHCODE_BASE;
import static core.util.HashCode.DEFAULT_MULTIPLIER_BASE;

/**
 * @author Patrick
 * @since 24.05.2017
 */
public class HashGenerator {
    //<editor-fold desc="Attributes">
    private static final HashMap<Class, Integer> CLASS_MAP = new HashMap<>(100);
    private static int _classCount = 0;
    private int _multBase;
    private int _hashCode;
    //</editor-fold>

    public HashGenerator(@NotNull Class<?> type){
        this(CLASS_MAP.computeIfAbsent(type, key -> _classCount++));
    }

    public HashGenerator(int offset){
        _multBase = DEFAULT_MULTIPLIER_BASE + offset * 2;
        _hashCode = DEFAULT_HASHCODE_BASE + _multBase;
    }

    //<editor-fold desc="Boolean">
    public HashGenerator append(boolean bool){
        int value = bool ? 1 : 0;
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator append(boolean... bools){
        for (int i = 0; i != bools.length; ++i){
            int value = bools[i] ? 1 : 0;
            _hashCode += value * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Chars">
    public HashGenerator append(char value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator append(char... chars){
        for (int i = 0; i != chars.length; ++i){
            _hashCode += chars[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Int">
    public HashGenerator append(int value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator append(int... ints){
        for (int i = 0; i != ints.length; ++i){
            _hashCode += ints[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Long">
    public HashGenerator append(long value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator append(long... longs){
        for (int i = 0; i != longs.length; ++i){
            _hashCode += longs[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Float">
    public HashGenerator append(float value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator append(float... floats){
        for (int i = 0; i != floats.length; ++i){
            _hashCode += floats[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Long">
    public HashGenerator append(double value){
        _hashCode += value * _multBase;
        return this;
    }

    public HashGenerator append(double... doubles){
        for (int i = 0; i != doubles.length; ++i){
            _hashCode += doubles[i] * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Object">
    public HashGenerator append(Object obj){
        _hashCode += obj.hashCode() * _multBase;
        return this;
    }

    public HashGenerator append( Object... objs){
        for (int i = 0; i != objs.length; ++i){
            _hashCode += objs[i].hashCode() * _multBase;
        }
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Object">
    public <T> HashGenerator append(Iterable<T> iterable){
        for (T item : iterable) {
            _hashCode += item.hashCode() * _multBase;
        }
        return this;
    }
    //</editor-fold>


    public int toHashCode(){
        return _hashCode;
    }

}
