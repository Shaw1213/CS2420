package assign06;

public class ArrayStackPeekTimer {

    public static void main(String[] args) {
        // Define the sizes for the problem, e.g., number of elements in the stack.
        int[] problemSizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 256000, 512000, 1024000};
        int timesToLoop = 100000; // Number of times to call peek.

        // Timing for ArrayStack peek operation
        System.out.println("ArrayStack Peek Operation Timing:");
        for (int size : problemSizes) {
            // Clear the stack and re-fill it for each problem size.
            ArrayStack<Integer> stack = new ArrayStack<>();

            // Pre-fill the stack with 'size' elements.
            for (int i = 0; i < size; i++) {
                stack.push(i);
            }

            // Warm up the JVM and allow for Just-In-Time (JIT) optimizations.
            for (int i = 0; i < timesToLoop; i++) {
                stack.peek(); // Warm-up calls.
            }

            // Clear the stack to simulate a new run.
            stack.clear();

            // Re-fill the stack with 'size' elements.
            for (int i = 0; i < size; i++) {
                stack.push(i);
            }

            long startTime = System.nanoTime(); // Start timing.

            // Perform the peek operations.
            for (int i = 0; i < timesToLoop; i++) {
                stack.peek();
            }

            long endTime = System.nanoTime(); // End timing.
            double averageTimePerPeek = (endTime - startTime) / (double) timesToLoop; // Calculate average time per peek.

            System.out.println("Problem size: " + size + " | Average time per peek: " + averageTimePerPeek + " ns");
        }
    }
}
