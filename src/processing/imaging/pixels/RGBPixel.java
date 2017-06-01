package processing.imaging.pixels;

import core.tuple.Triplet;
import core.util.HashCode;
import util.hash.HashGenerator;

/**
 * Individual pixel for all
 *
 * @author Patrick
 * @since 12.11.2016
 */
@SuppressWarnings("WeakerAccess")
public class RGBPixel {
    protected int _red;
    protected int _green;
    protected int _blue;

    //<editor-fold desc="Constructors">
    public RGBPixel(Triplet<Integer, Integer, Integer> triplet){
        this(triplet.getA(), triplet.getB(), triplet.getC());
    }

    public RGBPixel(int[] pixel){
        if (pixel.length != 3) throw new PixelConstructionException();
        _red = pixel[0];
        _green = pixel[0];
        _blue = pixel[0];
    }

    public RGBPixel(int red, int green, int blue) {
        this(new int[] {red, green, blue});
    }
    //</editor-fold>

    //<editor-fold desc="Properties">
    public int getRed() {
        return _red;
    }

    public void setRed(int red) {
        _red = red;
    }

    public int getGreen() {
        return _green;
    }

    public void setGreen(int green) {
        _green = green;
    }

    public int getBlue() {
        return _blue;
    }

    public void setBlue(int blue) {
        _blue = blue;
    }
    //</editor-fold>

    public int[] toArray(){
        return new int[]{_red, _green, _blue};
    }

    /**
     * Returns the brightness of the pixel
     * @return brightness of the pixel
     * From: http://stackoverflow.com/questions/596216/formula-to-determine-brightness-of-rgb-color
     * TODO: Could be put into an own ColorConverter class
     */
    public int brightness(){
        return (int) (0.2126 * _red + 0.7152 * _green + 0.0722 * _blue);
    }

    @Override
    public String toString() {
        return "R: " + _red + ", G: " + _green + ", B: " + _blue;
    }

    @Override
    public boolean equals(Object obj) {
        return HashCode.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return new HashGenerator(getClass())
                .appendAll(_red, _green, _blue)
                .toHashCode();
    }
}