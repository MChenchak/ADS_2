import java.util.ArrayList;
import java.util.List;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public ArrayList<T> EvenTrees() {
        return makeEven(Root, new ArrayList<T>());
    }

    private ArrayList<T> makeEven(SimpleTreeNode<T> node, ArrayList<T> intList) {
        if (node == null) return intList;
        if (isLeaf(node)) return intList;

        for (SimpleTreeNode<T> n : node.Children) {
            if (countSubtreeNodes(n) % 2 == 0) {
                 intList.add(node.NodeValue);
                 intList.add(n.NodeValue);
            }
            makeEven(n, intList);

        }
        return intList;
    }

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
        // ваш код добавления нового дочернего узла существующему ParentNode
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (!exists(NodeToDelete, this.Root)) return;
        if (NodeToDelete.equals(this.Root)) {
            this.Root = null;
            return;
        }

        NodeToDelete.Parent.Children.remove(NodeToDelete);
        NodeToDelete.Parent = null;
    }

    private boolean exists(SimpleTreeNode<T> NodeToDelete, SimpleTreeNode<T> root) {
        if (NodeToDelete == null) return false;
        if (NodeToDelete.equals(root)) return true;

        boolean result = false;
        if (root.Children != null && root.Children.size() > 0) {
            for (SimpleTreeNode<T> stn : root.Children) {
                result = exists(NodeToDelete, stn);
            }
        }

        return result;
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        if (this.Root == null) return null;
        return collect(this.Root);
    }

    private List<SimpleTreeNode<T>> collect(SimpleTreeNode<T> node) {
        List<SimpleTreeNode<T>> list = new ArrayList<>();

        list.add(node);

        if (node.Children == null || node.Children.size() == 0) return list;

        for (SimpleTreeNode<T> child : node.Children) {
            list.addAll(collect(child));
        }

        return list;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        List<SimpleTreeNode<T>> list = findRec(this.Root, val);
        return list.size() == 0 ? null : list;
    }

    private List<SimpleTreeNode<T>> findRec(SimpleTreeNode<T> current, T val) {
        List<SimpleTreeNode<T>> list = new ArrayList<>();
        if (current == null) return list;

        if (current.NodeValue.equals(val)) {
            list.add(current);
        }

        if (isLeaf(current)) return list;

        for (SimpleTreeNode<T> n : current.Children) {
            list.addAll(findRec(n, val));
        }

        return list;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        if (OriginalNode == null || NewParent == null) return;

        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(NewParent, OriginalNode);
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
    }

    public int Count() {
        if (this.Root == null) return 0;
        if (isLeaf(this.Root)) return 1;
        // количество всех узлов в дереве
        return countRec(this.Root);
    }

    private int countSubtreeNodes(SimpleTreeNode<T> node) {
        if (node == null) return 0;
        if (isLeaf(node)) return 1;
        return countRec(node);
    }

    private int countRec(SimpleTreeNode<T> current) {
        int count = 1;

        if (isLeaf(current)) return 1;

        for (SimpleTreeNode<T> n : current.Children) {
            count += countRec(n);
        }
        return count;
    }

    public int LeafCount() {
        // количество листьев в дереве
        return leafCountRec(this.Root);
    }

    private int leafCountRec(SimpleTreeNode<T> current) {
        if (isLeaf(current)) return 1;

        int leafCount = 0;
        for (SimpleTreeNode<T> n : current.Children) {
            leafCount += leafCountRec(n);
        }
        return leafCount;
    }

    private boolean isLeaf(SimpleTreeNode<T> node) {
        return node.Children == null || node.Children.size() == 0;
    }

}
