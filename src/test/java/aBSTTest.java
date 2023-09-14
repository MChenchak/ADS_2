import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class aBSTTest {

    @Test
    @DisplayName("Создание дерева. depth = 3")
    void createTree() {
        aBST tree = new aBST(3);

        assertEquals(7, tree.Tree.length);
    }

    @Test
    @DisplayName("Поиск ключа в пустом дереве")
    void addKeys() {
        aBST tree = new aBST(3);
        assertEquals(7, tree.Tree.length);

        Integer result = tree.FindKeyIndex(12);

        assertEquals(0, result);
    }

    @Test
    @DisplayName("Поиск существующего ключа в дереве из одного элемента")
    void existKey() {
        aBST tree = new aBST(3);
        assertEquals(7, tree.Tree.length);

        tree.Tree[0] = 12;

        Integer result = tree.FindKeyIndex(12);

        assertEquals(0, result);
    }

    @Test
    @DisplayName("Поиск несуществующего ключа в дереве из одного элемента")
    void notExistKey() {
        aBST tree = new aBST(3);
        assertEquals(7, tree.Tree.length);

        tree.Tree[0] = 12;

        Integer leftResult = tree.FindKeyIndex(8);
        Integer rightResult = tree.FindKeyIndex(80);

        assertEquals(-1, leftResult);
        assertEquals(-2, rightResult);
    }

    @Test
    @DisplayName("Поиск несуществующего ключа, который должен быть расположен глубже")
    void notExistKeyDeeper() {
        aBST tree = new aBST(1);
        assertEquals(1, tree.Tree.length);

        tree.Tree[0] = 12;

        Integer leftResult = tree.FindKeyIndex(8);

        assertNull(leftResult);
    }

    @Test
    @DisplayName("add key to empty tree")
    void addToEmpty() {
        aBST tree = new aBST(3);
        assertEquals(7, tree.Tree.length);

        int i = tree.AddKey(12);

        assertEquals(0, i);
        assertEquals(12, tree.Tree[0]);
    }

    @Test
    @DisplayName("add the same key twice")
    void addSameKeyTwice() {
        aBST tree = new aBST(3);
        assertEquals(7, tree.Tree.length);

        tree.AddKey(12);
        int i = tree.AddKey(12);

        assertEquals(0, i);
        assertEquals(12, tree.Tree[0]);
    }

    @Test
    @DisplayName("add key to full tree")
    void addKeyToFullTree() {
        aBST tree = new aBST(2);
        assertEquals(3, tree.Tree.length);

        int i1 = tree.AddKey(12);
        assertEquals(0, i1);


        int i2 = tree.AddKey(10);
        assertEquals(1, i2);

        int i3 = tree.AddKey(15);
        assertEquals(2, i3);

        int i4 = tree.AddKey(121);


        assertEquals(-1, i4);
        assertEquals(12, tree.Tree[0]);
    }


    @Test
    @DisplayName("add keys2")
    void addKeys2() {
        aBST tree = new aBST(4);
        assertEquals(15, tree.Tree.length);

        int i1 = tree.AddKey(50);
        assertEquals(0, i1);

        int i2 = tree.AddKey(25);
        assertEquals(1, i2);

        int i3 = tree.AddKey(75);
        assertEquals(2, i3);

        int i4 = tree.AddKey(37);
        assertEquals(4, i4);

        int i5 = tree.AddKey(84);
        assertEquals(6, i5);

        int i6 = tree.AddKey(20);
        assertEquals(3, i6);

        int i7 = tree.AddKey(43);
        assertEquals(10, i7);

        int i8 = tree.AddKey(45);
        assertEquals(-1, i8);

    }
}