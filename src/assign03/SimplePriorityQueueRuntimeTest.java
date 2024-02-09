package assign03;

public class SimplePriorityQueueRuntimeTest {
    public static void main(String[] args) {
        int[] sizes = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000, 1800000, 1900000, 2000000};
        int timesToLoop = 10000; // Number of times to call findMax for each queue size

        for (int size : sizes) {
            SimplePriorityQueue<Integer> queue = new SimplePriorityQueue<>();

            // Populate the queue with sequential integers up to the current size
            for (int i = 0; i < size; i++) {
                queue.insert(i);
            }

            long startTime = System.nanoTime();

            // Repeatedly call findMax
            for (int i = 0; i < timesToLoop; i++) {
                queue.findMax();
            }

            long endTime = System.nanoTime();
            double averageTime = ((endTime - startTime) / (double) timesToLoop);

            System.out.println("Average time for size " + size + ": " + averageTime + " nanoseconds.");
        }
    }
}
