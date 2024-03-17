package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Demonstrates how to use the Graph class.
 * 
 * @author Erin Parker
 * @version March 3, 2022
 */
public class GraphDemo {

	public static void main(String[] args) {

		// Build a sample directed graph
		Graph<String> complexGraph = new Graph<>();
		complexGraph.addEdge("a", "b");
		complexGraph.addEdge("a", "c");
		complexGraph.addEdge("b", "d");
		complexGraph.addEdge("c", "e");
		complexGraph.addEdge("d", "f");
		complexGraph.addEdge("e", "f");
		complexGraph.addEdge("f", "g");



		// Print textual representation of sample graph
		System.out.println(complexGraph);
//
//		// Print DOT representation of sample graph (paste into http://www.webgraphviz.com)
		System.out.println(complexGraph.generateDot());

		// Test topologicalSort method
		// Test sort method on the DAG
//		System.out.println("Testing sort method on a DAG:");
//		List<String> sources = List.of("a", "b", "b", "c");
//		List<String> destinations = List.of("b", "c", "d", "d");
//		System.out.println("Topological sort: " + GraphUtility.sort(sources, destinations));
//
//		// Build a graph with a cycle
//		Graph<String> graphWithCycle = new Graph<>();
//		graphWithCycle.addEdge("a", "b");
//		graphWithCycle.addEdge("b", "c");
//		graphWithCycle.addEdge("c", "a");
//
//		// Test sort method on a graph with a cycle
//		System.out.println("\nTesting sort method on a graph with a cycle:");
//		sources = List.of("a", "b", "c");
//		destinations = List.of("b", "c", "a");
//		try {
//			System.out.println("Topological sort: " + GraphUtility.sort(sources, destinations));
//		} catch (IllegalArgumentException e) {
//			System.out.println("Expected exception: " + e.getMessage());
//		}
//		// Test BFS and areConnected method
//		System.out.println("Testing shortestPath method:");
//		List<String> sources = List.of("a", "b", "b", "c");
//		List<String> destinations = List.of("b", "c", "d", "d");
////
//		System.out.println("Shortest path from 'a' to 'd': " + GraphUtility.shortestPath(sources, destinations, "a", "d"));
//		System.out.println("Shortest path from 'a' to 'c': " + GraphUtility.shortestPath(sources, destinations, "a", "c"));
//		System.out.println("Shortest path from 'b' to 'd': " + GraphUtility.shortestPath(sources, destinations, "b", "d"));// This should throw an exception because there is no path from 'd' to 'a'
//		try {
//			System.out.println("Shortest path from 'd' to 'a': " + GraphUtility.shortestPath(sources, destinations, "d", "a"));
//		} catch (IllegalArgumentException e) {
//			System.out.println("Expected exception: " + e.getMessage());
//		}







		// Test DFS and areConnected method
		System.out.println("Testing areConnected method on a more complicated graph:");
		List<String> sources = List.of("a", "a", "b", "c", "d", "e", "f");
		List<String> destinations = List.of("b", "c", "d", "e", "f", "f", "g");

		System.out.println("Are 'a' and 'g' connected? (Expected: true) Actual: " + GraphUtility.areConnected(sources, destinations, "a", "g"));
		System.out.println("Are 'b' and 'e' connected? (Expected: false) Actual: " + GraphUtility.areConnected(sources, destinations, "b", "e"));
		System.out.println("Are 'd' and 'g' connected? (Expected: true) Actual: " + GraphUtility.areConnected(sources, destinations, "d", "g"));
		System.out.println("Are 'e' and 'a' connected? (Expected: false) Actual: " + GraphUtility.areConnected(sources, destinations, "e", "a"));
		// This should throw an exception because 'h' does not exist in the graph
		try {
			System.out.println("Are 'a' and 'h' connected? (Expected: exception) Actual: " + GraphUtility.areConnected(sources, destinations, "a", "h"));
		} catch (IllegalArgumentException e) {
			System.out.println("Expected exception for non-existent vertex: " + e.getMessage());
		}
		//System.out.println("Is there a path from 'c' to 'a'? (Expected: true) Actual: " + GraphUtility.areConnected(sources, destinations, "c", "a"));
	}

	public static List<List<String>> generateRandomGraph(int vertexCount) {
		Random rng = new Random();
		List<String> sources = new ArrayList<>();
		List<String> destinations = new ArrayList<>();

		// Generate a list of vertices
		String[] vertices = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			vertices[i] = "v" + i;
		}

		// Randomly connect the vertices using 2 * |V| edges
		for (int i = 0; i < 2 * vertexCount; i++) {
			sources.add(vertices[rng.nextInt(vertexCount)]);
			destinations.add(vertices[rng.nextInt(vertexCount)]);
		}

		List<List<String>> graphData = new ArrayList<>();
		graphData.add(sources);
		graphData.add(destinations);
		return graphData;
	}

	public static List<List<String>> generateRandomDAG(int vertexCount, Random rng) {
		List<String> sources = new ArrayList<>();
		List<String> destinations = new ArrayList<>();

		// Generate a list of vertices
		String[] vertices = new String[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			vertices[i] = "v" + i;
		}

		// Randomly connect the vertices using 2 * |V| edges, ensuring no cycles
		for (int i = 0; i < 2 * vertexCount; i++) {
			int sourceIndex = rng.nextInt(vertexCount - 1); // Ensure sourceIndex is always less than the last index
			int destinationIndex = sourceIndex + 1 + rng.nextInt(vertexCount - sourceIndex - 1);

			sources.add(vertices[sourceIndex]);
			destinations.add(vertices[destinationIndex]);
		}

		List<List<String>> graphData = new ArrayList<>();
		graphData.add(sources);
		graphData.add(destinations);
		return graphData;
	}


}
