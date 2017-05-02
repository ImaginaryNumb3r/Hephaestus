package datastructure.tree.impl;

import datastructure.traverser.IdTraverser;
import datastructure.tree.impl.exception.NodeAlreadyExistsException;
import datastructure.tree.impl.node.MultiIdTreeNodeImpl;
import datastructure.tree.impl.node.MultiIdTreeNodeReaderImpl;
import datastructure.tree.node.subtype.IdTreeNodeReader;
import datastructure.tree.node.subtype.MultiTreeNode;
import datastructure.tree.node.subtype.MultiTreeNodeReader;
import datastructure.tree.node.subtype.subtype.MultiIdTreeNodeReader;
import graph.search.GraphSearchStrategy;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author Patrick
 * @since 30.04.2017
 */
class MultiIdTreeImplTest {
    private static final String ROOT = "ROOT";

    @Test
    void testAdd() {
        MultiIdTreeImpl<String, Integer> tree = new MultiIdTreeImpl<>(ROOT, 0);

        List<String> path = Collections.singletonList(ROOT);
        tree.add(path, "new Node", 1);

        boolean throwsException = false;
        try {
            tree.add(path, "new Node", 1);
        } catch (NodeAlreadyExistsException ex){
            throwsException = true;
        }
        assert throwsException;
    }

    @Test
    void testAddRoot(){
        MultiIdTreeImpl<String, Integer> tree = new MultiIdTreeImpl<>(ROOT, 0);
        tree.addToRoot("new Node", 1);

        boolean throwsException = false;
        try {
            tree.addToRoot("new Node", 1);
        } catch (NodeAlreadyExistsException ex){
            throwsException = true;
        }
        assert throwsException;
    }

    private static MultiIdTreeImpl<String, Integer> makeTree(){
        MultiIdTreeImpl<String, Integer> tree = new MultiIdTreeImpl<>(ROOT, 0);
        MultiIdTreeImpl<String, Integer> subTree = new MultiIdTreeImpl<>("sub", 1);
        subTree.addToRoot("sub sub", 2);

        tree.addTreeToRoot(subTree);
        return tree;
    }

    @Test
    void testAddTree(){
        MultiIdTreeImpl<String, Integer> tree = makeTree();
        IdTraverser<String, Integer> traverser = tree.traverser();

        // Check root level
        assert traverser.value().getIdentifier().equals(ROOT);
        assert traverser.value().getValue().equals(0);
        traverser.nextChild();
        traverser.enter();

        // Check level 1
        assert traverser.value().getIdentifier().equals("sub");
        assert traverser.value().getValue().equals(1);
        traverser.nextChild();
        traverser.enter();

        // Check level 2
        assert traverser.value().getIdentifier().equals("sub sub");
        assert traverser.value().getValue().equals(2);

        boolean throwsException = false;
        try {
            traverser.nextChild();
        } catch (NoSuchElementException ex){
            throwsException = true;
        }
        assert throwsException;
    }

    @Test
    void testTraverser(){
        MultiIdTreeImpl<String, Integer> tree = new MultiIdTreeImpl<>(ROOT, 0);
        tree.addToRoot("sub", 1);
        tree.add(Arrays.asList(ROOT, "sub"), "sub sub 1" , 3);
        tree.add(Arrays.asList(ROOT, "sub"), "sub sub 2" , 4);

        tree.addToRoot("sub2", 2);
        tree.add(Arrays.asList(ROOT, "sub2"), "sub sub 1" , 5);
        tree.add(Arrays.asList(ROOT, "sub2"), "sub sub 2" , 6);

        System.out.println("Breadth First:");
        Iterator<MultiIdTreeNodeReaderImpl<String, Integer>> iterator = tree.iterator(GraphSearchStrategy.BREADTH_FIRST);
        iterator.forEachRemaining(node -> System.out.println(node.getIdentifier() + " | " + node.getValue()));
        System.out.println();

        System.out.println("Depth First:");
        iterator = tree.iterator(GraphSearchStrategy.DEPTH_FIRST);
        iterator.forEachRemaining(node -> System.out.println(node.getIdentifier() + " | " + node.getValue()));
    }
}