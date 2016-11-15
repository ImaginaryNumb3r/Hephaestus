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

        public static void main(String[] args) {
                System.out.println("Ohai, World!");
        }

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
//            ignoreList.add(new HDirectory("D:\\KW SDK\\Mods\\One Vision\\Data\\.svn"));
//
//            DirectoryNodeTraverser trav = new DirectoryNodeTraverser(new DirectoryNode(rootDir));
//
//            // Old Test
//
///*            boolean add = fileDirectoryTree.add(file.parentDirectory(), file);
//            boolean add2 = fileDirectoryTree.add(file2.parentDirectory(), file2);
//            boolean add3 = fileDirectoryTree.add(file3.parentDirectory(), file3);
//
//            boolean exists = fileDirectoryTree.exists(file);*/
///*
//            RunnerDaemon daemon = new RunnerDaemon(rootDir, fileDirectoryTree, ignoreList);
//            daemon.start();*/
//        }
    }

