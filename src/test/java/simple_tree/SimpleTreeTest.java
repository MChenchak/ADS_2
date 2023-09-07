package simple_tree;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    @Test
    @DisplayName("Создание дерева без корневого элемента")
    void createSimpleTreeWithNull() {
        SimpleTree<String> tree = new SimpleTree<>(null);

        assertEquals(0, tree.Count());
    }

    @Test
    @DisplayName("Создание дерева с корневым элементом")
    void createSimpleTreeWithRootNode() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null); // у корневого элемента нет родителя
        SimpleTree<String> tree = new SimpleTree<>(root);

        assertEquals("root", root.NodeValue);
        assertEquals(1, tree.Count());
        assertEquals(1, tree.LeafCount());
    }

    @Test
    @DisplayName("Добавление дочернего элеиента в root. child.parent == null")
    void addChildToRoot() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null); // у корневого элемента нет родителя
        SimpleTree<String> tree = new SimpleTree<>(root);

        SimpleTreeNode<String> newChild = new SimpleTreeNode<>("child", null);

        tree.AddChild(tree.Root, newChild);

        assertTrue(root.Children.contains(newChild));
        assertEquals(1, tree.Count());
        assertEquals(1, tree.Root.Children.size());
        assertEquals(tree.Root, newChild.Parent);
        assertNull(newChild.Children);
    }

    @Test
    @DisplayName("Удаление корневого узла")
    void deleteRootNode() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));
        tree.DeleteNode(tree.Root);

        assertEquals(0,tree.Count());
    }

    @Test
    @DisplayName("Удаление некорневого узла")
    void deleteNode() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        tree.AddChild(secondRootChild, new SimpleTreeNode<>("secondRootChild child", null));
        tree.AddChild(secondRootChild, new SimpleTreeNode<>("secondRootChild child", null));

        tree.DeleteNode(secondRootChild);

        assertEquals(1,tree.Count());
    }



    @Test
    @DisplayName("Количество листов в пустом дереве")
    void emptyTreeLeafCount() {
        SimpleTree<String> tree = new SimpleTree<>(null);
        assertEquals(0, tree.LeafCount());
    }

    @Test
    @DisplayName("Количество листов в дереве, содержащем только корень")
    void onlyRootLeafCount() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTree<String> tree = new SimpleTree<>(root);
        assertEquals(1, tree.LeafCount());
        assertEquals(1, tree.Count());
    }

    @Test
    @DisplayName("Количество листов в произвольном дереве")
    void leafCount() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));
        assertEquals(2, tree.LeafCount());
    }

    @Test
    @DisplayName("Поиск по значению. Нет искомых узлов")
    void emptyListOfNodes() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));

        List<SimpleTreeNode<String>> list = tree.FindNodesByValue("lol");

        assertEquals(0, list.size());
    }

    @Test
    @DisplayName("Поиск по значению. Есть 2 подходящих узла")
    void foundNodesExists() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));
        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));

        List<SimpleTreeNode<String>> list = tree.FindNodesByValue("one more child");

        assertEquals(2, list.size());
    }

    @Test
    @DisplayName("Список узлов")
    void getListNodes() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));
        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));

        List<SimpleTreeNode<String>> list = tree.GetAllNodes();

        assertEquals(2,tree.Count());
        assertNotNull(list);
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Перемещение узла")
    void moveNode() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));
        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));

        assertEquals(3, tree.LeafCount());
        assertEquals(2, tree.Count());

        tree.MoveNode(firstRootChild, secondRootChild);

        assertEquals(3, tree.LeafCount());
        assertEquals(2, tree.Count());

    }

    @Test
    @DisplayName("Перемещение узла с изменением количества leaf")
    void moveNode2() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", root); // не увеличивается счетчик узлов. как исправить?
        SimpleTreeNode<String> secondRootChild = new SimpleTreeNode<>("secondRootChild", root);

        SimpleTreeNode<String> oneMoreChild = new SimpleTreeNode<>("one more child", null);
        tree.AddChild(secondRootChild, oneMoreChild);
        tree.AddChild(secondRootChild, new SimpleTreeNode<>("one more child", null));

        assertEquals(3, tree.LeafCount());
        assertEquals(2, tree.Count());

        tree.MoveNode(firstRootChild, oneMoreChild);

        assertEquals(2, tree.LeafCount());
        assertEquals(3, tree.Count());

    }

    @Test
    @DisplayName("Размер дерева из двух элементов")
    void countOfTwo() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTreeNode<String> firstRootChild = new SimpleTreeNode<>("firstRootChild", null);

        SimpleTree<String> tree = new SimpleTree<>(root);
        tree.AddChild(root, firstRootChild);

        assertEquals(1, tree.Count());

    }

    @Test
    @DisplayName("Количество узлов в дереве из одного элемента")
    void countOnlyRoot() {
        SimpleTreeNode<String> root = new SimpleTreeNode<>("root", null);
        SimpleTree<String> tree = new SimpleTree<>(root);

        int count = tree.Count();

        assertEquals(1, count);
    }


}