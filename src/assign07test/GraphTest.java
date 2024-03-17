package assign07test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testDfs() {
        // Create a new graph
        Graph<String> graph = new Graph<>();

        // Add nodes and edges to the graph
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");

        // Run dfs on the graph
        boolean result = graph.dfs("A", "D");

        // Assert that the result is as expected
        assertTrue(result, "DFS should find a path from A to D");

        // Run dfs on the graph for a non-existent path
        result = graph.dfs("D", "A");

        // Assert that the result is as expected
        assertFalse(result, "DFS should not find a path from D to A");
    }

//    @Test
//    public void testDfsWithEmptyGraph() {
//        Graph<String> graph = new Graph<>();
//        assertFalse(graph.dfs("A", "B"));
//    }
//
//    @Test
//    public void testDfsWithSingleNodeGraph() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "A");
//        assertTrue(graph.dfs("A", "A"));
//        assertFalse(graph.dfs("A", "B"));
//    }
//
//    @Test
//    public void testDfsWithDisconnectedGraph() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "B");
//        graph.addEdge("C", "D");
//        assertFalse(graph.dfs("A", "D"));
//    }
//
//    @Test
//    public void testDfsWithCyclicGraph() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "B");
//        graph.addEdge("B", "C");
//        graph.addEdge("C", "A");
//        assertTrue(graph.dfs("A", "C"));
//    }
//
//    @Test
//    public void testDfsWithDuplicateEdges() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "B");
//        graph.addEdge("A", "B");
//        assertTrue(graph.dfs("A", "B"));
//    }
//
//
//    @Test
//    public void testBfsWithEmptyGraph() {
//        Graph<String> graph = new Graph<>();
//        assertEquals(Collections.emptyList(), graph.bfs("A", "B"));
//    }
//
//    @Test
//    public void testBfsWithSingleNodeGraph() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "A");
//        assertEquals(Collections.singletonList("A"), graph.bfs("A", "A"));
//        assertEquals(Collections.emptyList(), graph.bfs("A", "B"));
//    }
//
//    @Test
//    public void testBfsWithDisconnectedGraph() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "B");
//        graph.addEdge("C", "D");
//        assertEquals(Collections.emptyList(), graph.bfs("A", "D"));
//    }
//
//    @Test
//    public void testBfsWithCyclicGraph() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "B");
//        graph.addEdge("B", "C");
//        graph.addEdge("C", "A");
//        assertEquals(Arrays.asList("A", "B", "C"), graph.bfs("A", "C"));
//    }
//
//    @Test
//    public void testBfsWithDuplicateEdges() {
//        Graph<String> graph = new Graph<>();
//        graph.addEdge("A", "B");
//        graph.addEdge("A", "B");
//        assertEquals(Collections.singletonList("B"), graph.bfs("A", "B"));
//    }


    @Test
    public void testAreConnectedEmptyGraph() {
        List<String> sources = new ArrayList<>();
        List<String> destinations = new ArrayList<>();
        Assertions.assertFalse(GraphUtility.areConnected(sources, destinations, "A", "B"));
    }

    @Test
    public void testAreConnectedDirectConnection() {
        List<String> sources = Arrays.asList("A");
        List<String> destinations = Arrays.asList("B");
        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "B"));
    }



    @Test
    public void testShortestPathDirectConnection() {
        List<String> sources = Arrays.asList("A");
        List<String> destinations = Arrays.asList("B");
        // This should return a path from A to B, which is just ["A", "B"].
        assertEquals(Arrays.asList("A", "B"), GraphUtility.shortestPath(sources, destinations, "A", "B"));
    }




}