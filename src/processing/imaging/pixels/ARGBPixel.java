package processing.imaging.pixels;

import com.sun.istack.internal.NotNull;
import core.tuple.Quartet;
import util.hash.HashGenerator;

/**
 * @author Patrick
 * @since 12.11.2016
 */
public class ARGBPixel extends RGBPixel{
    protected int _alpha;

    public ARGBPixel(@NotNull  Quartet<Integer, Integer, Integer, Integer> quartet) {
        this(quartet.getA(), quartet.getB(), quartet.getC(), quartet.getD());
    }

    public ARGBPixel(int red, int green, int blue, int alpha) {
        super(red, green, blue);
        _alpha = alpha;
    }

    //<editor-fold desc="">
    public int getAlpha() {
        return _alpha;
    }

    public void setAlpha(int alpha) {
        _alpha = alpha;
    }

    @Override
    public int[] toArray() {
        return new int[]{_red, _green, _blue, _alpha};
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .append(_red, _green, _blue, _alpha)
                .toHashCode();
    }
}
