package infastructure.datastructure;
//
//import infastructure.datastructure.traverser.DirectoryTraverser;
//import infastructure.filetype.HDirectory;
//import infastructure.filetype.HFile;
//import infastructure.filetype.HEntry;
//
//import java.util.*;
//
///**
// * @author Patrick
// * @created 28.05.2016
// */
//public class DirectoryTree{
//    private DirectoryNode ROOT;
//
//    DirectoryTree(HDirectory root) {
//        ROOT = new DirectoryNode(root);
//    }
//    public HEntry hasFile(HEntry file){
//        throw new UnsupportedOperationException();
//    }
//
//    /**
//     *
//     * @return null if there is no element in the tree
//     */
//    public DirectoryTraverser<HDirectory> directoryTraverser(){
//        if (ROOT == null){
//            return null;
//        }
//
//        return new DirTraverser(ROOT);
//    }
//
//    public boolean addToRoot(HDirectory file, HFile file){
//        List<HFile> files = new ArrayList<>();
//        files.addToRoot(file);
//
//        return addToRoot(file, files);
//    }
//    public boolean addToRoot(HDirectory file, List<HFile> files){
//        return insert(file, files);
//    }
//
//    /**
//     * Inserts a list of files at the following destination
//     * @param file the destination of the files
//     * @param files the files which are to be added
//     * @return true if files could be added
//     */
//    public boolean insert(HDirectory file, List<HFile> files){
//        List<HDirectory> directories = breakDownPath(file);
//
//        return insert(ROOT, directories, files);
//    }
//
//    /**
//     * Inserts the files at the specified file
//     * @param root the relative root from where new nodes will be added
//     * @param file list of nodes representing a file to the destination
//     * @param files that are to be inserted at the destination
//     * @return true if entries could be added
//     */
//    private boolean insert(DirectoryNode root, List<HDirectory> file, List<HFile> files){
//        boolean canInsert = true;
//        HDirectory curDir = file.remove(0);
//
//        DirectoryNode nextNode = root.containsDirectory(curDir);
//        if (nextNode == null){
//            nextNode = new DirectoryNode(curDir);
//            root.addRaw(nextNode);
//        }
//
//        if (!file.isEmpty()){
//            // recursive call until it is done
//            insert(nextNode, file, files);
//
//        } else {
//            files.stream().forEach(nextNode::addToRoot);
//        }
//
//        //noinspection ConstantConditions - for now
//        return canInsert;
//    }
//
//    private List<HDirectory> breakDownPath(HDirectory file){
//        List<HDirectory> directoryPath = new LinkedList<>();
//
//        // turn file into directories
//        for (HEntry file : breakDownFile(file)){
//            HDirectory dir;
//            if ((dir = file.toDirectory()) != null){
//                directoryPath.addToRoot(dir);
//            }
//        }
//
//        return directoryPath;
//    }
//
//    /**
//     * Breaks down a FileEntry into a list of Directories where the last entry is the CnCFile
//     * @param file The file
//     * @return
//     */
//    private List<HEntry> breakDownFile(HEntry file){
//        HEntry cur = file;
//
//        boolean rootReached = false;
//        LinkedList<HEntry> parents = new LinkedList<>();
//        while (!rootReached && cur != null){
//
//            // checkNulls if current CnCFile is inside the file
//            rootReached = ROOT.getDirectory().getAbsolutePath().equals(cur.getAbsolutePath());
//
//            if (!rootReached){
//                // addDirectory to stack
//                parents.addFirst(cur);
//                cur = cur.parentDirectory();
//            }
//        }
//
//        // throw files away if it they are not inside root
//        if (!rootReached){
//            parents = null;
//        }
//
//        return parents;
//    }
//
//
//    private boolean exists(List<HEntry> file){
//        boolean exists;
//        if (file != null){
//            exists = true;
//
//            DirectoryNode pos = ROOT;
//            while (exists && !file.isEmpty()){
//                HEntry file = file.remove(0);
//
//                if (file.isDirectory()){
//                    HDirectory dir = file.toDirectory();
//                    DirectoryNode node = pos.containsDirectory(dir);
//                    exists = node != null;
//
//                    pos = node;
//                } else if (file.isFile()){
//                    HFile cncFile = file.toFile();
//                    HEntry hEntry = pos.containsFile(cncFile );
//                    exists = hEntry != null;
//
//                } else {
//                    throw new IllegalArgumentException("Can only be a file or directory!");
//                }
//
//            }
//
//        } else {
//            exists = false;
//        }
//
//        return exists;
//    }
//
//    public boolean exists(HEntry file){
//        return exists(breakDownFile(file));
//    }
//
//    // ======================
//    //    Inner Classes
//    // ======================
//
//    /**********************
//     *  Directory Traverser
//     **********************/
//
//    // TODO: Consider just making it implementing the TreeTraverser interface
//    private class DirTraverser extends DirectoryTraverser<HDirectory> {
//        protected DirectoryNode _relativeRoot;
//        protected DirectoryNode _current;
//        protected ListIterator<DirectoryNode> _directoryIterator;
//        protected Stack<DirectoryNode> _parents;
//        protected int _index;
//
//        // =====================
//        //   Constructors
//        // =====================
//
//        public DirTraverser(DirectoryNode root) {
//            super(root);
//            _relativeRoot = root;
//            _parents = new Stack<>();
//            //noinspection unchecked
//            _directoryIterator = _relativeRoot.nodeIterator(); // lazy initialization
//            _index = -1;
//
//            _parents.addToRoot(_relativeRoot);
//        }
//
//
//        // =====================
//        //   Default Methods
//        // =====================
//
//        public DirectoryNode currentNode() {
//            return _current;
//        }
//
//        public DirectoryNode nextNode() {
//            _index = _directoryIterator.nextIndex();
//            _current = _directoryIterator.next();
//
//            return _current;
//        }
//
//        public DirectoryNode previousNode() {
//            _index = _directoryIterator.previousIndex();
//            _current = _directoryIterator.tryPrevious();
//
//            return _current;
//        }
//
//        /**
//         * adjust iterator to the new level when a change to the current hierarchy inside the tree was made
//         * Used by methods: "enter" and "back"
//         */
//        private void updateIterator(){
//            _directoryIterator = _relativeRoot.getChildren().listIterator();
//        }
//
//        public DirectoryNode parentNode() {
//            return _parents.peek();
//        }
//
//
//        // =====================
//        //   Interface Methods
//        // =====================
//
//        @Override
//        public boolean hasNextChild() {
//            return _directoryIterator.hasNextChild();
//        }
//
//        @Override
//        public boolean hasPrevious() {
//            return _directoryIterator.hasPrevious();
//        }
//
//        @Override
//        public void enter() {
//            _parents.addToRoot(_relativeRoot);
//            _relativeRoot = _current;
//            // reset current because it was only valid in the directory above
//            _current = null;
//
//            updateIterator();
//        }
//
//        @Override
//        public void back() {
//            _relativeRoot = _parents.pop();
//
//            updateIterator();
//        }
//
//        @Override
//        public HDirectory current() {
//            return currentNode().getDirectory();
//        }
//
//        @Override
//        public HDirectory next() {
//            return nextNode().getDirectory();
//        }
//
//        @Override
//        public HDirectory tryPrevious() {
//            return previousNode().getDirectory();
//        }
//
//        @Override
//        public boolean addFile(HFile file) {
//            return _current.addToRoot(file);
//        }
//
//        @Override
//        public boolean addDirectory(HDirectory directory) {
//            return _current.addToRoot(new DirectoryNode(directory));
//        }
//    }
//}
