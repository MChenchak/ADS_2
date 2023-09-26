import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SimpleTreeTest {

    @Test
    void even() {

        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> two = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> three = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> four = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> five = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> six = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> seven = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> eight = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> nine = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> ten = new SimpleTreeNode<>(10, null);

        SimpleTree<Integer> tree = new SimpleTree<>(root);

        tree.AddChild(root, two);
        tree.AddChild(root, three);
        tree.AddChild(root, six);

        tree.AddChild(three, four);

        tree.AddChild(three, five);
        tree.AddChild(three, seven);

        tree.AddChild(six, eight);
        tree.AddChild(eight, nine);
        tree.AddChild(eight, ten);

        ArrayList<Integer> result = tree.EvenTrees();

    }

}