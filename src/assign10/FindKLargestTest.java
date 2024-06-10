package assign10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * This is a testing class for the FindKLargest class we constructed from scratch.
 *
 * @author Janne Wald & Shawn Zang.
 * @version April 9, 2024.
 */
public class FindKLargestTest {

    @Test
    public void testFindKLargestHeap_NaturalOrder() {
        List<Integer> list = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        List<Integer> expected = Arrays.asList(9, 6, 5, 5, 5);
        assertEquals(expected, FindKLargest.findKLargestHeap(list, 5));
    }
    
    @Test
    public void testFindKLargestHeap_KEqualsListSize() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(expected, FindKLargest.findKLargestHeap(list, 5));
    }

    @Test
    public void testFindKLargestHeap_KGreaterThanListSize() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 4));
    }

    @Test
    public void testFindKLargestHeap_KIsZero() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, FindKLargest.findKLargestHeap(list, 0));
    }

    @Test
    public void testFindKLargestHeap_EmptyList() {
        List<Integer> list = Arrays.asList();
        assertThrows(IllegalArgumentException.class, () -> FindKLargest.findKLargestHeap(list, 1));
    }
}
