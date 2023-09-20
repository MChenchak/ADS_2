import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BalancedBSTTest {

    @Test
    void isBalanced() {
        int[] a = {4,5,3,1,2,7,6,8};

        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(a);

        assertTrue(tree.IsBalanced(tree.Root));
    }

    @Test
    void isEmptyTreeBalanced() {
        int[] a = {};

        BalancedBST tree = new BalancedBST();
        tree.GenerateTree(a);

        assertTrue(tree.IsBalanced(tree.Root));
    }

    @Test
    void notBalanced() {
        BalancedBST tree = new BalancedBST();
        tree.Root = new BSTNode(100, null);
        tree.Root.Level = 0;

        BSTNode leftChild = new BSTNode(1000, tree.Root);
        leftChild.Level = 1;
        tree.Root.LeftChild = leftChild;

        BSTNode leftChild2 = new BSTNode(10200, leftChild);
        leftChild2.Level=2;
        leftChild.LeftChild = leftChild2;

        assertFalse(tree.IsBalanced(tree.Root));
    }
  
}