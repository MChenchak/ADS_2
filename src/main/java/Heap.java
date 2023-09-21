class Heap {
    public int[] HeapArray; // хранит неотрицательные числа-ключи
    public int size;

    public Heap() {
        HeapArray = null;
        size = 0;
    }

    public void MakeHeap(int[] a, int depth) {
        // создаём массив кучи HeapArray из заданного
        // размер массива выбираем на основе глубины depth
        if (a == null || depth < 0) return;

        int length = countArrayLength(depth);
        HeapArray = new int[length];

        for (int i : a) {
            boolean added = Add(i);
            if (!added) return;
        }
    }

    public int GetMax() {
        if (size == 0) return -1; // если куча пуста

        int rootKey = HeapArray[0];
        HeapArray[0] = HeapArray[size-1];
        HeapArray[size-1] = 0;
        size--;
        if (size > 1)
            shiftDown(0);
        return rootKey;
    }

    private void shiftDown(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        int maxIndex = index;

        if(hasLeft(index) && HeapArray[leftChildIndex] > HeapArray[maxIndex])
            maxIndex = leftChildIndex;
        if(hasRight(index) && HeapArray[rightChildIndex] > HeapArray[maxIndex])
            maxIndex = rightChildIndex;

        if (maxIndex != index) {
            swap(index, maxIndex);
            shiftDown(maxIndex);
        }
    }

    public boolean Add(int key) {
        if (size == HeapArray.length) return false; // если куча вся заполнена
        size++;

        // первый свободный слот
        int slot = size - 1;
        HeapArray[slot] = key;
        shiftUp(slot);

        return true;
    }

    private void shiftUp(int index) {
        if (index == 0) return;

        int parentIndex = getParentIndex(index);
        if (HeapArray[parentIndex] < HeapArray[index]) {
            swap(parentIndex, index);
            shiftUp(parentIndex);
        }
    }


    private void swap(int parentIndex, int index) {
        int temp = HeapArray[parentIndex];
        HeapArray[parentIndex] = HeapArray[index];
        HeapArray[index] = temp;
    }


    private int countArrayLength(int depth) {
        return (int) Math.pow(2, depth + 1) - 1;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private boolean hasLeft(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRight(int index) {
        return getRightChildIndex(index) < size;
    }

}