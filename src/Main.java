import datastructure.tree.impl.node.MultiIdTreeNodeImpl;
import datastructure.tree.node.subtype.subtype.MultiIdTreeNodeReader;
import stream.Count;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
/*
    public static void main(String[] args) {
        final String path = "D:";
        AbsoluteDirectory absDir = PathFactory.makeAbsoluteDirectory(path);
        AbsoluteDirectory absDir2 = PathFactory.makeAbsoluteDirectory2(path);
        infastructure.datastructure.tree.DirectoryTree tree = new infastructure.datastructure.tree.DirectoryTree(absDir);
        TreeNode root = tree.getRoot();

        String string = root.toString();
        assert path.equals(string);
    }*/



    private static <I, V, T extends MultiIdTreeNodeReader<I, V, T>, U extends Iterable<U>> void test(){
        MultiIdTreeNodeImpl<I, V> test2 = new MultiIdTreeNodeImpl<>(null);
        MultiIdTreeNodeReader<I, V, T> test = (MultiIdTreeNodeReader<I, V, T>) test2;
        Iterable<T> iterable = test;

        Iterator<MultiIdTreeNodeReader<I, V, T>> iter = null;

        Iterable<T> source = null;
        // iter = GraphIterator.from((T) null, GraphSearchStrategy.BREADTH_FIRST);
    }

    // TODO: Delete Me
    public static void main(String[] args) {
        test();

        MultiIdTreeNodeReader<String, String, ?> readerNode = new MultiIdTreeNodeImpl<>(null);
        Iterable<?> iterable = readerNode;

        MultiIdTreeNodeImpl<String, String> node = new MultiIdTreeNodeImpl<>(null);
        Iterator<MultiIdTreeNodeImpl<String, String>> iterator = node.iterator();
        MultiIdTreeNodeImpl<String, String> next = iterator.next();
    }

        /*public static void main(String[] args) {
                System.out.println("Ohai, World!");
        }*/

//    public static void main(String[] args) {
//
//            LinkedList<Object> objects = new LinkedList<>();
//
//            String root = "D:\\KW SDK\\Mods\\One Vision";
//            String data = "D:\\KW SDK\\Mods\\One Vision\\Data";
//            String path = "D:\\KW SDK\\Mods\\One Vision\\Data\\Static.xml";
//            String path2 = "D:\\KW SDK\\Mods\\One Vision\\Data\\Static.xml";
//            String path3 = "D:\\KW SDK\\Mods\\One Vision\\Data\\AdditionalMaps\\Global Conquest\\StrikeForceBuildTemplate.xml";
//
//            DirectoryTree fileDirectoryTree = new DirectoryTreeFactory().makeDirectoryTree(root);
//
//            HFile file = new HFile(path);
//            HFile file2 = new HFile(path2);
//            HFile file3 = new HFile(path3);
//
//            HDirectory rootDir = new HDirectory(data);
//            List<HEntry> ignoreList = new LinkedList<>();
//            ignoreList.enqueue(new HDirectory("D:\\KW SDK\\Mods\\One Vision\\Data\\.svn"));
//
//            DirectoryNodeTraverser trav = new DirectoryNodeTraverser(new DirectoryNode(rootDir));
//
//            // Old Test
//
///*            boolean addToRoot = fileDirectoryTree.addToRoot(file.parentDirectory(), file);
//            boolean add2 = fileDirectoryTree.enqueue(file2.parentDirectory(), file2);
//            boolean add3 = fileDirectoryTree.enqueue(file3.parentDirectory(), file3);
//
//            boolean exists = fileDirectoryTree.exists(file);*/
///*
//            RunnerDaemon daemon = new RunnerDaemon(rootDir, fileDirectoryTree, ignoreList);
//            daemon.start();*/
//        }
    }

