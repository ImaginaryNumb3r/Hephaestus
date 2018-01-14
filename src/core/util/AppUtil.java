/*
 * Copyright (c) 2016 Team 7, ITB5_2
 */
package core.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Matthias Fussenegger
 */
// TODO: Consider removal or incorporate it into a different class.
public class AppUtil {

    /**
     * Returns the decoded root file of the application's JAR-file.
     *
     * @param clazz The class of which to receive the root file.
     * @return The decoded root file of the JAR-file.
     * @throws UnsupportedEncodingException
     */
    public static String getDecodedRootPath(Class<?> clazz) throws UnsupportedEncodingException {
        String decPath; //to hold decoded file of JAR-file
        String path = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();

        final File jarFile = new File(path);
        final int cutLength = determineCutLength(jarFile);


        if (System.getProperty("os.name").startsWith("Windows")) {
            decPath = URLDecoder.decode(path.substring(
                    1, path.length() - cutLength), "UTF-8");
        } else {
            decPath = URLDecoder.decode(path.substring(
                    0, path.length() - cutLength), "UTF-8");
        }

        return decPath;
    }

    /**
     * Determines the cut length to output the application directory.
     *
     * @param f The file to output cut length for.
     * @return The cut length, that is the name of the executable or the folder.
     */
    private static int determineCutLength(File f) {

        int cutLength = 0;

        if (!f.isDirectory()) {
            cutLength = f.getName().length();
        }

        return cutLength;
    }
}
