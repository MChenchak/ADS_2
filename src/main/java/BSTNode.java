import java.util.Arrays;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        // создаём дерево с нуля из неотсортированного массива a
        if (a.length == 0) return;
        int[] sorted = Arrays.stream(a).sorted().toArray();
        this.Root = generateTreeRec(null, sorted);
    }

    public BSTNode generateTreeRec(BSTNode parent, int[] subArr) {
        if (subArr == null || subArr.length == 0) return null;

        int midIndex = (subArr.length - 1) / 2;

        int[] leftSubArr = getSubArray(subArr, 0, midIndex);
        int[] rightSubArr = getSubArray(subArr, midIndex + 1, subArr.length);

        BSTNode node = new BSTNode(subArr[midIndex], parent);
        node.Level = node.Parent == null ? 0 : node.Parent.Level + 1;

        node.LeftChild = generateTreeRec(node, leftSubArr);
        node.RightChild = generateTreeRec(node, rightSubArr);
        return node;
    }

    private int[] getSubArray(int[] source, int startIndex, int endIndex) {
        if (startIndex > endIndex) return null;
        return Arrays.copyOfRange(source, startIndex, endIndex);
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) return true;

        int lh = getHeight(root_node.LeftChild);
        int rh = getHeight(root_node.RightChild);

        return Math.abs(lh - rh) <= 1 && IsBalanced(root_node.LeftChild) && IsBalanced(root_node.RightChild);// сбалансировано ли дерево с корнем root_node
    }

    private int getHeight(BSTNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.LeftChild), getHeight(node.RightChild));
    }
}
