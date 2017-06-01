import datastructure.tree.impl.node.AbstractMultiIdTreeNode;

/**
 * @author Patrick
 * @description
 * @since 28.05.2017
 */
public class InnerNode
        extends AbstractMultiIdTreeNode<String, String, InnerNode> {

    @Override
    protected InnerNode makeChild(String identifier, String value, InnerNode parent) {
        return null;
    }
}
