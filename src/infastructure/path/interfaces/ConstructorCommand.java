package infastructure.path.interfaces;

import infastructure.filetype.interfaces.Path;
import infastructure.path.DirectoryNode;
import infastructure.path.FileNode;

/**
 * @author Patrick
 * @created 08.07.2016
 */
@FunctionalInterface
public interface ConstructorCommand<T extends Path> {

    T execute(DirectoryNode head, DirectoryNode tail, FileNode file, int length);

}
