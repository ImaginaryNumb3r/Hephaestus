package infastructure.traverser;

//import infastructure.datastructure.DirectoryNode;
//import infastructure.datastructure.traverser.DirectoryTraverser;
//import infastructure.filetype.HDirectory;
//import infastructure.filetype.HFile;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
//
//import java.util.HashMap;
//
///**
// * @author Patrick
// * @created 30.05.2016
// */
//public class DirectoryNodeTraverser extends  DirectoryTraverser<DirectoryNode> { /*
//    protected DirectoryNode _curDir;
//    protected DirectoryNode _destDir;
//    protected Stack<NodeIterator<DirectoryNode>> _prevIter;
//    protected NodeIterator<DirectoryNode> _curIter; */
//
//    // ======================
//    //      Constructors
//    // ======================
//
//    public DirectoryNodeTraverser(DirectoryNode root) {
//        super(root);
//    }
//
//    // ======================
//    //   Interface Methods
//    // ======================
//
//
//    /**
//     * Checks if directory is still valid
//     */
//    @Override
//    public void back() {
//        super.back();
//
//        if (!isSynchronized()){
//            synchronize();
//        }
//    }
//
//    protected boolean isSynchronized(){
//        throw new UnsupportedOperationException("Not implemented yet");
//    }
//
//    protected boolean hasChanged(){
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public void enter() {
//        enter(_destDir.nodeIterator());
//    }
//
//    @Override
//    public boolean addFile(HFile file) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean addDirectory(HDirectory directory) {
//        throw new UnsupportedOperationException();
//    }
//
//    // ======================
//    //       Methods
//    // ======================
//
//    /**
//     * Calling this method ensures that the nodes that have been previously iterated are not iterated through again
//     */
//    protected void synchronize(){
//        // throw away future nodes
//        _curIter.split();
//
//        HashMap<String, Boolean> _iteratedNodes = new HashMap<>(_curIter.size());
//
//        // addDirectory iterated nodes to HashMap
//        while (_curIter.hasNextChild()){
//            DirectoryNode cur = _curIter.next();
//            _iteratedNodes.put(cur.getAbsolutePath(), true);
//        }
//
//        for (HDirectory dir : ROOT.getDirectories()){
//            // addDirectory directory if not in hashmap
//            if (!_iteratedNodes.get(dir.getAbsolutePath())){
//                // _curIter.addDirectory(dir);
//            }
//        }
//    }
//
//    /**
//     * Could be moved to a separate spliterator, but that would be overkill
//     */
//    protected void split(){
//        throw new NotImplementedException();
//    }
//
//    public DirectoryNode currentNode() {
//        return _destDir;
//    }
//
//    public DirectoryNode nextNode() {
//        _destDir = _curIter.next();
//
//        return _destDir;
//    }
//
//    public DirectoryNode previousNode() {
//        _destDir = _curIter.previous();
//
//        return _destDir;
//    }
//}