package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphTimeExperiment {
    public static void main(String[] args) {
        int maxVertices = 100;
        int increment = 10;
        int trials = 100;
        int warmUpIterations = 100;

        Random random = new Random(42);

        // Warm-up phase
        for (int i = 0; i < warmUpIterations; i++) {
            List<List<String>> graphData = GraphDemo.generateRandomDAG(50, random);
            List<String> sources = graphData.get(0);
            List<String> destinations = graphData.get(1);
            GraphUtility.sort(sources, destinations);
        }

        // Timing phase
        for (int vertices = increment; vertices <= maxVertices; vertices += increment) {
            List<Long> times = new ArrayList<>();

            for (int trial = 0; trial < trials; trial++) {
                List<List<String>> graphData = GraphDemo.generateRandomDAG(vertices, random);
                List<String> sources = graphData.get(0);
                List<String> destinations = graphData.get(1);

                long startTime = System.nanoTime();
                List<String> sorted = GraphUtility.sort(sources, destinations);
                long endTime = System.nanoTime();

                times.add(endTime - startTime);
            }

            long averageTime = times.stream().mapToLong(Long::longValue).sum() / trials;
            System.out.println("Average time for " + vertices + " vertices: " + averageTime + " ns");
        }
    }
}
