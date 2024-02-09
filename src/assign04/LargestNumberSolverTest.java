package assign04;

import org.junit.jupiter.api.Test;

import static assign04.LargestNumberSolver.readFile;
import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
public class LargestNumberSolverTest {

    /**
     * Test the readFile method
     */
    @Test
    public void testReadFile() {
        String filename = "src/assign04/integers.txt"; // Use existing file

        // Read data from the file
        List<Integer[]> actualData = readFile(filename);

        // Check that the data matches the structure of the file
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            long numLines = lines.count();
            assertEquals(numLines, actualData.size());

            for (Integer[] line : actualData) {
                assertNotNull(line);
                assertTrue(line.length > 0);
            }
        } catch (IOException e) {
            fail("Failed to read test file");
        }
    }

    @Test
    public void testReadFileWithNonExistentFile() {
        String filename = "src/assign04/nonexistent.txt"; // Use non-existent file

        // Read data from the file
        List<Integer[]> actualData = readFile(filename);

        // Check that the data matches the structure of the file
        assertEquals(0, actualData.size());
    }

    @Test
    public void testReadFileWithEmptyFile() {
        String filename = "src/assign04/empty.txt"; // Use empty file

        // Read data from the file
        List<Integer[]> actualData = readFile(filename);

        // Check that the data matches the structure of the file
        assertEquals(0, actualData.size());
    }

    /**
     * Test the insertionSort method
     */
    @Test
    void insertionSortTest(){

        var cmp = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        var arr1 = new Integer[]{2,6,4,9,1,7,3,5,0,8};
        var arr2 = new Integer[]{0,1,2,3,4,5,6,7,8,9};
        LargestNumberSolver.insertionSort(arr1, cmp);
        assertArrayEquals(arr1, arr2);
    }

    /**
     * Test the findLargestInt method
     */
    @Test
    void findLargestStringTest(){

        var arr = new Integer[]{25,102,9};
        String str = LargestNumberSolver.findLargestString(arr);
        assertEquals(str,"925102");
    }

    @Test
    void findLargestBigStringTest(){

            var arr = new Integer[]{1342, 25, 3213, 123, 240, 323};
            String str = LargestNumberSolver.findLargestString(arr);
            assertEquals(str,"3233213252401342123");
    }

    /**
     * Test the findLargestNumber method
     */
    @Test
    void findLargestNumberTest(){

        var arr = new Integer[]{25,102,9};
        BigInteger num = LargestNumberSolver.findLargestNumber(arr);
        assertEquals(num,new BigInteger("925102"));
    }

    @Test
    void findLargestBigNumberTest(){

        var arr = new Integer[]{1342, 25, 3213, 123, 240, 323};
        BigInteger num = LargestNumberSolver.findLargestNumber(arr);
        assertEquals(num,new BigInteger("3233213252401342123"));
    }

    /**
     * Test the findLargestLong method
     */
    @Test
    void findLargestLongTestWithSmallNumbers() {
        var arr = new Integer[]{2, 5, 1};
        long num = LargestNumberSolver.findLargestLong(arr);
        assertEquals(num, 521L);
    }

    @Test
    void findLargestLongTestWithBigNumbers() {
        var arr = new Integer[]{1234, 9876};
        long num = LargestNumberSolver.findLargestLong(arr);
        assertEquals(num, 98761234L);
    }

    /**
     * Test the findLargestInt method
     */
    @Test
    void findLargestIntTestWithSmallNumbers() {
        var arr = new Integer[]{2, 5, 1};
        int num = LargestNumberSolver.findLargestInt(arr);
        assertEquals(num, 521);
    }

    @Test
    void findLargestIntTestWithBigNumbers() {
        var arr = new Integer[]{1234, 9876};
        int num = LargestNumberSolver.findLargestInt(arr);
        assertEquals(num, 98761234);
    }

    /**
     * Test the sum method
     */
    @Test
    public void testSumWithEmptyList() {
        List<Integer[]> emptyList = new ArrayList<>();
        BigInteger result = LargestNumberSolver.sum(emptyList);
        assertEquals(BigInteger.ZERO, result);
    }

    @Test
    public void testSumWithSingleArray() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[] { 5, 15, 25 });
        BigInteger result = LargestNumberSolver.sum(list);
        assertEquals(new BigInteger("52515"), result);
    }

    @Test
    public void testSumWithMultipleArrays() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[] { 1, 2, 3 });
        list.add(new Integer[] { 1, 2, 3 });
        BigInteger result = LargestNumberSolver.sum(list);
        assertEquals(new BigInteger("642"), result);
    }

    @Test
    public void testSumWithLargeArrays() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[] { 1342, 25, 3213 });
        list.add(new Integer[] { 123, 240, 323 });
        BigInteger result = LargestNumberSolver.sum(list);
        assertEquals(new BigInteger("3536491465"), result);
    }

    /**
     * Test the findKthLargest method
     */

    @Test
    public void testFindKthLargestWithEmptyList() {
        List<Integer[]> emptyList = new ArrayList<>();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> LargestNumberSolver.findKthLargest(emptyList, 1));
    }

    @Test
    public void testFindKthLargestWithKEqualToSize() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[] { 1, 2, 3 });
        list.add(new Integer[] { 2, 10, 3 });
        list.add(new Integer[] { 1, 5, 3 });
        assertArrayEquals(new Integer[]{1,2,3}, LargestNumberSolver.findKthLargest(list, 2));
    }

    @Test
    public void testFindKthLargestWithKGreaterThanSize() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[] { 1, 2, 3 });
        list.add(new Integer[] { 2, 10, 3 });
        list.add(new Integer[] { 1, 5, 3 });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> LargestNumberSolver.findKthLargest(list, 5));
    }
}
