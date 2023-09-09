package binary_tree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @Test
    @DisplayName("find by key. Found")
    void foundByKey() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "devyanosto");
        bst.AddKeyValue(110, "stodesyat");
        boolean vsmdst = bst.AddKeyValue(80, "vsmdst");

        BSTFind<String> result = bst.FindNodeByKey(80);

        assertTrue(vsmdst);
        assertEquals("vsmdst", result.Node.NodeValue);
        assertTrue(result.NodeHasKey);
    }

    @Test
    @DisplayName("find by key. Found")
    void notFoundByKey() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "devyanosto");
        bst.AddKeyValue(110, "stodesyat");
        bst.AddKeyValue(80, "vsmdst");

        BSTFind<String> result = bst.FindNodeByKey(109);

        assertFalse(result.NodeHasKey);
        assertTrue(result.ToLeft);
        assertEquals("stodesyat", result.Node.NodeValue);
    }

    @Test
    @DisplayName("add exists")
    void addExists() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "devyanosto");
        bst.AddKeyValue(110, "stodesyat");
        bst.AddKeyValue(80, "vsmdst");
        boolean vsmdst = bst.AddKeyValue(80, "vsmdst");

        assertFalse(vsmdst);
    }

    @Test
    @DisplayName("find max")
    void findMax() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "devyanosto");
        bst.AddKeyValue(110, "stodesyat");
        bst.AddKeyValue(80, "vsmdst");

        BSTNode<String> max = bst.FinMinMax(bst.Root, true);

        assertEquals(110, max.NodeKey);
    }

    @Test
    @DisplayName("find min")
    void findMin() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "devyanosto");
        bst.AddKeyValue(110, "stodesyat");
        bst.AddKeyValue(80, "vsmdst");
        bst.AddKeyValue(109, "vsmdst");
        bst.AddKeyValue(112, "vsmdst");

        BSTFind<String> found = bst.FindNodeByKey(110);

        BSTNode<String> min = bst.FinMinMax(found.Node, false);

        assertEquals(109, min.NodeKey);
    }


    @Test
    @DisplayName("delete leaf")
    void deleteLeaf() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "devyanosto");
        bst.AddKeyValue(110, "stodesyat");
        bst.AddKeyValue(80, "vsmdst");
        bst.AddKeyValue(109, "vsmdst");
        bst.AddKeyValue(112, "vsmdst");

        boolean b = bst.DeleteNodeByKey(112);

        assertTrue(b);
    }

    @Test
    @DisplayName("delete root with only left child")
    void delRootWithOnlyLeftChild() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(80, "80");

        bst.DeleteNodeByKey(100);
        assertEquals(1, bst.Count());
        assertEquals(bst.FindNodeByKey(80).Node, bst.Root);
    }

    @Test
    @DisplayName("delete root with only right child")
    void delRootWithOnlyRightChild() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(800, "800");
        bst.AddKeyValue(1000, "1000");

        bst.DeleteNodeByKey(100);
        assertEquals(2, bst.Count());
        assertEquals(bst.FindNodeByKey(800).Node, bst.Root);
    }

    @Test
    @DisplayName("delete node with two children")
    void delNodeWithTwoChildren() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "90");
        bst.AddKeyValue(80, "80");
        bst.AddKeyValue(110, "110");
        bst.AddKeyValue(109, "109");
        bst.AddKeyValue(150, "150");
        bst.AddKeyValue(170, "170");
        bst.AddKeyValue(160, "160");
        bst.AddKeyValue(175, "175");

        assertEquals(9, bst.Count());

        boolean deleted = bst.DeleteNodeByKey(110);

        assertTrue(deleted);
        assertEquals(8, bst.Count());

    }

    @Test
    @DisplayName("add to emty tree")
    void addToEmptyTree() {
        BST<String> bst = new BST<>(null);
        bst.AddKeyValue(100, "100");
    }


}