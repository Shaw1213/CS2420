package assign03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SimplePriorityQueueTest {
    SimplePriorityQueue<Integer> queue;
    @BeforeEach
    void setUp() {
        queue = new SimplePriorityQueue<>();
    }

    /**
     * Test for findMax
     */
    @Test
    void findMax() {
        //normal case
        queue.insert(5);
        assertEquals(5, queue.findMax());

        queue.insert(10);
        assertEquals(10, queue.findMax());
    }

    @Test
    void findMaxMultiple() {
        //multiple elements
        queue.insert(5);
        queue.insert(10);
        queue.insert(15);
        assertEquals(15, queue.findMax());
    }

    @Test
    void findMaxNegative() {
        //negative case
        queue.insert(-5);
        assertEquals(-5, queue.findMax());
    }

    @Test
    void findMaxEmpty() {
        //empty case
        assertThrows(NoSuchElementException.class, () -> queue.findMax());
    }

    @Test
    void findMaxEqual() {
        //equal case
        queue.insert(5);
        queue.insert(5);
        assertEquals(5, queue.findMax());
    }

    @Test
    void findMaxLarge() {
        //large case
        queue.insert(1000000000);
        assertEquals(1000000000, queue.findMax());
    }

    @Test
    void findMaxLargeNegative() {
        //large negative case
        queue.insert(-1000000000);
        assertEquals(-1000000000, queue.findMax());
    }

    @Test
    void findMaxLargeNegativeMultiple() {
        //large negative case
        queue.insert(-1000000000);
        queue.insert(-1000000001);
        queue.insert(-1000000002);
        assertEquals(-1000000000, queue.findMax());
    }

    @Test
    void findMaxLargePositiveMultiple() {
        //large negative case
        queue.insert(1000000000);
        queue.insert(1000000001);
        queue.insert(1000000002);
        assertEquals(1000000002, queue.findMax());
    }

    /**
     * Test for deleteMax
     */
    @Test
    void deleteMax() {
        //normal case
        queue.insert(5);
        queue.insert(10);
        queue.insert(15);
        assertEquals(15, queue.deleteMax());
    }

    @Test
    void deleteMaxEmpty() {
        //empty case
        assertThrows(NoSuchElementException.class, () -> queue.deleteMax());
    }

    @Test
    void deleteMaxEqual() {
        //equal case
        queue.insert(5);
        queue.insert(5);
        assertEquals(5, queue.deleteMax());
    }

    @Test
    void deleteMaxLarge() {
        //large case
        queue.insert(1000000000);
        assertEquals(1000000000, queue.deleteMax());
    }

    @Test
    void deleteMaxLargeNegative() {
        //large negative case
        queue.insert(-1000000000);
        assertEquals(-1000000000, queue.deleteMax());
    }

    @Test
    void deleteMaxLargeNegativeMultiple() {
        //large negative case
        queue.insert(-1000000000);
        queue.insert(-1000000001);
        queue.insert(-1000000002);
        assertEquals(-1000000000, queue.deleteMax());
    }

    @Test
    void deleteMaxLargePositiveMultiple() {
        //large negative case
        queue.insert(1000000000);
        queue.insert(1000000001);
        queue.insert(1000000002);
        assertEquals(1000000002, queue.deleteMax());
    }

    /**
     * Test for insert
     */
    @Test
    void insert() {
        //normal case
        queue.insert(5);
        queue.insert(10);
        assertEquals(10, queue.findMax());
    }

    @Test
    void insertNullThrowsException() {
        //null case

        assertThrows(NullPointerException.class, () -> queue.insert(null));
    }

    @Test
    void insertNegative() {
        //negative case
        queue.insert(-5);
        assertEquals(-5, queue.findMax());
    }

    @Test
    void insertEqual() {
        //equal case
        queue.insert(5);
        queue.insert(5);
        assertEquals(5, queue.findMax());
    }

    @Test
    void insertLarge() {
        //large case
        queue.insert(1000000000);
        assertEquals(1000000000, queue.findMax());
    }

    @Test
    void insertLargeNegative() {
        //large negative case
        queue.insert(-1000000000);
        assertEquals(-1000000000, queue.findMax());
    }
    @Test
    void insertLargeNegativeMultiple() {
        //large negative case
        queue.insert(-1000000000);
        queue.insert(-1000000001);
        queue.insert(-1000000002);
        assertEquals(-1000000000, queue.findMax());
    }

    @Test
    void insertLargePositiveMultiple() {
        //large negative case
        queue.insert(1000000000);
        queue.insert(1000000001);
        queue.insert(1000000002);
        assertEquals(1000000002, queue.findMax());
    }

    /**
     * Test for insertAll
     */
    @Test
    void insertAll() {
        //normal case
        queue.insertAll(java.util.List.of(5, 10, 15));
        assertEquals(15, queue.findMax());
    }

    @Test
    void insertAllNullThrowsException() {
        //null case
        assertThrows(NullPointerException.class, () -> queue.insertAll(java.util.List.of(null, null, null)));
    }

    @Test
    void insertAllNegative() {
        //negative case
        queue.insertAll(java.util.List.of(-5, -10, -15));
        assertEquals(-5, queue.findMax());
    }

    @Test
    void insertAllEqual() {
        //equal case
        queue.insertAll(java.util.List.of(5, 5, 5));
        assertEquals(5, queue.findMax());
    }

    @Test
    void insertAllLarge() {
        //large case
        queue.insertAll(java.util.List.of(1000000000, 1000000001, 1000000002));
        assertEquals(1000000002, queue.findMax());
    }

    @Test
    void insertAllLargeNegative() {
        //large negative case
        queue.insertAll(java.util.List.of(-1000000000, -1000000001, -1000000002));
        assertEquals(-1000000000, queue.findMax());
    }

    /**
     * Test for contains
     */
    @Test
    void contains() {
        //normal case
        queue.insert(5);
        queue.insert(10);
        assertTrue(queue.contains(5));
    }

    @Test
    void containsNullThrowsException() {
        //null case
        assertThrows(NullPointerException.class, () -> queue.contains(null));
    }

    @Test
    void containsNegative() {
        //negative case
        queue.insert(-5);
        assertTrue(queue.contains(-5));
    }

    @Test
    void containsEqual() {
        //equal case
        queue.insert(5);
        queue.insert(5);
        assertTrue(queue.contains(5));
    }

    @Test
    void containsLarge() {
        //large case
        queue.insert(1000000000);
        assertTrue(queue.contains(1000000000));
    }

    @Test
    void containsLargeNegative() {
        //large negative case
        queue.insert(-1000000000);
        assertTrue(queue.contains(-1000000000));
    }

    /**
     * Test for size
     */
    @Test
    void size() {
        //normal case
        queue.insert(5);
        queue.insert(10);
        assertEquals(2, queue.size());
    }

    @Test
    void sizeEmpty() {
        //empty case
        assertEquals(0, queue.size());
    }

    @Test
    void sizeZero() {
        //Zero case
        queue.insert(5);
        queue.deleteMax();
        assertEquals(0, queue.size());

    }

    @Test
    void sizeLarge() {
        //large case
        for(int i = 0; i < 9999; i++) {
            queue.insert(i);
        }
        assertEquals(9999, queue.size());
    }

    @Test
    void sizeLargeNegative() {
        //large negative case
        for(int i = 0; i < 9999; i++) {
            queue.insert(-i);
        }
        assertEquals(9999, queue.size());
    }

    /**
     * Test for isEmpty
     */
    @Test
    void isEmpty() {
        //False case
        queue.insert(5);
        queue.insert(10);
        assertFalse(queue.isEmpty());
    }

    @Test
    void isEmptyEmpty() {
        //Ture case
        assertTrue(queue.isEmpty());
    }

    /**
     * Test for clear
     */
    @Test
    void clear() {
        //normal case
        queue.insert(5);
        queue.insert(10);
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    void clearEmpty() {
        //empty case
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    @Test
    void clearLarge() {
        //large case
        for(int i = 0; i < 9999; i++) {
            queue.insert(i);
        }
        queue.clear();
        assertTrue(queue.isEmpty());
    }
}