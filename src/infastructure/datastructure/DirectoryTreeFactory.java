package infastructure.datastructure;
//
//import infastructure.filetype.HDirectory;
//
///**
// * @author Patrick
// * @created 28.05.2016
// * @purpose Factory for DirectoryTree
// */
//public class DirectoryTreeFactory {
//
//    /**
//     * Creates a tree when root exists, otherwise returns null
//     * @param root the start of the tree
//     * @return null if not a real directory
//     */
//    public DirectoryTree makeDirectoryTree(String root){
//        return makeDirectoryTree(new HDirectory(root));
//    }
//
//    /**
//     * Creates a tree when root exists, otherwise returns null
//     * @param root the start of the tree
//     * @return null if not a real directory
//     */
//    public DirectoryTree makeDirectoryTree(HDirectory root){
//        DirectoryTree tree;
//        if (root.exists()){
//            tree = new DirectoryTree(root);
//        } else {
//            tree = null;
//        }
//
//        return tree;
//    }
//}
