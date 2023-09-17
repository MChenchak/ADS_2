import java.util.Arrays;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray(int[] a) {
        if (a.length == 0) return null;
        int[] sorted = Arrays.stream(a).sorted().toArray();
        int[] bbstArr = new int[sorted.length];

        arrayToBSTarrayRec(bbstArr, sorted, 0, sorted.length - 1, 0);

        return bbstArr;
    }

    private static void arrayToBSTarrayRec(int[] bstArr, int[] arr, int start, int end, int index) {
        if (start > end) return;
        if (index > arr.length) return;

        int middleIndex = (start + end) / 2;
        bstArr[index] = arr[middleIndex];

        arrayToBSTarrayRec(bstArr, arr, start, middleIndex - 1, 2 * index + 1);
        arrayToBSTarrayRec(bstArr, arr, middleIndex + 1, end, 2 * index + 2);

    }
}
