package assign04;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortPerformanceTest {

    public static void main(String[] args) {
        // Setup for generating random data
        Random rand = new Random();

        // Specify the sizes of the tests
        int[] testSizes = new int[50];
        for (int i = 0; i < testSizes.length; i++) {
            testSizes[i] = i * 500;
        }

        // Loop over each test size
        for (int size : testSizes) {
            // Generate test data
            List<Integer[]> testData = generateTestData(size, rand);

            // Measure execution time using Arrays.sort()
            long startTime = System.nanoTime();

            // Sort each array and find the kth largest element (example with k=1 for simplicity)
            Integer kthLargest = findKthLargestUsingSort(testData, 1);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);

            // Output the results
            System.out.println("Test size: " + size + " arrays, Execution time: " + duration + " ms");
        }
    }

    private static List<Integer[]> generateTestData(int size, Random rand) {
        List<Integer[]> testData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer[] array = new Integer[4]; // Assuming each array has 4 elements
            for (int j = 0; j < array.length; j++) {
                array[j] = rand.nextInt(100); // Fill with random integers
            }
            testData.add(array);
        }
        return testData;
    }

    private static Integer findKthLargestUsingSort(List<Integer[]> testData, int k) {
        List<Integer> kthLargestElements = new ArrayList<>();
        for (Integer[] array : testData) {
            Arrays.sort(array);
            if (k <= array.length) {
                kthLargestElements.add(array[array.length - k]);
            } else {
                throw new IllegalArgumentException("k is larger than the size of the arrays");
            }
        }
        return kthLargestElements.stream().max(Integer::compareTo).orElse(null);
    }
}
