import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class HeapTest {

    @Test
    void add() {
        Heap heap = new Heap();
        heap.HeapArray = new int[15];

        heap.Add(5);
        assertEquals(heap.HeapArray[0], 5);

        heap.Add(10);
        assertEquals(heap.HeapArray[0], 10);
        assertEquals(heap.HeapArray[1], 5);

        heap.Add(7);
        assertEquals(heap.HeapArray[0], 10);
        assertEquals(heap.HeapArray[1], 5);
        assertEquals(heap.HeapArray[2], 7);

        heap.Add(11);
        assertEquals(heap.HeapArray[0], 11);
        assertEquals(heap.HeapArray[1], 10);
        assertEquals(heap.HeapArray[2], 7);
        assertEquals(heap.HeapArray[3], 5);
    }

    @Test
    void makeHeap() {
        int[] a = {5, 10, 7, 11};
        Heap heap = new Heap();

        heap.MakeHeap(a,1);

        assertEquals(heap.HeapArray[0], 10);
        assertEquals(heap.HeapArray[1], 5);
        assertEquals(heap.HeapArray[2], 7);
    }

    @Test
    void remove() {
        int[] a = {5, 10, 7, 11};
        Heap heap = new Heap();

        heap.MakeHeap(a,3);

        int max = heap.GetMax();

        assertEquals(11, max);
    }
  
}