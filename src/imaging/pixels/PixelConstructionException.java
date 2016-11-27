package imaging.pixels;

/**
 * @author Patrick
 * @since 12.11.2016
 */
public class PixelConstructionException extends RuntimeException{
    public PixelConstructionException() {
    }

    public PixelConstructionException(String message) {
        super(message);
    }

    public PixelConstructionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PixelConstructionException(Throwable cause) {
        super(cause);
    }

    public PixelConstructionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
