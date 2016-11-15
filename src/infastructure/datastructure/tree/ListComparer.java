package infastructure.datastructure.tree;

import infastructure.datastructure.map.TolerantHashMap;
import infastructure.datastructure.map.TolerantHashMapFactory;
import infastructure.datastructure.tree.container.ListCompareResult;

import java.util.Collection;

/**
 * @author Patrick
 * @created 27.07.2016
 */
public class ListComparer<T> {

    /**
     * Reloads the map, but only if necessary or if modified to a great extend.
     */
    private TolerantHashMap<T, T> createMap(Collection<T> oldEntries){
        TolerantHashMap<T, T> fileMap = new TolerantHashMapFactory<T, T>().create(oldEntries.size());

        for (T name : oldEntries) {
            fileMap.put(name, name);
        }

        return fileMap;
    }

    /**
     * @param oldEntries The base entries that existed in the first place
     * @param newEntries The new collection of entries that is being compared
     *
     * This algorithm compares 2 directories and each call to compare will delete the current lists of elements after finishing.
     * The algorithm works more efficient if the inner HashMap is as small as possible.
     *
     * Explanation: By comparing the 2 lists of filenames, the lists are first allocated in a HashMap for direct access when comparing.
     * Step by step the elements from the HashMap are being removed, until only the elements are left over which are not present in the new list.
     * To grab the remaining (read deleted) files in the map, an iteration through the complete HashMap has to be performed.
     * This results in an additional access time of O(n) on top.
     *
     * @return ListCompareResult for the result
     */
    public ListCompareResult<T> compare(Collection<T> newEntries, Collection<T> oldEntries){
        ListCompareResult<T> listCompareResult = new ListCompareResult<>();
        // Create Map: 1 x  O(n)
        TolerantHashMap<T, T> fileMap = createMap(oldEntries);

        // Read Map: 1 x O(n)
        for (T file : newEntries) {
            boolean contains = fileMap.remove(file) != null;

            if (contains) {
                listCompareResult.addUnchangedFiles(file);
            } else {
                listCompareResult.addNewFiles(file);
            }
        }

        // Catch Remaining Values: 1 x O(n)
        for (T fileName : fileMap.values()) {
            listCompareResult.addRemovedFiles(fileName);
        }

        // Reset Map
        return listCompareResult;
    }
}
