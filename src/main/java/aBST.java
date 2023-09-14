class aBST {
    public Integer Tree[]; // массив ключей
    public int depth;

    public aBST(int depth) {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = countTreeSize(depth);
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
        this.depth = depth;
    }

    public Integer FindKeyIndex(int key) {
        if (Tree[0] == null) return 0;
        if (Tree[0] == key) return 0;

        return findKeyRec(0, key, 0); // не найден
    }

    private Integer findKeyRec(int index, int key, int level) {
        if (level > depth) return null;
        if (Tree[index] == null) return -1 * index;
        if (Tree[index] == key) return index;

        if (key < Tree[index]) {
            int leftChildIndex = calcIndex(index, true);

            return findKeyRec(leftChildIndex, key, ++level);
        }

        if (key > Tree[index]) {
            int rightChildIndex = calcIndex(index, false);

            return findKeyRec(rightChildIndex, key, ++level);
        }
        return 0;
    }

    public int AddKey(int key) {
        Integer keyIndex = FindKeyIndex(key);

        if (keyIndex == null || keyIndex > 0) return -1;

        this.Tree[-keyIndex] = key;
        return -keyIndex;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }

    private int countLevelNodes(int treeDepth) {
        if (treeDepth == 0) return 1;
        return 2 * countLevelNodes(treeDepth - 1);
    }

    private int countTreeSize(int treeDepth) {
        if (treeDepth == 0) return 1;
//        if (treeDepth == 1) return 1;

        int size = countLevelNodes(treeDepth);
        return size + countTreeSize(treeDepth - 1);

    }

    private int calcIndex(int index, boolean isLeft) {
        return isLeft ? 2 * index + 1 :
                        2 * index + 2;
    }

}
