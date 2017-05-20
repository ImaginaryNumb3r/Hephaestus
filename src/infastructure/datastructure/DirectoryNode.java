package infastructure.datastructure;
//
//import infastructure.datastructure.exceptions.IllegalNodeStateException;
//import infastructure.datastructure.traverser.NodeIterator;
//import infastructure.filetype.HDirectory;
//import infastructure.filetype.HEntry;
//import infastructure.filetype.HFile;
//import infastructure.filetype.interfaces.aubtypes.AbsolutePath;
//
//import java.util.InnerIterator;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @author Patrick
// * @created 29.05.2016
// * @purpose An element inside tree with functionality as a CnCDirectory. It may have sub-nodes and files but these entries have to be added manually
// */
//
//public class DirectoryNode extends HDirectory {
//    /** The representation/location of the node on the hard drive */
//    private HDirectory _file;
//    /** The child nodes which have been added after construction */
//    private List<DirectoryNode> _children;
//    /** The files in the current node which are to be added piece by piece when needed after construction */
//    private List<HFile> _values;
//
//    // ======================
//    //      Constructors
//    // ======================
//
//    public DirectoryNode(HDirectory directory) {
//        super(directory);
//        _file = directory;
//
//        // LazyImpl instantiation - or step by step construction
//        _children = new LinkedList<>();
//        _values = new LinkedList<>();
//    }
//
//    // ======================
//    //      Methods
//    // ======================
//
//    /**
//     * Initialize the node with help of its source directory.
//     * Method can only be performed if the node is completely empty and no value has been assigned yet.
//     * @throws IllegalNodeStateException if node has changed since construction.
//     */
//    public void initialize() throws IllegalNodeStateException{
//        if (_children.isEmpty() && _values.isEmpty()){
//            _file.getDirectories().stream().forEach((
//                    (HDirectory dir) -> _children.add(new DirectoryNode(dir))));
//
//            _file.getFiles().stream().forEach((
//                    (HFile file) -> _values.add(file)));
//        } else {
//            throw new IllegalNodeStateException();
//        }
//    }
//
//    /**
//     * Returns the CnCFiles that have been added to this node
//     * @return Values that have been added so far to the node
//     */
//    public List<HFile> getValues() {
//        return _values;
//    }
//
//
//    /**
//     * Fast addDirectory without checking if it already exists
//     * @param file - it is your responsibility to ensure that it is unique
//     */
//    public void addRaw(HFile file){
//        _values.add(file);
//    }
//
//    /**
//     * Save addDirectory - always checks if entry exists before it is being added
//     * @param file unique file that is preferably not already present
//     * @return true if file was added
//     */
//    public boolean add(HFile file){
//        boolean added;
//
//        if (!_values.contains(file)){
//            _values.add(file);
//            added = true;
//        } else {
//            added = false;
//        }
//
//        return added;
//    }
//
//    /**
//     * Fast addDirectory without checking if it already exists
//     * @param directory is your responsibility to ensure that it is unique
//     */
//    public void addRaw(DirectoryNode directory){
//        _children.add(directory);
//    }
//
//    /**
//     * Save addDirectory - always checks if entry exists before it is being added
//     * @param directory unique directory that is preferably not already present
//     * @return true if directory was added
//     */
//    public boolean add(DirectoryNode directory){
//        boolean added;
//
//        if (!_children.contains(directory)){
//            _children.add(directory);
//            added = true;
//        } else {
//            added = false;
//        }
//
//        return added;
//    }
//
//    /**
//     * Returns representation/location of this Node on the filesystem
//     * @return CnCDirectory - Source Directory where this node is located
//     */
//    public HDirectory getDirectory() {
//        return _file;
//    }
//
//    @Override
//    public String getAbsolutePath() {
//        return _file.getAbsolutePath();
//    }
//
//    public DirectoryNode containsDirectory(HDirectory file) {
//        return contains(_children.iterator(), file);
//    }
//
//    public <T extends AbsolutePath> T contains(InnerIterator<T> iter, HEntry file) {
//        boolean contains = false;
//        T cur = null;
//        while (iter.hasNextChild() && !contains){
//            cur = iter.next();
//
//            if (file.equals(cur)){
//                contains = true;
//            }
//        }
//
//
//        return cur;
//    }
//
//    public HEntry containsFile(HFile file) {
//        return contains(_values.iterator(), file);
//    }
//
//    /**
//     * Gets all child nodes that have been added since construction
//     * @return List of nodes inside this directory, representing sub directories
//     */
//    public List<DirectoryNode> getChildren() {
//        return _children;
//    }
//
//    /**
//     *
//     * @param dir
//     * @return null if child does not exist
//     */
//    public DirectoryNode getChild(HDirectory dir){
//        boolean found = false;
//        DirectoryNode cur = null;
//
//        for (InnerIterator<DirectoryNode> iter = _children.iterator(); iter.hasNextChild() && !found; ){
//            cur = iter.next();
//
//            if (cur.equals(dir)){
//                found = true;
//            }
//
//        }
//
//        return cur;
//    }
//
//    public String toString() {
//        String toString = "[ Root : " + _file.toString() + " , ";
//        toString += "Size: " + _children.size() + " , ";
//
//        for (NodeIterator<DirectoryNode> iter = nodeIterator(); iter.hasNextChild();) {
//            // addDirectory each item
//            toString += iter.next().toString();
//
//            if (iter.hasNextChild()){
//                toString += ", ";
//            }
//        }
//
//        toString += " ]";
//
//        return toString;
//    }
//
//    public NodeIterator<DirectoryNode> nodeIterator(){
//        return new DirectoryNodeIterator(_children);
//    }
//
//
//    /******************
//     * Node InnerIterator
//     /*****************/
//    protected class DirectoryNodeIterator extends NodeIterator<DirectoryNode> {
//
//        public DirectoryNodeIterator(final List<DirectoryNode> init) {
//            super(init);
//        }
//    }
//}