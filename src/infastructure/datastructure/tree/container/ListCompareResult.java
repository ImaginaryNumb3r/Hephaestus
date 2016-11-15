package infastructure.datastructure.tree.container;

import java.util.LinkedList;
import java.util.List;

/**
 * Container Class for files inside a directory.
 * Used for output container after a comparison has been performed.
 * @author Patrick
 * @created 27.07.2016
 */
public class ListCompareResult<T> {
    private final List<T> _unchangedFiles;
    private final List<T> _newFiles;
    private final List<T> _removedFiles;

    public ListCompareResult() {
        _unchangedFiles = new LinkedList<>();
        _newFiles = new LinkedList<>();
        _removedFiles = new LinkedList<>();
    }



    public List<T> getUnchangedFiles() {
        return _unchangedFiles;
    }

    public List<T> getNewFiles() {
        return _newFiles;
    }

    public List<T> getRemovedFiles() {
        return _removedFiles;
    }


    public void addUnchangedFiles(T file) {
         _unchangedFiles.add(file);
    }

    public void addNewFiles(T file) {
        _newFiles.add(file);
    }

    public void addRemovedFiles(T file) {
         _removedFiles.add(file);
    }
}
