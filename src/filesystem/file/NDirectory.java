package filesystem.file;

import com.sun.istack.internal.NotNull;
import core.util.contracts.Contract;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;

import java.nio.file.Files;

/**
 * @author Patrick
 * @description
 * @since 14.06.2017
 */
public class NDirectory {
    private AbsoluteDirectory _path;

    protected NDirectory(AbsoluteDirectory path) {
        _path = path;
    }

    public NDirectory from(@NotNull AbsoluteDirectory absDir){
        Contract.checkNull(absDir, "absDir");
        return new NDirectory(absDir);
    }

    public void listFiles(){
        Files.list(_path);
    }



}
