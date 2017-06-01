package datastructure.tree;

import datastructure.tree.impl.AbstractMultiIdTree;

/**
 * @author Patrick
 * @since 26.05.2017
 */
public class Trees {

    public int count(AbstractMultiIdTree tree){
        int count = 0;

        for (Object ignore : tree) {
            ++count;
        }

        return count;
    }


}
