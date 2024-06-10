package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a testing class for the BinaryMaxHeap class we constructed from scratch.
 *
 * @author Janne Wald & Shawn Zang.
 * @version April 9, 2024.
 */
class BinaryMaxHeapTest {
    private BinaryMaxHeap<String> empty;
    private BinaryMaxHeap<Integer> root;
    private BinaryMaxHeap<Integer> norm;
    private BinaryMaxHeap<nonComparable> comparator;

    /**
     * This is a class for a random object that only stores one value and does not already have a comparable interface.
     */
    public class nonComparable {
        private int val;

        public nonComparable(int val) {
            this.val = val;
        }
        // To print the arrays to console better
        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    /**
     * This is a class that creates a custom comparator that checks the values of the NonComparable
     * objects.
     */
    public class nonComparableComparator implements Comparator<nonComparable> {
        @Override
        public int compare(nonComparable o1, nonComparable o2) {
            return o1.val - o2.val;
        }
    }

    @BeforeEach
    void setUp() {
        // These working already tests our add() method
        empty = new BinaryMaxHeap<>();

        root = new BinaryMaxHeap<>();
        root.add(1);

        norm = new BinaryMaxHeap<>();
        norm.add(10);
        norm.add(7);
        norm.add(2);

        comparator = new BinaryMaxHeap<>(new nonComparableComparator());
        comparator.add(new nonComparable(10));
        comparator.add(new nonComparable(7));
        comparator.add(new nonComparable(2));

    }

    @Test
    public void testAddEmpty() {
        assertEquals(0, empty.size());
        empty.add("root");
        assertEquals(1, empty.size());
        assertEquals("root", empty.peek());
    }

    @Test
    public void testAddLeaf() {
        assertEquals(1, root.size());
        root.add(-1);
        assertEquals(2, root.size());
        assertEquals(1, root.peek());
    }

    @Test
    public void testAddMiddle() {
        assertEquals(3, comparator.size());
        comparator.add(new nonComparable(4));
        assertEquals(4, comparator.size());
        assertEquals(10, comparator.peek().val);

    }

    @Test
    public void testClearEmpty() {
        assertEquals(0, empty.size());
        empty.clear();
        assertEquals(0, empty.size());
        try {
            assertNull(empty.peek());
            fail();
        } catch (Exception e) {
            // Do nothing
        }
    }

    @Test
    public void testClearHead() {
        assertEquals(1, root.size());
        root.clear();
        assertEquals(0, root.size());
        try {
            assertNull(root.peek());
            fail();
        } catch (Exception e) {
            // Do nothing
        }
    }

    @Test
    public void testClearNorm() {
        assertEquals(3, norm.size());
        norm.clear();
        assertEquals(0, norm.size());
        try {
            assertNull(norm.peek());
            fail();
        } catch (Exception e) {
            // Do nothing
        }

    }

    @Test
    public void testRemoveMaxEmpty() {
        try {
            empty.extractMax();
            fail();
        } catch (Exception e) {
            // DO nothing
        }
    }
    @Test
    public void testRemoveMaxRoot(){
        assertEquals(1, root.extractMax());
        try {
            root.extractMax();
            fail();
        } catch (Exception e) {
            // DO nothing
        }
    }
    @Test
    public void testRemoveMax(){
        assertEquals(10, norm.extractMax());
        assertEquals(7, norm.extractMax());
        assertEquals(2, norm.extractMax());
        try {
            norm.extractMax();
            fail();
        } catch (Exception e) {
            // DO nothing
        }

    }
    @Test
    public void testClear(){
        var none = new BinaryMaxHeap<>();
        norm.clear();
        assertEquals(none.size(), norm.size());
    }
    @Test
    public void testClearClear(){
        try{
            empty.clear();
        } catch(Exception e){
            fail();
        }
        assertEquals(0, empty.size());
    }
    @Test
    public void toArrayEmpty(){
        var a = Arrays.toString(new Integer[0]);
        var b = Arrays.toString(empty.toArray());

        assertEquals(a, b);
    }
    @Test
    public void toArrayHead(){
        var a = Arrays.toString(new Integer[]{1});
        var b = Arrays.toString(root.toArray());

        assertEquals(a, b);
    }
    @Test
    public void toArrayNorm(){
        var a = Arrays.toString(new Integer[]{10, 7, 2});
        var b = Arrays.toString(norm.toArray());

        assertEquals(a, b);
    }
    @Test
    public void testAddTooBig(){
        var big = new BinaryMaxHeap<Integer>();
        for(int i = 0; i < 15; i++)
            big.add(i);
        assertEquals(15, big.size());
        assertEquals(14, big.peek());
        var a = Arrays.toString(new Integer[]{14, 9, 13, 6, 8, 10, 12, 0, 3, 2, 7, 1, 5, 4, 11});
        var b = Arrays.toString(big.toArray());

        assertEquals(a, b);
    }
    @Test
    public void testConstructFromEmptyList(){
        var list = new ArrayList<Integer>();
        var heap = new BinaryMaxHeap<>(list);
        assertEquals(Arrays.toString(new Integer[0]), Arrays.toString(heap.toArray()));
    }
    @Test
    public void testConstructFromList(){
        var palindrome = new ArrayList<nonComparable>();
        palindrome.add(new nonComparable(25));
        palindrome.add(new nonComparable(53));
        palindrome.add(new nonComparable(-5));
        palindrome.add(new nonComparable(11));
        palindrome.add(new nonComparable(25));
        palindrome.add(new nonComparable(25));
        palindrome.add(new nonComparable(11));
        palindrome.add(new nonComparable(-5));
        palindrome.add(new nonComparable(53));
        palindrome.add(new nonComparable(25));
        var heap = new BinaryMaxHeap<>(palindrome, new nonComparableComparator());
        var expected = "[53, 53, 25, 25, 25, -5, 11, -5, 11, 25]";
        assertEquals(expected, Arrays.toString(heap.toArray()));
    }
}