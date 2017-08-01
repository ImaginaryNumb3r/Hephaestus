package infastructure.filetype;

import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;

/**
 * @author Patrick
 * @description
 * @since 13.06.2017
 */
public class LockedFile extends HFile {

    public LockedFile(AbsoluteDirectory location, String fileName) {
        super(location, fileName);
    }

    public LockedFile(AbsoluteFile location) {
        super(location);
    }





}
