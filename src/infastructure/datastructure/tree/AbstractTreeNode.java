package infastructure.datastructure.tree;

import infastructure.datastructure.tree.container.ListCompareResult;
import infastructure.filetype.HDirectory;
import infastructure.filetype.HFile;
import infastructure.filetype.interfaces.Path;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.AbsoluteFile;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeDirectory;
import infastructure.filetype.interfaces.aubtypes.subtypes.RelativeFile;
import infastructure.path.DirectoryNode;
import infastructure.path.PathFactory;
import infastructure.path.exceptions.PathsNotMatchingException;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Patrick
 * @created 30.07.2016
 */
public abstract class AbstractTreeNode implements TreeNode {
    // Node Handling
    final protected List<AbstractTreeNode> _childNodes;
    // Saved Values
    protected List<String> _files;
    protected List<String> _directories;
    // LazyImpl Instantiation
    protected HDirectory _directory;

    // ================
    //   Constructors
    // ================

    public AbstractTreeNode() {
        _directories = new LinkedList<>();
        _childNodes = new LinkedList<>();
        _files = new LinkedList<>();
    }

    // ===================
        //<editor-fold desc="Interface Methods">
    abstract AbstractTreeNode getParentNode();

    public abstract boolean hasParent();

    @Override
    public abstract String getName();

    /**
     *
     * @param dirName name of the child node
     * @return null if name was invalid
     */
    @Override
    // TODO: Idea: Replace with TolerantHashMap for better performance
    // TODO: Could return RelativeDirectoryTreeNode instead
    public final AbstractTreeNode getOrAdd(String dirName){
        AbstractTreeNode retVal = null;
        RelativeDirectory relDir = PathFactory.makeRelativeDirectory(dirName);

        if (relDir != null){
            retVal = getOrAdd(relDir);
        }

        return retVal;
    }

    public final AbstractTreeNode getOrAdd(RelativeDirectory dirName){
        AbstractTreeNode retVal;
        Optional<AbstractTreeNode> treeNode = _childNodes.stream().filter(item -> item.getName().equals(dirName.getName())).findFirst();

        if (treeNode.isPresent()){
            retVal = treeNode.get();

        } else {
            retVal = new RelativeDirectoryTreeNode(this, dirName);
            addNode(retVal);
        }

        return retVal;
    }
    //</editor-fold>
    // ===================

    // ===========
    //   Methods
    // ===========

    public ListCompareResult compare(){
        initializeDirectory();
        ListComparer<String> comparer = new ListComparer<>();

        List<String> entries = _directory.getEntries(entry -> true, entry -> true, File::getName);
        ListCompareResult<String> compare = comparer.compare(entries, _directories);

        updateDirectories(compare);
        return compare;
    }

    private void updateDirectories(ListCompareResult<String> compare){
        _directories = new LinkedList<>();

        for (String file : compare.getNewFiles()) {
            _directories.add(file);
        }

        for (String file : compare.getUnchangedFiles()) {
            _directories.add(file);
        }
    }

    protected final void initializeDirectory(){
        if (_directory == null){
            _directory = new HDirectory(getAbsolutePath());
        }
    }

    // ============
        //<editor-fold desc="Accessors">
    // TODO: Tackle Problem with having multiple instance. But maybe its not that bad?
    public final boolean addNode(AbstractTreeNode node) {
        _childNodes.add(node);
        return true;
    }

    public final boolean addNode(RelativeDirectory node) {
        boolean canAdd = false;
        if (!node.isEmpty()){
            _childNodes.add(new RelativeDirectoryTreeNode(this, node));
            canAdd = true;
        }

        return canAdd;
    }

    /**
     * Adds a directory to this node
     * @param directory should not be null
     */
    public final void addDirectory(String directory){
        _directories.add(directory);
    }

    /**
     * Adds a file to this node
     * @param file should not be null
     */
    public final void addFile(String file){
        _files.add(file);
    }

    public List<AbstractTreeNode> getChildNodes() {
        return _childNodes;
    }

    // TODO: Could make a problem with duplicate elements
    public int childrenCount(){
        return _childNodes.size();
    }

    public int filesCount(){
        return _files.size();
    }

    public int directoriesCount(){
        return _directories.size();
    }
    //</editor-fold>
    // ============

    // ==============
        //<editor-fold desc="Java Default">
    @Override
    public abstract boolean equals(Object tree);

    public final boolean equals(Object tree, Class<? extends AbstractTreeNode> nodeClass){
        boolean equals;

        if (this == tree){
            equals = true;


        } else if (nodeClass.isInstance(tree)) {
            TreeNode dirTree = (TreeNode) tree;
            equals = dirTree.getName().equals(getName());

        } else {
            equals = false;
        }

        return equals;
    }

    @Override
    public abstract String toString();
    //</editor-fold>
    // ==============

    // =============
        //<editor-fold desc="HDirectory">

    @Override
    public abstract String getAbsolutePath();

    @Override
    public List<HFile> getFiles(FileFilter filter) {
        initializeDirectory();
        return _directory.getFiles(filter);
    }

    @Override
    public List<HFile> getFiles() {
        initializeDirectory();
        return _directory.getFiles();
    }

    @Override
    public List<HDirectory> getDirectories(FileFilter filter) {
        initializeDirectory();
        return _directory.getDirectories();
    }

    @Override
    public List<HDirectory> getDirectories() {
        initializeDirectory();
        return _directory.getDirectories();
    }

    @Override
    public RelativeDirectory remove(AbsoluteDirectory absDir) throws PathsNotMatchingException {
        initializeDirectory();
        return _directory.remove(absDir);
    }

    @Override
    public boolean exists() {
        initializeDirectory();
        return _directory.exists();
    }

    @Override
    public AbsoluteFile concat(RelativeFile relFile) {
        initializeDirectory();
        return _directory.concat(relFile);
    }

    @Override
    public int length() {
        initializeDirectory();
        return _directory.length();
    }

    @Override
    public AbsoluteDirectory concat(RelativeDirectory rel) {
        initializeDirectory();
        return _directory.concat(rel);
    }

    @Override
    public AbsoluteDirectory add(String rel) {
        initializeDirectory();
        return _directory.add(rel);
    }

    @Override
    public AbsoluteDirectory remove(RelativeDirectory removal) throws PathsNotMatchingException {
        initializeDirectory();
        return _directory.remove(removal);
    }

    @Override
    public boolean equals(Path path) {
        initializeDirectory();
        return _directory.equals(path);
    }

    @Override
    public DirectoryNode headNode() {
        initializeDirectory();
        return _directory.headNode();
    }

    @Override
    public DirectoryNode tailNode() {
        initializeDirectory();
        return _directory.tailNode();
    }

    @Override
    public AbsoluteDirectory copy() {
        initializeDirectory();
        return _directory.copy();
    }
    //</editor-fold>
    // =============

    /**
     * Utility class for Converting tree nodes into Directory Nodes
     */
    protected static class NodeConverter {

        public static DirectoryNode toNode(TreeNode node){
            return new DirectoryNode(null, node.getName());
        }

        public static DirectoryNode toNode(DirectoryNode prev, TreeNode node){
            DirectoryNode directoryNode = new DirectoryNode(null, node.getName());
            prev.setPrev(prev);

            return directoryNode;
        }
    }
}
