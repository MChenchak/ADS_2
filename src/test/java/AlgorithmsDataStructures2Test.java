import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {

    @Test
    void sortArray() {
        int[] notSortedArr = {9, 25, 8, 36, 17, 11, 14, 2, 3, 4, 1, 5, 6, 7, 0};

        int[] balancedArr = AlgorithmsDataStructures2.GenerateBBSTArray(notSortedArr);

        assertNotNull(balancedArr);
        assertEquals(8, balancedArr[36]);

    }

}