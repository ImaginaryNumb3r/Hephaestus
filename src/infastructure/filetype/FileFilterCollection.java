package infastructure.filetype;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection of file filters. A checked entry must pass checks of all filters.
 * @author Patrick
 * @created 22.07.2016
 */
public class FileFilterCollection implements FileFilter {
    private List<FileFilter> _filters;

    // ===================
    //    Constructors
    // ===================

    public FileFilterCollection(FileFilterCollection base){
        _filters = new LinkedList<>();
        _filters.addAll(base._filters);
    }

    public FileFilterCollection() {
        _filters = new LinkedList<>();
    }

    public FileFilterCollection(List<FileFilter> filters) {
        this(); // Create list
        _filters.addAll(filters);
    }

    public FileFilterCollection(FileFilter filter) {
        this(); // Create list
        _filters.add(filter);
    }

    // ==============
    //    Methods
    // ==============

    /**
     * Tests whether or not the specified abstract pathname should be
     * included in a pathname list.
     *
     * @param  pathname  The abstract pathname to be tested
     * @return  <code>true</code> if and only if <code>pathname</code>
     *          should be included
     */
    public boolean accept(File pathname){
        for (FileFilter filter : _filters) {
            if (!filter.accept(pathname)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds another filter to the list of filters
     * @param filter filter
     */
    public void addFilter(FileFilter filter){
        _filters.add(filter);
    }

    @Override
    public String toString() {
        return "Size: " + _filters.size();
    }
}
