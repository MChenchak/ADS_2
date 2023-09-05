package simple_tree;

import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;

        if (parent == null) return;

        if (parent.Children == null) {
            parent.Children = new ArrayList<>(
            ) {{
                add(SimpleTreeNode.this);
            }};
        } else {
            parent.Children.add(SimpleTreeNode.this);
        }

    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    private boolean isLeaf(SimpleTreeNode<T> node) {
        return node.Children == null || node.Children.size() == 0;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        // ваш код удаления существующего узла NodeToDelete
        if (!existsNode(NodeToDelete, this.Root)) return;

        if (NodeToDelete.equals(this.Root)) {
            this.Root = null;
            return;
        }

        if (isLeaf(NodeToDelete)) {
            NodeToDelete.Parent = null;
        } else {
            NodeToDelete.Parent.Children.remove(NodeToDelete);
            NodeToDelete.Parent = null;
        }


    }

    private boolean existsNode(SimpleTreeNode<T> NodeToDelete, SimpleTreeNode<T> treeNode) {
        if (NodeToDelete == null) return false;
        if (NodeToDelete.equals(treeNode)) return true;

        boolean result = false;
        if (treeNode.Children != null && treeNode.Children.size() > 0) {
            for (SimpleTreeNode<T> stn : treeNode.Children) {
                result = existsNode(NodeToDelete, stn);
            }
        }

        return result;
    }


    public List<SimpleTreeNode<T>> GetAllNodes() {
        if (this.Root == null) return new ArrayList<>();
        return collect(this.Root);
    }

    private List<SimpleTreeNode<T>> collect(SimpleTreeNode<T> node) {
        List<SimpleTreeNode<T>> list = new ArrayList<>() {{add(node);}};

        if (isLeaf(node)) return list;

        for(SimpleTreeNode<T> child : node.Children) {
            list.addAll(collect(child));
        }

        return list;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        return containsNodeRecursive(this.Root, val);
    }

    private List<SimpleTreeNode<T>> containsNodeRecursive(SimpleTreeNode<T> node, T value) {
        List<SimpleTreeNode<T>> list = new ArrayList<>();
        if (node == null) return list;

        if (node.NodeValue.equals(value)) {
            list.add(node);
            return list;
        }

        if (isLeaf(node)) return list;

        for (SimpleTreeNode<T> n : node.Children) {
            list.addAll(containsNodeRecursive(n, value));
        }

        return list;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        if (OriginalNode == null || NewParent == null) return;

        OriginalNode.Parent.Children.remove(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        return countSubTreeNodes(this.Root);
    }

    public int LeafCount() {
        return getLeafCount(this.Root);
    }

    private int countSubTreeNodes(SimpleTreeNode<T> node) {
        if (node == null) return 0;
        if (isLeaf(node)) return 1;

        int count = 1;

        for (SimpleTreeNode<T> n : node.Children) {
            count += countSubTreeNodes(n);
        }

        return count;
    }

    private int getLeafCount(SimpleTreeNode<T> node) {
        if (node == null) return 0;
        if (isLeaf(node)) return 1;

        int leafCount = 0;
        for (SimpleTreeNode<T> n : node.Children) {
            leafCount += getLeafCount(n);
        }
        return leafCount;
    }
}