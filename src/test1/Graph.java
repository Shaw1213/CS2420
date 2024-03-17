package test1;

import java.util.*;

public class Graph<T> {
    private Map<T, Vertex<T>> vertices;


    /**
     * Initializes an empty graph with no vertices or edges.
     */
    public Graph() {
        this.vertices = new HashMap<>();
    }

    /**
     * Initializes a graph with vertices and edges based on provided source-destination pairs.
     *
     * @param sources      A list of source vertices.
     * @param destinations A list of destination vertices.
     * @throws IllegalArgumentException If the sizes of the source and destination lists are not equal.
     */
    public Graph(List<T> sources, List<T> destinations) {
        this();
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException("Sources and destinations lists must be of equal size.");
        }

        for (int i = 0; i < sources.size(); i++) {
            addEdge(sources.get(i), destinations.get(i));
        }
    }


    /**
     * Adds a directed edge from a source vertex to a destination vertex.
     *
     * @param from The source vertex value.
     * @param to   The destination vertex value.
     */
    public void addEdge(T from, T to) {
        Vertex<T> source = vertices.computeIfAbsent(from, Vertex::new);
        Vertex<T> destination = vertices.computeIfAbsent(to, Vertex::new);

        source.connectTo(destination);
    }

    /**
     * Returns a string representation of the graph, including its vertices and edges.
     *
     * @return A string containing information about the graph's vertices and their connections.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<T> vertex : vertices.values()) {
            sb.append(vertex).append("\n");
        }
        return sb.toString();
    }

    /**
     * Performs a Depth-First Search from a source vertex to a goal vertex.
     *
     * @param curr The value of the source vertex.
     * @param goal The value of the goal vertex.
     * @return True if a path exists from the source to the goal, false otherwise.
     */
    public boolean DFS(T curr, T goal) {
        for (Vertex<T> vertex : vertices.values()) { // reset visited status before a new DFS
            vertex.markUnvisited();
        }
        return DFSRecursive(vertices.get(curr), vertices.get(goal));
    }

    /**
     * Recursively performs a Depth-First Search from a current vertex to a goal vertex.
     *
     * @param curr The current vertex being explored.
     * @param goal The goal vertex being searched for.
     * @return True if a path from the current vertex to the goal is found, false otherwise.
     */
    private boolean DFSRecursive(Vertex<T> curr, Vertex<T> goal) {
        curr.markVisited();
        if (curr.equals(goal)) {
            return true;
        }
        for (Vertex<T> neighbor : curr.getNeighbors()) {
            if (!neighbor.hasBeenVisited() && DFSRecursive(neighbor, goal)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Performs a Breadth-First Search (BFS) traversal from a specified source vertex to a goal vertex.
     *
     * @param curr The value of the source vertex.
     * @param goal The value of the goal vertex.
     * @return A list of vertices representing the path from the source to the goal if one exists, or an empty list if no path is found.
     */
    public List<T> BFS(T curr, T goal) {
        for (Vertex<T> vertex : vertices.values()) { // reset visited status before a new BFS
            vertex.markUnvisited();
        }

        Vertex<T> source = vertices.get(curr);
        Vertex<T> destination = vertices.get(goal);

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.offer(source);
        source.markVisited();

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();
            if (currentVertex.equals(destination)) {
                return constructPath(source, destination);
            }
            for (Vertex<T> neighbor : currentVertex.getNeighbors()) {
                if (!neighbor.hasBeenVisited()) {
                    neighbor.markVisited();
                    neighbor.setPredecessor(currentVertex);
                    queue.offer(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }

    /**
     * Constructs a path from a source vertex to a destination vertex.
     *
     * @param source       The starting vertex of the path.
     * @param destination  The ending vertex of the path.
     * @return A list of vertices representing the path from the source to the destination.
     */
    private List<T> constructPath(Vertex<T> source, Vertex<T> destination) {
        LinkedList<T> path = new LinkedList<>();
        Vertex<T> current = destination;
        while (current != null && current != source) {
            path.addFirst(current.getData());
            current = current.getPredecessor();
        }

        if (current == source) {
            path.addFirst(source.getData());
        }

        return path;
    }

    /**
     * Performs a topological sort on the directed acyclic graph (DAG).
     *
     * @return A list of vertices in a topological order, or throws an exception if the graph contains a cycle.
     */
    public List<T> topological() {
        List<T> sortedList = new LinkedList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        for (Vertex<T> vertex : vertices.values()) {
            if (vertex.incomingEdgeCount() == 0) {
                queue.add(vertex);
            }
        }

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();
            sortedList.add(currentVertex.getData());

            for (Vertex<T> adjacentVertex : currentVertex.getNeighbors()) {
                adjacentVertex.adjustIncomingEdges(-1);

                if (adjacentVertex.incomingEdgeCount() == 0) {
                    queue.add(adjacentVertex);
                }
            }
        }

        if (sortedList.size() != vertices.size()) {
            throw new IllegalArgumentException("The graph contains a cycle.");
        }

        return sortedList;
    }
}
