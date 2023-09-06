package binary_tree;

class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок
    public boolean isLeft;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;

        if (parent != null && key <= parent.NodeKey) {
            parent.LeftChild = this;
            isLeft = true;
        } else if (parent != null && key > parent.NodeKey) {
            parent.RightChild = this;
            isLeft = false;
        }
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
        return findNodeByKeyRec(this.Root, null, key, false);
    }

    private BSTFind<T> findNodeByKeyRec(BSTNode<T> current, BSTNode<T> parent, int key, boolean toLeft) {
        BSTFind<T> found = new BSTFind<>();
        if (current == null) {
            found.Node = parent;
            found.NodeHasKey = false;
            found.ToLeft = toLeft;
            return found;
        }

        if (current.NodeKey == key) {
            found.Node = current;
            found.NodeHasKey = true;
            found.ToLeft = false;
            return found;
        }

        return key < current.NodeKey ?
                findNodeByKeyRec(current.LeftChild, current, key, true) :
                findNodeByKeyRec(current.RightChild, current, key, false);
    }

    public boolean AddKeyValue(int key, T val) {
        // добавляем ключ-значение в дерево
        BSTFind<T> found = FindNodeByKey(key);
        if (!found.NodeHasKey) {
            BSTNode<T> parent = found.Node;
            BSTNode<T> newNode = new BSTNode<>(key, val, parent);
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
        return findMinRec(current.RightChild);
    }

    private boolean isLeaf(BSTNode<T> node) {
        return node.LeftChild == null && node.RightChild == null;
    }

    public boolean DeleteNodeByKey(int key) {
        // удаляем узел по ключу
        BSTFind<T> found = FindNodeByKey(key);

        //Если искомого узла нет в дереве
        if (!found.NodeHasKey) return false;

        BSTNode<T> toDelete = found.Node;

        // Eсли удаляемый узел - лист
        if (isLeaf(toDelete)) {
            if (toDelete.equals(toDelete.Parent.LeftChild)) {
                toDelete.Parent.LeftChild = null;
            } else {
                toDelete.Parent.RightChild = null;
            }
            toDelete.Parent = null;
            return true;
        }

        BSTNode<T> newNode = findNewNode(toDelete, key);
        // если только один потомок
        if (toDelete.LeftChild == null || toDelete.RightChild == null) {
            newNode.Parent = toDelete.Parent;

            if (toDelete.equals(toDelete.Parent.LeftChild)) {
                toDelete.Parent.LeftChild = newNode;
                return true;
            } else if (toDelete.equals(toDelete.Parent.RightChild)) {
                toDelete.Parent.RightChild = newNode;
            }

            toDelete.Parent = null;
            toDelete.RightChild = null;
            toDelete.LeftChild = null;
            return true;
        }

        if (toDelete.isLeft) {
            toDelete.Parent.LeftChild = newNode;
            newNode.Parent.LeftChild = null;
        } else {
            toDelete.Parent.RightChild = newNode;
        }

        newNode.isLeft = toDelete.isLeft;
        newNode.Parent = toDelete.Parent;
        newNode.RightChild = toDelete.RightChild;
        newNode.LeftChild = toDelete.LeftChild;

        newNode.RightChild.Parent = newNode;
        newNode.LeftChild.Parent = newNode;

        toDelete.Parent = null;
        toDelete.LeftChild = null;
        toDelete.RightChild = null;

        return true;

    }

    private BSTNode<T> findNewNode(BSTNode<T> current, int key) {
        if (current.LeftChild == null) {
            return current.RightChild;
        }

        if (current.RightChild == null) {
            return current.LeftChild;
        }

        return FinMinMax(current.RightChild, false);
    }

    public int Count() {
        return 0; // количество узлов в дереве
    }

}
