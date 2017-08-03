package datastructure.tree.impl;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import core.exception.ParameterNullException;
import core.tuple.Tuple;
import core.util.collections.Lists;
import core.util.contracts.Contract;
import datastructure.traverser.IdTraverser;
import datastructure.tree.impl.node.MultiIdTreeNodeReaderImpl;
import datastructure.tree.interfaces.ReadableTree;
import datastructure.tree.impl.exception.NodeAlreadyExistsException;
import datastructure.tree.node.subtype.IdTreeNodeReader;
import datastructure.tree.node.subtype.subtype.MultiIdTreeNode;
import datastructure.tree.node.subtype.subtype.MultiIdTreeNodeReader;
import datastructure.tree.interfaces.subtypes.MultiIdTree;
import graph.GraphIterator;
import graph.search.DepthFirstSearch;
import graph.search.GraphSearchStrategy;

import java.util.*;

/**
 * @author Patrick
 * @since 26.01.2017
 * @param <I> The identifier for individual nodes
 * @param <V> The value for individual nodes
 * @param <N> The Node itself and of the children/parent
 * @param <R> The read only version of the node
 *
 * A tree where every node has a variable number of sub-nodes.
 * Furthermore, each node in the tree can have its own value.
 */
// TODO: Adding mechanism
@SuppressWarnings("WeakerAccess")
public abstract class AbstractMultiIdTree
        <I /*extends Comparable<I>*/, V, N extends MultiIdTreeNode<I, V, N>, R extends MultiIdTreeNodeReader<I, V, R>>
        implements MultiIdTree<I, V>, ReadableTree<R>, Iterable<R> {
    //<editor-fold desc="Attributes">
    protected final N _sentinel;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    /**
     * Constructor for internal purposes
     * @param root node of the tree. Parent of all subsequent nodes
     */
    protected AbstractMultiIdTree(@NotNull N root){
        if (root == null) throw new ParameterNullException();
        _sentinel = root;
    }

    /**
     * Public constructor
     * @param identifier the way of accessing the node after its creating. May not be null
     * @param value that is saved in the root node
     * @throws ParameterNullException if identifier is null
     */
    public AbstractMultiIdTree(@NotNull I identifier, V value){
        if (identifier == null) throw new ParameterNullException();
        _sentinel = makeNode(identifier, value,null);
    }
    //</editor-fold>

    //<editor-fold desc="Access Methods">

    /**
     * Adds a new node to the following file inside the tree
     * @param sequence file to the parent that has a node to be added
     * @param identifier identifier of the new node
     * @param value value of the new node
     * @throws NoSuchElementException if the file is not valid
     * @throws NodeAlreadyExistsException if the node already exists
     */
    public void add(List<I> sequence,I identifier, V value){
        N node = searchNode(sequence);
        verifyNode(node, identifier);

        node.addChild(makeNode(identifier, value, node));
    }

    /**
     * Verifies whether adding a node with the following identifier can be added to the given node
     * @param parentNode parent of the node that is to be added
     * @param identifier of the new node that is to be added
     * @throws NoSuchElementException if the parent is null. Hence, it cannot enqueue nodes
     * @throws NodeAlreadyExistsException if the identifier already exists
     */
    private void verifyNode(N parentNode, I identifier){
        if (parentNode == null) throw new NoSuchElementException("The sequence does not point to a valid tree node");

        boolean idAlreadyExists = parentNode.children().stream().anyMatch(child -> child.getIdentifier() == identifier);
        if (idAlreadyExists) throw new NodeAlreadyExistsException("Cannot addToRoot node because a node with the same identifier already exists");
    }

    /**
     *  Adds to the root of the tree
     * @param identifier identifier of the new node
     * @param value of the new node
     */
    public void addToRoot(I identifier, V value){
        add(Collections.singletonList(_sentinel.getIdentifier()), identifier, value);
    }

    public void addTreeToRoot(AbstractMultiIdTree<I, V, N, R> subTree){
        addTree(Collections.singletonList(_sentinel.getIdentifier()), subTree);
    }

    public void addTree(List<I> path, AbstractMultiIdTree<I, V, N, R> subTree){
        Contract.checkNull(subTree);
        N node = searchNode(path);
        verifyNode(node, subTree._sentinel.getIdentifier());

        node.addChild(subTree._sentinel);
    }

    /**
     * Deletes the node with the specified identifier in the specified file
     * and adds a new node with a new value and identifier
     * @param sequence of where to find the node
     * @param identifier of the old and new node
     * @param value of the new node
     * @throws NoSuchElementException if the sequence to the node is invalid
     * @return The old node as read-only object
     */
    public MultiIdTreeNodeReader<I,V, N> replace(Deque<I> sequence, I identifier, V value){
        MultiIdTreeNodeReader<I, V, N> removedNode = remove(sequence);

        N parent = removedNode.parent();
        parent.children().add(makeNode(identifier, value, parent));

        return removedNode;
/*        N node = getNode(sequence);
        if (node == null) throw new NoSuchElementException("The sequence does not point to a valid tree node");

        N childNode = node.removeChild(identifier);

        if (childNode == null) throw new NoSuchElementException("The specified node that is to be replaced does not exist");

        // After the old node has been removed, addToRoot the new node
        node.children().addToRoot(makeNode(identifier, value, node));

        return childNode; */
    }

    /**
     * Returns true i fthe specified node exists
     * @param sequence to the specified node
     * @return True if the following node exists
     */
    public boolean hasElement(List<I> sequence){
        return get(sequence) != null;
    }

    /**
     * Searches for a node at the end of the given file
     *
     * @param sequence The file to the searched node
     * @return Null if no such node is present. Otherwise, the node that was searched for
     */
    protected N searchNode(Iterable<I> sequence){
        N curNode = null;
        boolean finished;

        Iterator<I> iter = sequence.iterator();
        do {
            Optional<N> node = getNode(iter.next(), curNode);

            if (node.isPresent()){
                // Continue iteration with the tryNext node
                curNode = node.get();
                finished = false;
            } else {
                finished = true;
            }

        } while (!finished && iter.hasNext());

        // If the iteration was interrupted before the end of the file was reached,
        // the node could not be found. In this case, return null, otherwise the last node.
        return iter.hasNext()
                ? null
                : curNode;
    }

    protected IdTreeNodeReader<I, V> searchNode(@Nullable I identifier){
        return searchNode(identifier, null);
    }

    protected IdTreeNodeReader<I, V> searchNode(@Nullable I identifier, @Nullable V value){
        return new IdTreeNodeReader<I, V>() {
            @Override
            public I getIdentifier() {
                return identifier;
            }

            @Override
            public V getValue() {
                return value;
            }
        };
    }


    /**
     *
     * @param sequence
     * @throws java.util.NoSuchElementException If no such entry exists
     */
    public MultiIdTreeNodeReader<I, V, N> get(List<I> sequence){
        return searchNode(sequence);
    }

    /**
     *
     * @param sequence
     * @throws java.util.NoSuchElementException If no such entry exists
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public MultiIdTreeNodeReader<I, V, N> remove(Deque<I> sequence){
        I lastId = sequence.pollLast();
        N parentOfLast = searchNode(sequence);

        return parentOfLast.removeChild(lastId);
    }
    //</editor-fold>

//    /**
//     * Returns an iterator, based on the traversing strategy
//     * @param strategy how to create a list get a DAG (directed acyclic graph). May not be null
//     * @return Iterator of all nodes inside the tree
//     */
//    public abstract Iterator<R> iterator(@NotNull GraphSearchStrategy<R> strategy);

    /**
     * Iterated the tree via a Depth First Search
     * @return InnerIterator for all nodes of the tree
     */
    public Iterator<R> iterator(){
        return iterator(new DepthFirstSearch<>());
    }

    /**
     * Iterate the tree by a given strategy
     * @param strategy for iteration
     * @return InnerIterator for all nodes of the tree
     */
    public Iterator<R> iterator(@NotNull GraphSearchStrategy<R> strategy){
        return GraphIterator.from(iterationStartNode(), strategy);
    }

    @Override
    public List<R> toList(GraphSearchStrategy<R> strategy) {
        return Lists.toLinkedList(iterator(strategy));
    }

    //<editor-fold desc="Protected Methods">
    /**
     * Returns the start node for iterations.
     * Returns a version of the internal nodes which can be given outside.
     * @return the start node for iterations.
     */
    protected abstract R iterationStartNode();

    protected abstract N makeNode(I identifier, V value, N root);

    protected N makeNode(I identifier, N root){
        return makeNode(identifier, null, root);
    }

    /**
     *
     * @param id - Identifier of the node
     * @param root - Parent node whose children are to be searched
     * @throws NoSuchElementException if no such Element exists
     * @return Element matching the id
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    protected N get(I id, N root){
        Optional<N> optional = getNode(id, root);
        return optional.get();
    }

    /**
     * Returns the child node get root matching the id
     * Returns the sentinel if root is null
     * @param id the identifier of the child node
     * @param root parent node of the searched node
     * @return sentinel if root is null
     */
    protected Optional<N> getNode(I id, N root){
        Optional<N> optional;
        if (root != null){
            optional = root.children().stream()
                    .filter(node -> node.getIdentifier() == id).findFirst();
        }
        // Return sentinel
        else if (id.equals(_sentinel.getIdentifier())){
            optional = Optional.of(_sentinel);
        }
        else {
            optional = Optional.empty();
        }

        return optional;
    }

    /**
     * Tries to get a node get the list of children of a root element (identified via the identifier).
     * If the element could not be found, it will be created instead
     * @param id - Identifier of the node
     * @param value - Value of the node
     * @param root - Parent of the new node
     * @return The node
     */
    // TODO: Doesnt work
    protected Tuple<N, NodeState> getOrMake(I id, V value, N root){
        Optional<N> optional = getNode(id, root);

        NodeState state = optional.isPresent() ? NodeState.EXISTING : NodeState.NEW;
        N node = optional.orElseGet(() -> makeNode(id, value, root));

        return Tuple.from(node, state);
    }
    //</editor-fold>

    //<editor-fold desc="Traversing">
    @Override
    public IdTraverser<I, V> traverser() {
        return new TraverserImpl(_sentinel);
    }

    @Override
    public IdTraverser<I, V> traverser(List<I> startingPoint) {
        return new TraverserImpl(searchNode(startingPoint));
    }

    /**
     * Inner Traverser for the structure of the tree
     */
    protected class TraverserImpl
            implements IdTraverser<I, V> {
        protected final Stack<Level> _previous;
        protected N _current;
        protected Iterator<N> _iter;
        protected N _next;

        public TraverserImpl(N sentinel) {
            _previous = new Stack<>();
            _current = sentinel;
        }

        @Override
        public boolean hasParent() {
            return _current.hasParent();
        }

        /**
         * Loses the information about the current state and goes back
         * to the state of the parent node
         */
        @Override
        public void enterParent() {
            _next = _current;
            Level prevLevel = _previous.pop();
            _current = prevLevel.ROOT;
            _iter = prevLevel.ITER;
        }

        @Override
        public boolean hasChildren() {
            return _current != null && _current.hasChildren();
        }

        @Override
        public boolean hasNextChild() {
            initialize();
            return _iter.hasNext();
        }

        @Override
        public MultiIdTreeNodeReader<I,V, N> nextChild() {
            initialize();
            _next = _iter.next();

            return _next;
        }

        @Override
        public void enter() {
            _previous.add(new Level(_current, _iter));
            _current = _next;
            _next = null;

            _iter = _current.children().iterator();
        }

        @Override
        public MultiIdTreeNodeReader<I,V, N> value() {
            return _current;
        }

        protected void initialize(){
            if (_iter == null){
                _iter = _current.children().iterator();
            }
        }

        @Override
        public String toString() {
            return "Current: { " + _current + " } | tryNext: { " + _next + " }";
        }

        //<editor-fold desc="Level">
        /**
         * One level inside the tree hierarchy
         */
        protected class Level{
            protected final N ROOT;
            protected final Iterator<N> ITER;

            public Level(@NotNull N root, @NotNull Iterator<N> iter) {
                Contract.checkNulls(root, iter);

                ROOT = root;
                ITER = ROOT.children().iterator();
            }
        }
        //</editor-fold>
    }
    //</editor-fold>

    private enum NodeState{
        NEW, EXISTING
    }
}
