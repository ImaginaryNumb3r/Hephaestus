package infastructure.filetype;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;

import java.time.LocalDateTime;

/**
 * @author Patrick
 * @since 05.06.2017
 *
 *
 */
public interface FileTimeStamp {

    long lastModified();

    long contentSize();

    String name();

    AbsolutePath path();

    LocalDateTime measureTime();

}
