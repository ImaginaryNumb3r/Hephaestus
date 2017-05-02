package infastructure.traverser;

//import infastructure.datastructure.DirectoryTree;
//import infastructure.filetype.HDirectory;
//import infastructure.filetype.HFile;
//import infastructure.filetype.HEntry;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author Patrick
// * @created 29.05.2016
// */
//public class RunnerDaemon implements Runnable {
//    private HDirectory ROOT;
//    private Thread _thread;
//    private DirectoryTree _tree;
//    private HashMap<String, Boolean> _ignore;
//
//    public RunnerDaemon(HDirectory root, DirectoryTree tree) {
//        this(root, tree, Collections.emptyList());
//    }
//    public RunnerDaemon(HDirectory root, DirectoryTree tree, List<HEntry> ignoreList) {
//        if (root == null){
//            throw new IllegalArgumentException();
//        }
//        ROOT = root;
//        _thread = null;
//        _tree = tree;
//
//        _ignore = new HashMap<>(ignoreList.size());
//        ignoreList.stream().forEach((
//                HEntry file) -> _ignore.put(file.getAbsolutePath(), true));
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (true){
//                traverse(ROOT);
//                Thread.sleep(100);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void start(){
//        start(false);
//    }
//    public void start(boolean daemon){
//
//        if (_thread == null)
//        {
//            _thread = new Thread (this, "lastModified");
//            _thread.setDaemon(daemon);
//            _thread.start ();
//        }
//    }
//
//    private void traverse(HDirectory root){
//
//        for (HDirectory dir : root.getDirectories()){
//            String debug = dir.toString();
//
//            Boolean ignoreDirectory = _ignore.get(dir.getAbsolutePath());
//            if (ignoreDirectory == null || !ignoreDirectory){
//
//                for (HFile file : dir.getFiles()){
//                    // TODO: Improve performance by adding via a traverser
//
//                    _tree.add(file.parentDirectory(), file);
//                }
//                traverse(dir);
//            }
//        }
//    }
//}