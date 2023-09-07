package simple_tree2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    @Test
    @DisplayName("Количество узлов в дереве из одного элемента")
    void rootNodeCount() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTree<String> tree = new SimpleTree<>(root);

        assertEquals(1, tree.Count());


    }

    @Test
    @DisplayName("Количество узлов в дереве из двух элементов")
    void countOfTwoElements() {
       SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);

        SimpleTree<String> tree = new SimpleTree<>(root);

        int count = tree.Count();

        assertEquals(2, count);
        assertEquals(1, tree.LeafCount());
    }

    @Test
    @DisplayName("Количество листьев в дереве из одного элемента")
    void rootLeafCount() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTree<String> tree = new SimpleTree<>(root);

        assertEquals(1, tree.Count());
        assertEquals(1, tree.LeafCount());
    }

    @Test
    @DisplayName("Количество листьев в дереве из двух элементов")
    void leafCountOfTwoElements() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);

        SimpleTree<String> tree = new SimpleTree<>(root);

        int count = tree.Count();

        assertEquals(2, count);
        assertEquals(1, tree.LeafCount());
    }

    @Test
    @DisplayName("Добавление узла. +1 узел")
    void addChild() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("child2", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        tree.AddChild(child, child2);

        int count = tree.Count();

        assertEquals(3, count);
        assertEquals(1, tree.LeafCount());
    }

    @Test
    @DisplayName("Добавление узла. +1 узел +1 лист")
    void addChild2() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("child2", null);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("child3", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        tree.AddChild(child, child2);
        tree.AddChild(child, child3);

        int count = tree.Count();

        assertEquals(4, count);
        assertEquals(2, tree.LeafCount());
    }

    @Test
    @DisplayName("Delete exists node")
    void deleteExistsNode() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("child2", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        tree.AddChild(child, child2);

        tree.DeleteNode(child);

        assertNull(tree.FindNodesByValue(child.NodeValue));

        assertEquals(1, tree.Count());
        assertEquals(1, tree.LeafCount());
    }

    @Test
    @DisplayName("List of nodes")
    void listNodes() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("child2", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        tree.AddChild(child, child2);

        List<SimpleTreeNode<String>> simpleTreeNodes = tree.GetAllNodes();


        assertEquals(3, tree.Count());
        assertEquals(1, tree.LeafCount());
        assertEquals(3, simpleTreeNodes.size());
    }

    @Test
    @DisplayName("Node not found")
    void notFound() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("child2", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        tree.AddChild(child, child2);

        List<SimpleTreeNode<String>> simpleTreeNodes = tree.GetAllNodes();

        List<SimpleTreeNode<String>> f = tree.FindNodesByValue("f");


        assertNull(f);
        assertEquals(3, tree.Count());
        assertEquals(1, tree.LeafCount());
        assertEquals(3, simpleTreeNodes.size());
    }

    @Test
    @DisplayName("Node found")
    void nodeFound() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> child = new SimpleTreeNode<>("child", root);
        SimpleTreeNode<String> child2 = new SimpleTreeNode<>("child2", null);
        SimpleTreeNode<String> child3 = new SimpleTreeNode<>("child", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        tree.AddChild(child, child2);
        tree.AddChild(child2, child3);

        List<SimpleTreeNode<String>> simpleTreeNodes = tree.GetAllNodes();

        List<SimpleTreeNode<String>> f = tree.FindNodesByValue("child");

        assertEquals(2, f.size());
        assertEquals(4, tree.Count());
        assertEquals(1, tree.LeafCount());
        assertEquals(4, simpleTreeNodes.size());
    }

}