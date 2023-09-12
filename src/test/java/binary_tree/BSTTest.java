package binary_tree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    @DisplayName("add left")
    void addLeft() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "90");
        bst.AddKeyValue(110, "110");

        assertEquals(3, bst.Count());

        BSTFind<String> found = bst.FindNodeByKey(80);

        assertFalse(found.NodeHasKey);
        boolean addResult = bst.AddKeyValue(80, "80");
        assertTrue(found.ToLeft);
        assertTrue(addResult);

        found = bst.FindNodeByKey(80);
        assertTrue(found.NodeHasKey);
        assertEquals(4, bst.Count());
    }

    @Test
    @DisplayName("add right")
    void addRight() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "90");
        bst.AddKeyValue(110, "110");

        assertEquals(3, bst.Count());

        BSTFind<String> found = bst.FindNodeByKey(95);

        assertFalse(found.NodeHasKey);
        boolean addResult = bst.AddKeyValue(95, "95");
        assertFalse(found.ToLeft);
        assertTrue(addResult);

        found = bst.FindNodeByKey(95);
        assertTrue(found.NodeHasKey);
        assertEquals(4, bst.Count());
    }

    @Test
    @DisplayName("add exists")
    void addExists() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        bst.AddKeyValue(90, "90");
        bst.AddKeyValue(110, "110");
        bst.AddKeyValue(80, "80");

        assertEquals(4, bst.Count());

        boolean addResult = bst.AddKeyValue(80, "уже есть такой ключ");
        assertFalse(addResult);
        assertEquals(4, bst.Count());
    }

    @Test
    @DisplayName("add exists2")
    void addExists2() {
        BST<String> bst = new BST<>(null);
        boolean firstAdd = bst.AddKeyValue(100, "100");
        assertTrue(firstAdd);

        bst.AddKeyValue(90, "90");
        bst.AddKeyValue(110, "110");
        bst.AddKeyValue(80, "80");

        bst.DeleteNodeByKey(80);

        boolean addResult = bst.AddKeyValue(80, "80");

        assertTrue(addResult);
        assertEquals(4, bst.Count());
    }


    @Test
    @DisplayName("find max")
    void findMax() {
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

        BSTFind<String> found = bst.FindNodeByKey(170);

        BSTNode<String> max = bst.FinMinMax(found.Node, true);

        assertEquals(175, max.NodeKey);
    }

    @Test
    @DisplayName("find min")
    void findMin() {
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

        BSTFind<String> found = bst.FindNodeByKey(170);

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

        BSTNode<String> max = bst.FinMinMax(bst.Root, true);

        assertTrue(deleted);
        assertEquals(8, bst.Count());

    }

    @Test
    @DisplayName("add to emty tree")
    void addToEmptyTree() {
        BST<String> bst = new BST<>(null);
        bst.AddKeyValue(100, "100");
    }

    @Test
    @DisplayName("обход в ширину")
    void wide() {
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

        ArrayList<BSTNode> bstNodes = bst.WideAllNodes();
        assertEquals(9, bstNodes.size());
    }

    @Test
    @DisplayName("обход в глубину in-order")
    void inOrder() {
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

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(1);
        assertEquals(9, bstNodes.size());
    }

    @Test
    @DisplayName("обход в глубину in-order. empty tree")
    void inOrderWhenRootIsNull() {
        BST<String> bst = new BST<>(null);

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(1);
        assertEquals(0, bstNodes.size());
    }

    @Test
    @DisplayName("обход в глубину in-order. empty tree")
    void inOrderOnlyRoot() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst = new BST<>(root);

        ArrayList<BSTNode> bstNodes = bst.DeepAllNodes(1);
        assertEquals(1, bstNodes.size());
    }

}