package core.util;

/**
 * @author Patrick
 * @since 16.11.2016
 */
public class Generics<T> {

    /**
     * Casts the object to the generic type or returns null if class cannot be castet
     * @param object that is to be casted
     * @return null if type of object does not match the generic, otherwise returns the object as type T
     */
    @SuppressWarnings("unchecked")
    // TODO: Does not totally work yet
    public T cast(Object object){
        T val;
        try{
            val = (T) object;

            Inner<T> tInner = new Inner<>(val);
        } catch (ClassCastException ex){
            val = null;
        }

        return val;
    }

    /**
     * Checks whether this object is an instance of this class
     * @param object that is to be tested by class
     * @return true if {@param object} is of the specified generic type
     */
    public boolean instanceOf(Object object){
        return cast(object) != null;
    }

    private class Inner<S extends T> {

        public S test;

        public Inner(S test) {
            this.test = test;
        }
    }

}
