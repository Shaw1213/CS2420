package assign07test;

import java.util.*;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph generic.
 * 
 * @author Erin Parker and Shawn Zhang
 * @version March 14, 2024
 */
public class Graph<T> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<T, Vertex<T>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<>();
	}

	/**
	 * Adds a directed edge from the source vertex to the destination vertex.
	 *
	 * @param sources - the data of the source vertex
	 * @param destinations - the data of the destination vertex
	 */
	public Graph(List<T> sources, List<T> destinations) {
		this();
		// If the sizes of the source and destination lists are not equal, throw an exception.
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Sources and destinations lists must be of equal size.");
		}

		// Add an edge for each source-destination pair.
		for (int i = 0; i < sources.size(); i++) {
			addEdge(sources.get(i), destinations.get(i));
		}
	}

	/**
	 * Adds a directed edge from the source vertex to the destination vertex.
	 *
	 * @param from - the data of the source vertex
	 * @param to - the data of the destination vertex
	 */
	public void addEdge(T from, T to) {
		Vertex<T> source = vertices.get(from);
		// If the source vertex does not exist, create it.
		if (source == null) {
			source = new Vertex<>(from);
			vertices.put(from, source);
		}

		Vertex<T> destination = vertices.get(to);
		// If the destination vertex does not exist, create it.
		if (destination == null) {
			destination = new Vertex<>(to);
			vertices.put(to, destination);
		}

		// Connect the source vertex to the destination vertex.
		source.connectTo(destination);
	}

	/**
	 * Generates the DOT encoding of this graph as string, which can be
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");

		// for every vertex
		for(Vertex<T> v : vertices.values()) {
			// for every edge
			for (Vertex<T> neighbor : v.getAdj()) {
				dot.append("\t\"" + v.getData() + "\" -> \"" + neighbor.getData() + "\"\n");
			}
		}

		dot.append("}");
		return dot.toString();
	}

	/**
	 * Returns a string representation of the graph.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		// For each vertex in the graph, append its string representation to the StringBuilder.
		for (Vertex<T> vertex : vertices.values()) {
			sb.append(vertex).append("\n");
		}
		return sb.toString();
	}

	/**
	 * Returns the number of vertices in the graph.
	 */
	public boolean dfs(T cur, T goal){
		for(Vertex<T> vertex : vertices.values()){
			// Mark all vertices as unvisited.
			vertex.markUnvisited();
		}

		// Check if the vertices exist in the graph
		Vertex<T> curVertex = vertices.get(cur);
		Vertex<T> goalVertex = vertices.get(goal);
		if (curVertex == null || goalVertex == null) {
			throw new IllegalArgumentException();
		}

		// Perform a depth-first search from the current vertex to the goal vertex.
		return dfsRecursive(vertices.get(cur), vertices.get(goal));
	}

	// Helper method for dfs.
	private boolean dfsRecursive(Vertex<T> cur, Vertex<T> goal) {
		// Mark the current vertex as visited.
		cur.markVisited();
		// If the current vertex is the goal vertex, return true.
		if (cur.equals(goal)) {
			return true;
		}

		for (Vertex<T> neighbor : cur.getAdj()) {
			// If the neighbor has not been visited and a path to the goal vertex is found, return true.
			if (!neighbor.hasBeenVisited() && dfsRecursive(neighbor, goal)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the shortest path from the current vertex to the goal vertex.
	 */
	public List<T> bfs(T cur, T goal){
		for(Vertex<T> vertex : vertices.values()){
			// Mark all vertices as unvisited before performing a new BFS.
			vertex.markUnvisited();
		}

		// Perform a breadth-first search from the current vertex to the goal vertex.
		Vertex<T> source = vertices.get(cur);
		Vertex<T> destination = vertices.get(goal);

		// If the source or destination vertex does not exist, return an empty list.
		Queue<Vertex<T>> queue = new LinkedList<>();
		queue.add(source);
		source.markVisited();

		// While the queue is not empty, perform a breadth-first search.
		while (!queue.isEmpty()) {
			Vertex<T> curVertex = queue.poll();
			if (curVertex.equals(destination)) {
				return constructPath(source, destination);
			}
			for (Vertex<T> neighbor : curVertex.getAdj()) {
				if (!neighbor.hasBeenVisited()) {
					neighbor.markVisited();
					neighbor.setPredecessor(curVertex);
					queue.add(neighbor);
				}
			}
		}

		return new LinkedList<>();
	}

	// Helper method for bfs.
	private List<T> constructPath(Vertex<T> source, Vertex<T> destination){
		LinkedList<T> path = new LinkedList<>();
		Vertex<T> cur = destination;
		while (cur != null && cur != source) {
			path.addFirst(cur.getData());
			cur = cur.getPredecessor();
		}

		if (cur == source){
			path.addFirst(source.getData());
		}

		return path;
	}

	// Returns a list of the vertices in topologically sorted order.
	public List<T> topSort(){
		List<T> sorted = new LinkedList<>();
		Queue<Vertex<T>> queue = new LinkedList<>();

		for(Vertex<T> vertex : vertices.values()){
			// If the vertex has no incoming edges, add it to the queue.
			if (vertex.getIncomingEdges() == 0) {
				queue.add(vertex);
			}
		}

		while(!queue.isEmpty()){
			Vertex<T> cur = queue.poll();
			sorted.add(cur.getData());
			// For each neighbor of the current vertex, decrement the count of incoming edges.
			for (Vertex<T> adj : cur.getAdj()) {
				adj.adjustIncomingEdges(-1);
				// If the neighbor has no incoming edges, add it to the queue.
				if (adj.getIncomingEdges() == 0) {
					queue.add(adj);
				}
			}
		}

		if (sorted.size() != vertices.size()) {
			throw new IllegalArgumentException("Graph contains a cycle.");
		}

		return sorted;
	}



}