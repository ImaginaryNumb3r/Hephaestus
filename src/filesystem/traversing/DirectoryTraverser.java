package filesystem.traversing;

import core.util.contracts.Contract;
import infastructure.filetype.HDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * @author Patrick
 * @since 28.05.2017
 */
public class DirectoryTraverser implements Iterator<HDirectory> {
    private Stack<HDirectory> _remaining;

    public DirectoryTraverser(HDirectory root) {
        _remaining = new Stack<>();
        _remaining.add(root);
    }

    public static DirectoryTraverser from(AbsoluteDirectory root){
        return from(new HDirectory(root));
    }

    public static DirectoryTraverser from(HDirectory root){
        Contract.checkNull(root);
        return new DirectoryTraverser(new HDirectory(root));
    }

    @Override
    public boolean hasNext() {
        return !_remaining.isEmpty();
    }

    @Override
    public HDirectory next() {
        HDirectory directory = _remaining.pop();
        Optional<List<HDirectory>> optionalDirectories = directory.getDirectories();
        optionalDirectories.ifPresent(_remaining::addAll);

        return directory;
    }
}
