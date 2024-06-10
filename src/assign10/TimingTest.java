package assign10;

import java.util.ArrayList;
import java.util.Random;


/**
 * This is a class designated for timing different hashcode and their efficiency in the custom BinaryMaxHeap class.
 *
 * @author Janne Wald and Shawn Zhang
 * @version April 3, 2024.
 */
public class TimingTest {
    /**
     * @return a random number.
     */
    public static int randomNumber() {
        Random r = new Random();
        return r.nextInt(250000);
    }


    /**
     * Runs the actual timing experiments
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        var maxElements = 100000;
        var timeToLoop = 200;
        int elementCount;
        int i;
        StringBuilder matlabN;
        StringBuilder matlabAVGtime;
        BinaryMaxHeap<Integer> heap = null;
        ArrayList<Integer> preHeap;


        // =========================== Add Test ===========================
        System.out.println("\n\nTesting Adding Numbers**");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                heap = new BinaryMaxHeap<>();
                for (int j = 0; j < elementCount; j++) {
                    var num = randomNumber();
                    // Start
                    var start = System.nanoTime();
                    heap.add(num); // Check for collision in basic arr
                    // Finish
                    var end = System.nanoTime();
                    sum += end - start;
                }

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to insert an " + elementCount + " , amount of random numbers: " + avg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== Peek Test ===========================
        System.out.println("\n\nTesting Peeking Numbers**");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                heap = new BinaryMaxHeap<>();
                for (int j = 0; j < elementCount; j++) {
                    var num = randomNumber();
                    heap.add(num); // Check for collision in basic arr
                    // Start
                    var start = System.nanoTime();
                    heap.peek();
                    // Finish
                    var end = System.nanoTime();
                    sum += end - start;
                }

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to peek an " + elementCount + " , amount of random numbers: " + avg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== Extract Max Test ===========================
        System.out.println("\n\nTesting Extracting max**");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) { // loops 40 times

                heap = new BinaryMaxHeap<>();
                for (int j = 0; j < elementCount; j++) {
                    var num = randomNumber();
                    heap.add(num); // Check for collision in basic arr

                }
                for (int j = 0; j < elementCount; j++) {
                    // Start
                    var start = System.nanoTime();
                    // Finish
                    heap.extractMax();
                    var end = System.nanoTime();
                    sum += end - start;
                }

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to extract an " + elementCount + " , amount of random numbers: " + avg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== HeapKth 1000 Max Test ===========================
        System.out.println("\n\nTesting Extracting Kth largest numbers using heap 1k k");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                preHeap = new ArrayList<>();
                for (int j = 0; j < elementCount; j++) {
                    var num = randomNumber();
                    preHeap.add(num);
                }

                // Start
                var start = System.nanoTime();
                FindKLargest.findKLargestHeap(preHeap, 1000);
                // Finish
                var end = System.nanoTime();
                sum += end - start;

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to get 50 largest an " + elementCount + " , amount of random numbers: " + avg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== SortKth Max Test 1000 ===========================
        System.out.println("\n\nTesting Extracting Kth largest numbers using sort 1k k");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                preHeap = new ArrayList<>();
                for (int j = 0; j < elementCount; j++) {
                    var num = randomNumber();
                    preHeap.add(num);
                }

                // Start
                var start = System.nanoTime();
                FindKLargest.findKLargestSort(preHeap, 1000);
                // Finish
                var end = System.nanoTime();
                sum += end - start;

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to get 50 largest an " + elementCount + " , amount of random numbers: " + avg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== HeapKth 10000 Max Test ===========================
        System.out.println("\n\nTesting Extracting Kth largest numbers using heap10k k");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                preHeap = new ArrayList<>();
                for (int j = 0; j < elementCount; j++) {
                    var num = randomNumber();
                    preHeap.add(num);
                }

                // Start
                var start = System.nanoTime();
                FindKLargest.findKLargestHeap(preHeap, 10000);
                // Finish
                var end = System.nanoTime();
                sum += end - start;

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to get 50 largest an " + elementCount + " , amount of random numbers: " + avg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);

        // =========================== SortKth Max Test ===========================
        System.out.println("\n\nTesting Extracting Kth largest numbers using sort 10k k");
        // Accumulation loop setup
        matlabN = new StringBuilder();
        matlabN.append("Matlab N arr: [");
        matlabAVGtime = new StringBuilder();
        matlabAVGtime.append("Matlab AVG time arr: [");

        for (elementCount = 10000; elementCount <= maxElements; elementCount += 10000) {
            long sum = 0;
            for (i = 0; i < timeToLoop; i++) {
                preHeap = new ArrayList<>();
                for (int j = 0; j < elementCount; j++) {
                    var num = randomNumber();
                    preHeap.add(num);
                }

                // Start
                var start = System.nanoTime();
                FindKLargest.findKLargestSort(preHeap, 10000);
                // Finish
                var end = System.nanoTime();
                sum += end - start;

            }
            // Average Calculation
            long avg = sum / timeToLoop;
            matlabN.append(elementCount + " ");
            matlabAVGtime.append(avg + " ");
            // Display current iteration to command line
            System.out.println("The time to get 50 largest an " + elementCount + " , amount of random numbers: " + avg);
        }
        // Display current test to command line
        matlabN.append("]");
        System.out.println("\n" + matlabN);
        matlabAVGtime.append("]");
        System.out.println(matlabAVGtime);
    }
}
