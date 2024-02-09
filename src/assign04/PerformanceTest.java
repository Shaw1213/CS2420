package assign04;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerformanceTest {

    public static void main(String[] args) {
        // Setup for generating random data
        Random rand = new Random();

        // Specify the sizes of the tests, including up to ten thousand or more
        int[] testSizes = {100, 1000, 5000, 10000, 15000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000};

        // Loop over each test size
        for (int size : testSizes) {
            // Generate a list of integer arrays of the specified size
            List<Integer[]> testData = generateTestData(size, rand);

            // Warm up the JVM and the method (optional but recommended)
            warmUp(testData, rand);

            // Measure execution time
            long startTime = System.nanoTime();

            // Replace with the actual method call you're testing
            try {
                Integer[] result = LargestNumberSolver.findKthLargest(testData, 1); // Example call
            } catch (Exception e) {
                System.err.println("Error during execution: " + e.getMessage());
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds for readability

            // Output the results
            System.out.println("Test size: " + size + " arrays, Execution time: " + duration + " ms");
        }
    }

    private static List<Integer[]> generateTestData(int size, Random rand) {
        List<Integer[]> testData = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer[] array = new Integer[4]; // Example: Each array has 4 elements
            for (int j = 0; j < array.length; j++) {
                array[j] = rand.nextInt(100); // Populate the array with random integers
            }
            testData.add(array);
        }
        return testData;
    }

    private static void warmUp(List<Integer[]> testData, Random rand) {
        // Perform a few dummy runs to warm up the JVM (optional)
        for (int i = 0; i < 10; i++) {
            try {
                Integer[] result = LargestNumberSolver.findKthLargest(testData, rand.nextInt(testData.size()) + 1);
            } catch (Exception e) {
                // Handle exceptions or ignore
            }
        }
    }
}
