package infastructure.traverser;

import infastructure.datastructure.traverser.DirectoryTraverser;
import infastructure.datastructure.traverser.NodeIterator;
import infastructure.filetype.HDirectory;
import infastructure.filetype.HFile;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Stack;

/**
 * @author Patrick
 * @created 29.05.2016
 */

// TODO: Probleme mit concurrancy. Was wenn sich das Directory ändert während man arbeitet?
// -> Unterschied zwischen einem rein lesendem traverser und einem rein speicherndem Baum. Traverser aktualisiert Baum.
public class CnCDirectoryTraverser extends DirectoryTraverser<HDirectory> {
    protected HDirectory _curDir;
    protected HDirectory _destDir;
    protected Stack<NodeIterator<HDirectory>> _prevIter;
    protected NodeIterator<HDirectory> _curIter;

    // ======================
    //      Constructors
    // ======================

    public CnCDirectoryTraverser(HDirectory root) {
        super(root);
        _curDir = root;
        _curIter = _curDir.iterator();
        _prevIter = new Stack<>();
    }

    // ======================
    //   Interface Methods
    // ======================

    @Override
    public void enter() {
        enter(_destDir.iterator());
    }

    @Override
    public boolean addFile(HFile file) {
        throw new NotImplementedException();
        // return _destDir.addDirectory(file);
    }

    @Override
    public boolean addDirectory(HDirectory directory) {
        throw new NotImplementedException();
        // return _destDir.addDirectory(directory);
    }

}