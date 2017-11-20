package infastructure.path.interfaces;

import org.jetbrains.annotations.Nullable;
import functional.QuintSupplier;
import infastructure.filetype.interfaces.Path;
import infastructure.path.DirectoryNode;
import infastructure.path.FileNode;

/**
 * @author Patrick
 * @since 08.07.2016
 */
@FunctionalInterface
public interface PathSupplier<T extends Path> extends QuintSupplier<T, DirectoryNode, DirectoryNode, FileNode, Integer> {

    T get(DirectoryNode head, DirectoryNode tail, @Nullable FileNode file, Integer length);

}
