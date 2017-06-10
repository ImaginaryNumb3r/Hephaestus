package infastructure.filetype;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;

import java.time.LocalDateTime;

/**
 * @author Patrick
 * @since 05.06.2017
 *
 * This interface serves as a set of properties of a filesystem entry at the measuredTime.
 * This is necessary because the program has no control over the file system.
 * Therefore, the properties can change at any given moment and when working with the object,
 * the file might have been deleted or changed already. Therefore, this needs to be treated as momentary value object
 */
public interface FileTimeStamp {

    long lastModified();

    LocalDateTime lastModifiedDateTime();

    long contentSize();

    String name();

    AbsolutePath path();

    LocalDateTime measureTime();

}
