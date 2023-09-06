package binary_tree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @Test
    @DisplayName("Поиск в дереве только из корневого узла. Узел не найден. Вставить в left")
    void notFoundInsertToLeft() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst  = new BST<>(root);

        BSTFind<String> found = bst.FindNodeByKey(90);

        assertEquals(found.Node, root);
        assertFalse(found.NodeHasKey);
        assertTrue(found.ToLeft);
    }

    @Test
    @DisplayName("Поиск в дереве только из корневого узла. Узел не найден. Вставить в right")
    void notFoundInsertToRight() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst  = new BST<>(root);

        BSTFind<String> found = bst.FindNodeByKey(110);

        assertEquals(found.Node, root);
        assertFalse(found.NodeHasKey);
        assertFalse(found.ToLeft);
    }

    @Test
    @DisplayName("Node found - root")
    void nodeFoundRoot() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BST<String> bst  = new BST<>(root);

        BSTFind<String> found = bst.FindNodeByKey(100);

        assertEquals(found.Node, root);
        assertTrue(found.NodeHasKey);
        assertFalse(found.ToLeft);
    }

    @Test
    @DisplayName("Node found - left node")
    void nodeFoundLeft() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BSTNode<String> left = new BSTNode<>(90, "left", root);
        BSTNode<String> right = new BSTNode<>(110, "right", root);
        BST<String> bst  = new BST<>(root);

        BSTFind<String> found = bst.FindNodeByKey(90);

        assertEquals(found.Node, left);
        assertTrue(found.NodeHasKey);
    }

    @Test
    @DisplayName("Node found - right node")
    void nodeFoundRight() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BSTNode<String> left = new BSTNode<>(90, "left", root);
        BSTNode<String> right = new BSTNode<>(110, "right", root);
        BST<String> bst  = new BST<>(root);

        BSTFind<String> found = bst.FindNodeByKey(110);

        assertEquals(found.Node, right);
        assertTrue(found.NodeHasKey);
    }

    @Test
    @DisplayName("Add - right node")
    void addRight() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BSTNode<String> left = new BSTNode<>(90, "left", root);
        BSTNode<String> right = new BSTNode<>(110, "right", root);
        BST<String> bst  = new BST<>(root);

        boolean result = bst.AddKeyValue(150, "newRight");

        assertTrue(result);
        assertEquals(right.RightChild.NodeValue, "newRight");
    }

    @Test
    @DisplayName("Add - left node")
    void addLeft() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BSTNode<String> left = new BSTNode<>(90, "left", root);
        BSTNode<String> right = new BSTNode<>(110, "right", root);
        BST<String> bst  = new BST<>(root);

        boolean result = bst.AddKeyValue(15, "newleft");

        assertTrue(result);
        assertEquals(left.LeftChild.NodeValue, "newleft");
    }

    @Test
    @DisplayName("Add - false when exists")
    void addExistsKey() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BSTNode<String> left = new BSTNode<>(90, "left", root);
        BSTNode<String> right = new BSTNode<>(110, "right", root);
        BST<String> bst  = new BST<>(root);

        boolean result = bst.AddKeyValue(110, "newleft");

        assertFalse(result);
    }


    @Test
    @DisplayName("find max")
    void findMax() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BSTNode<String> left = new BSTNode<>(90, "left", root);
        BSTNode<String> right = new BSTNode<>(110, "right", root);
        BST<String> bst  = new BST<>(root);

        BSTNode<String> max = bst.FinMinMax(root, true);

        assertNotNull(max);
        assertEquals(right, max);
    }

    @Test
    @DisplayName("find min")
    void findMin() {
        BSTNode<String> root = new BSTNode<>(100, "root", null);
        BSTNode<String> left = new BSTNode<>(90, "left", root);
        BSTNode<String> right = new BSTNode<>(110, "right", root);
        BST<String> bst  = new BST<>(root);

        BSTNode<String> min = bst.FinMinMax(root, false);

        assertNotNull(min);
        assertEquals(left, min);
    }

    @Test
    @DisplayName("delete when found is leaf")
    void deleteLeaf() {
        BSTNode<String> nine = new BSTNode<>(9, "root", null);
        BSTNode<String> four = new BSTNode<>(4, "four", nine);
        BSTNode<String> seventeen = new BSTNode<>(17, "seventeen", nine);

        BSTNode<String> three = new BSTNode<>(3, "three", four);
        BSTNode<String> six = new BSTNode<>(6, "six", four);

        BSTNode<String> five = new BSTNode<>(5, "five", six);
        BSTNode<String> seven = new BSTNode<>(7, "seven", six);

        BSTNode<String> twentytwo = new BSTNode<>(22, "twentytwo", seventeen);
        BSTNode<String> twenty = new BSTNode<>(20, "twenty", twentytwo);

        BST<String> bst  = new BST<>(nine);

        BSTNode<String> min = bst.FinMinMax(seventeen, false);

        bst.DeleteNodeByKey(20);
        BSTFind<String> found = bst.FindNodeByKey(20);

        assertFalse(found.NodeHasKey);
        assertEquals(twentytwo, found.Node);
    }

    @Test
    @DisplayName("delete when only one child")
    void deleteWhenOnlyOneChild() {
        BSTNode<String> nine = new BSTNode<>(9, "root", null);
        BSTNode<String> four = new BSTNode<>(4, "four", nine);
        BSTNode<String> seventeen = new BSTNode<>(17, "seventeen", nine);

        BSTNode<String> three = new BSTNode<>(3, "three", four);
        BSTNode<String> six = new BSTNode<>(6, "six", four);

        BSTNode<String> five = new BSTNode<>(5, "five", six);
        BSTNode<String> seven = new BSTNode<>(7, "seven", six);

        BSTNode<String> twentytwo = new BSTNode<>(22, "twentytwo", seventeen);
        BSTNode<String> twenty = new BSTNode<>(20, "twenty", twentytwo);

        BST<String> bst  = new BST<>(nine);

        bst.DeleteNodeByKey(22);
        BSTFind<String> found = bst.FindNodeByKey(22);

        assertFalse(found.NodeHasKey);
        assertEquals(twenty, found.Node);
    }

    @Test
    @DisplayName("delete with two children")
    void deleteWhenTwoChildren() {

        BSTNode<String> nine = new BSTNode<>(9, "root", null);
        BSTNode<String> four = new BSTNode<>(4, "four", nine);
        BSTNode<String> seventeen = new BSTNode<>(17, "seventeen", nine);

        BSTNode<String> three = new BSTNode<>(3, "three", four);
        BSTNode<String> six = new BSTNode<>(6, "six", four);

        BSTNode<String> five = new BSTNode<>(5, "five", six);
        BSTNode<String> seven = new BSTNode<>(7, "seven", six);

        BSTNode<String> twentytwo = new BSTNode<>(22, "twentytwo", seventeen);
        BSTNode<String> twenty = new BSTNode<>(20, "twenty", twentytwo);

        BST<String> bst  = new BST<>(nine);

        bst.DeleteNodeByKey(4);
        BSTFind<String> found = bst.FindNodeByKey(4);

        assertFalse(found.NodeHasKey);

    }

    @Test
    @DisplayName("delete with two children right")
    void deleteWhenTwoChildrenRight() {

        BSTNode<String> nine = new BSTNode<>(9, "root", null);
        BSTNode<String> four = new BSTNode<>(4, "four", nine);
        BSTNode<String> seventeen = new BSTNode<>(17, "seventeen", nine);

        BSTNode<String> three = new BSTNode<>(3, "three", four);
        BSTNode<String> six = new BSTNode<>(6, "six", four);

        BSTNode<String> five = new BSTNode<>(5, "five", six);
        BSTNode<String> seven = new BSTNode<>(7, "seven", six);

        BSTNode<String> twentytwo = new BSTNode<>(22, "twentytwo", seventeen);
        BSTNode<String> twenty = new BSTNode<>(20, "twenty", twentytwo);


        BSTNode<String> eight = new BSTNode<>(8, "eight", seven);


        BST<String> bst  = new BST<>(nine);

        bst.DeleteNodeByKey(7);
        BSTFind<String> found = bst.FindNodeByKey(7);

        assertFalse(found.NodeHasKey);

    }



}