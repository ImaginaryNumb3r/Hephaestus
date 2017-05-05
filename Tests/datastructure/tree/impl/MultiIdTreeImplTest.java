package datastructure.tree.impl;

import datastructure.traverser.IdTraverser;
import datastructure.tree.impl.exception.NodeAlreadyExistsException;
import datastructure.tree.impl.node.MultiIdTreeNodeReaderImpl;
import graph.search.BreadthFirstSearch;
import graph.search.DepthFirstSearch;
import org.junit.jupiter.api.Test;

import java.util.*;

/**^´
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

        // Compare actual values with expected values
        List<String> bfs = Arrays.asList(ROOT, "sub", "sub2", "sub sub 1", "sub sub 2", "sub sub 1", "sub sub 2");
        Iterator<String> bfsIter = bfs.iterator();

        Iterator<MultiIdTreeNodeReaderImpl<String, Integer>> iterator = tree.iterator(new BreadthFirstSearch<>());
        iterator.forEachRemaining(node -> {
            assert bfsIter.next().equals(node.getIdentifier());
        } );

        // Compare actual values with expected values
        List<String> dfs = Arrays.asList(ROOT, "sub2", "sub sub 2", "sub sub 1", "sub", "sub sub 2", "sub sub 1");
        Iterator<String> dfsIter = dfs.iterator();

        iterator = tree.iterator(new DepthFirstSearch<>());
        iterator.forEachRemaining(node -> {
            assert dfsIter.next().equals(node.getIdentifier());
        } );
    }
}