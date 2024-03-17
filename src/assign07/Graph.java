package assign07;

import java.util.*;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges). 
 * The graph is generic and can store any type of object at each vertex.
 *
 * @author Erin Parker and Shawn Zhang
 * @version March 16, 2024
 */
public class Graph<T> {

	// the graph -- a set of vertices (T name mapped to Vertex instance)
	private HashMap<T, Vertex<T>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<T, Vertex<T>>();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1"
	 * to the vertex with name "name2".  (If either vertex does not already
	 * exist in the graph, it is added.)
	 *
	 * @param name1 - T name for source vertex
	 * @param name2 - T name for destination vertex
	 */
	public void addEdge(T name1, T name2) {
		Vertex<T> vertex1;
		// if vertex already exists in graph, get its object
		if(vertices.containsKey(name1))
			vertex1 = vertices.get(name1);
			// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<T>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex<T> vertex2;
		if(vertices.containsKey(name2))
			vertex2 = vertices.get(name2);
		else {
			vertex2 = new Vertex<T>(name2);
			vertices.put(name2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
		vertex1.addEdge(vertex2);
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
			Iterator<Edge<T>> edges = v.edges();
			while(edges.hasNext())
				dot.append("\t\"" + v.getName() + "\" -> \"" + edges.next() + "\"\n");

		}

		return dot.toString() + "}";
	}

	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();

		for(Vertex<T> v : vertices.values())
			result.append(v + "\n");

		return result.toString();
	}

	/**
	 *
	 * DFS search for a path between two vertices in the graph.
	 *
	 * @param graph - the graph to search
	 * @param srcData - the data of the source vertex
	 * @param dstData - the data of the destination vertex
	 * @return
	 * @param <Type> - the type of the data stored in the graph's vertices
	 */
	public static <Type> boolean dfs(Graph<Type> graph, Type srcData, Type dstData) {
		HashSet<Type> visited = new HashSet<>();
		List<Type> path = dfsHelper(graph, srcData, dstData, visited);
		return path != null; // Return true if a path exists, false otherwise
	}


	//helper method for dfs
	private static <Type> List<Type> dfsHelper(Graph<Type> graph, Type current, Type target, HashSet<Type> visited) {
		visited.add(current);

		if (current.equals(target)) {
			List<Type> path = new ArrayList<>();
			path.add(current);
			return path;
		}

		if (graph.vertices.containsKey(current)) {
			Vertex<Type> vertex = graph.vertices.get(current);
            for (Iterator<Edge<Type>> it = vertex.edges(); it.hasNext(); ) {
                Edge<Type> edge = it.next();
                Type next = edge.getOtherVertex().getName();
                if (!visited.contains(next)) {
                    List<Type> rest = dfsHelper(graph, next, target, visited);
                    if (rest != null) {
                        rest.add(0, current); // Prepend current to rest
                        return rest;
                    }
                }
            }
		}

		return null; // Never found a path
	}

	/**
	 * TopologicalSort of the graph
	 * @param graph - the graph to sort
	 * @return a list of the vertices in the sorted order
	 * @param <Type> - the type of the data stored in the graph's vertices
	 * @throws IllegalArgumentException - if the graph contains a cycle
	 */
	public static <Type> List<Type> topologicalSort(Graph<Type> graph) throws IllegalArgumentException {
		// Map to store the in-degree of each vertex
		Map<Type, Integer> inDegree = new HashMap<>();
		for (Type vertex : graph.vertices.keySet()) {
			inDegree.put(vertex, 0);
		}

		// Calculate the in-degree of each vertex
		for (Type vertex : graph.vertices.keySet()) {
            for (Iterator<Edge<Type>> it = graph.vertices.get(vertex).edges(); it.hasNext(); ) {
                Edge<Type> edge = it.next();
                Type neighbor = edge.getOtherVertex().getName();
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
		}

		// Queue to store the vertices with in-degree 0
		Queue<Type> doableTasks = new LinkedList<>();
		for (Type vertex : inDegree.keySet()) {
			if (inDegree.get(vertex) == 0) {
				doableTasks.add(vertex);
			}
		}

		// List to store the sorted order
		List<Type> output = new ArrayList<>();

		// Perform the topological sort
		while (!doableTasks.isEmpty()) {
			Type task = doableTasks.poll();
			output.add(task);
            for (Iterator<Edge<Type>> it = graph.vertices.get(task).edges(); it.hasNext(); ) {
                Edge<Type> edge = it.next();
                Type neighbor = edge.getOtherVertex().getName();
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    doableTasks.add(neighbor);
                }
            }
		}

		// Check if there was a cycle in the graph
		if (output.size() != graph.vertices.size()) {
			throw new IllegalArgumentException("The graph contains a cycle.");
		}

		return output;
	}

	/**
	 *
	 * BFS search for a path between two vertices in the graph.
	 *
	 * @param graph - the graph to search
	 * @param srcData - the data of the source vertex
	 * @param dstData - the data of the destination vertex
	 * @return a list of the vertices in the path, in order from the source to the destination
	 * @param <Type> - the type of the data stored in the graph's vertices
	 */
	public static <Type> List<Type> bfs(Graph<Type> graph, Type srcData, Type dstData) throws IllegalArgumentException {
		if (!graph.vertices.containsKey(srcData) || !graph.vertices.containsKey(dstData)) {
			throw new IllegalArgumentException("One or both vertices not found in the graph.");
		}

		Map<Type, Type> cameFrom = new HashMap<>();
		Queue<Type> nodesToVisit = new LinkedList<>();
		Set<Type> visited = new HashSet<>();

		nodesToVisit.add(srcData);
		visited.add(srcData);
		cameFrom.put(srcData, null);

		while (!nodesToVisit.isEmpty()) {
			Type current = nodesToVisit.poll();

			if (current.equals(dstData)) {
				return reconstructPath(cameFrom, dstData, srcData);
			}

			Vertex<Type> vertex = graph.vertices.get(current);
            for (Iterator<Edge<Type>> it = vertex.edges(); it.hasNext(); ) {
                Edge<Type> edge = it.next();
                Type neighbor = edge.getOtherVertex().getName();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    cameFrom.put(neighbor, current);
                    nodesToVisit.add(neighbor);
                }
            }
		}

		throw new IllegalArgumentException("No path exists between the two vertices.");
	}

	//helper method for bfs
	private static <Type> List<Type> reconstructPath(Map<Type, Type> cameFrom, Type target, Type start) {
		List<Type> path = new ArrayList<>();
		for (Type node = target; node != null; node = cameFrom.get(node)) {
			path.add(node);
		}
		Collections.reverse(path);
		return path;
	}


}
