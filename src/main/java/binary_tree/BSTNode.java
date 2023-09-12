package binary_tree;

import java.util.*;

class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }

    public int getSize() {
        int leftSize = 0;
        int rightSize = 0;

        if (this.LeftChild != null)
            leftSize = this.LeftChild.getSize();
        if (this.RightChild != null)
            rightSize = this.RightChild.getSize();

        return 1 + leftSize + rightSize;

    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        // ищем в дереве узел и сопутствующую информацию по ключу
        BSTNode<T> found = findRec(this.Root, key);
        BSTFind<T> result = new BSTFind<>();

        result.Node = findRec(this.Root, key);
        result.NodeHasKey = key == found.NodeKey;
        result.ToLeft = key < found.NodeKey;

        return result;
    }

    private BSTNode<T> findRec(BSTNode<T> current, int key) {
        if (current == null) return current;
        if (current.NodeKey == key) return current;

        if (key < current.NodeKey) {
            if (current.LeftChild == null) return current;
            return findRec(current.LeftChild, key);
        } else {
            if (current.RightChild == null) return current;
            return findRec(current.RightChild, key);
        }
    }

    public boolean AddKeyValue(int key, T val) {
        if (this.Root == null) {
            this.Root = new BSTNode<>(key, val, null);
            return true;
        }

        BSTFind<T> found = FindNodeByKey(key);
        // добавляем ключ-значение в дерево
        if (!found.NodeHasKey) {
            BSTNode<T> parent = found.Node;
            BSTNode<T> newNode = new BSTNode<>(key, val, parent);
            if (found.ToLeft) {
                parent.LeftChild = newNode;
            } else {
                parent.RightChild = newNode;
            }
            return true;
        }
        return false; // если ключ уже есть
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        // ищем максимальный/минимальный ключ в поддереве
        return FindMax ? findMaxRec(FromNode) : findMinRec(FromNode);
    }

    private BSTNode<T> findMinRec(BSTNode<T> current) {
        if (current.LeftChild == null) return current;
        return findMinRec(current.LeftChild);
    }

    private BSTNode<T> findMaxRec(BSTNode<T> current) {
        if (current.RightChild == null) return current;
        return findMaxRec(current.RightChild);
    }

    public boolean DeleteNodeByKey(int key) {
        BSTFind<T> found = FindNodeByKey(key);
        if (!found.NodeHasKey) return false; // если узел не найден

        BSTNode<T> toDelete = found.Node;
        this.Root = deleteRecursive(this.Root, key);
        return true;

    }

    private BSTNode<T> deleteRecursive(BSTNode<T> current, int key) {
        if (current == null) return current;

        if (key == current.NodeKey) {
            if (isLeaf(current)) return null;

            if (current.RightChild == null) {
                current.LeftChild.Parent = current.Parent;
                current.Parent = null;
                return current.LeftChild;
            }

            if (current.LeftChild == null) {
                current.RightChild.Parent = current.Parent;
                current.Parent = null;
                return current.RightChild;
            }

            BSTNode<T> successor = this.FinMinMax(current.RightChild, false);

            current.NodeKey = successor.NodeKey;
            current.NodeValue = successor.NodeValue;
            current.RightChild = deleteRecursive(current.RightChild, successor.NodeKey);

            return current;
        }

        if (key < current.NodeKey) {
            current.LeftChild = deleteRecursive(current.LeftChild, key);
            return current;
        } else {
            current.RightChild = deleteRecursive(current.RightChild, key);
        }
        return current;

    }

    private boolean isLeaf(BSTNode<T> node) {
        return node.RightChild == null && node.LeftChild == null;
    }

    private boolean hasOnlyOneChild(BSTNode<T> node) {
        return node.RightChild == null || node.LeftChild == null;
    }

    public int Count() {
        if (this.Root != null)
            return Root.getSize();
        else
            return 0; // количество узлов в дереве
    }

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> list = new ArrayList<>();

        if (this.Root == null) {
            return list;
        }

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(this.Root);

        while (queue.size() > 0) {
            BSTNode currentNode = queue.poll();
            list.add(currentNode);
            if (currentNode.LeftChild != null)
                queue.add(currentNode.LeftChild);

            if (currentNode.RightChild != null)
                queue.add(currentNode.RightChild);
        }
        return list;
    }

    public ArrayList<BSTNode> DeepAllNodes(int orderNumber) {
        ArrayList<BSTNode> res = new ArrayList<>();

        if (this.Root == null) return res;

        if (orderNumber == 0) inOrder(this.Root, res);

        if (orderNumber == 1) postOrder(this.Root, res);

        if (orderNumber == 2) preOrder(this.Root, res);

        return res;
    }

    private void inOrder(BSTNode current, List<BSTNode> res) {
        if (current == null) return;

        inOrder(current.LeftChild, res);
        res.add(current);
        inOrder(current.RightChild, res);
    }

    private void postOrder(BSTNode current, List<BSTNode> res) {
        if (current == null) return;

        postOrder(current.LeftChild, res);
        postOrder(current.RightChild, res);
        res.add(current);
    }

    private void preOrder(BSTNode current, List<BSTNode> res) {
        if (current == null) return;

        res.add(current);
        preOrder(current.LeftChild, res);
        preOrder(current.RightChild, res);
    }
}
