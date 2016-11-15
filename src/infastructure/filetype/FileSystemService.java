package infastructure.filetype;

import infastructure.filetype.interfaces.aubtypes.AbsolutePath;

/**
 * @author Patrick
 * @created 03.08.2016
 */
public class FileSystemService {




    // Singleton Pattern

    static FileSystemService getInstance(){
        return InstanceHoler.INSTANCE;
    }

    private static class InstanceHoler{
        private static final FileSystemService INSTANCE = new FileSystemService();
    }
}
