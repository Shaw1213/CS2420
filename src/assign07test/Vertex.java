package assign07test;

import java.util.LinkedList;

/**
 * This class represents a vertex (AKA node) in a directed graph. The vertex is
 * generic.
 * 
 * @author Erin Parker and Shawn Zhang
 * @version March 14, 2024
 */
public class Vertex<T>{

	// used to id the Vertex
	private T data;

	// adjacency list
	private LinkedList<Vertex<T>> adj;

	private Vertex<T> predecessor;

	private int incomingEdges;

	private boolean isVisited;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this Vertex
	 */
	public Vertex(T name) {
		this.data = data;
		this.adj = new LinkedList<Vertex<T>>();
		this.incomingEdges = 0;
		this.isVisited = false;
		this.predecessor = null;
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public T getData() {
		return data;
	}

	/**
	 * @return the predecessor vertex of this vertex in a path
	 */
	public boolean hasBeenVisited() {
		return isVisited;
	}

	/**
	 * Marks the vertex as visited.
	 */
	public void markVisited() {
		this.isVisited = true;
	}

	/**
	 * Marks the vertex as unvisited.
	 */
	public void markUnvisited() {
		this.isVisited = false;
	}

	/**
	 * @return the predecessor vertex of this vertex in a path
	 */
	public Vertex<T> getPredecessor() {
		return predecessor;
	}

	/**
	 * Sets the predecessor vertex for this vertex in a path.
	 *
	 * @param predecessor - the predecessor vertex
	 */
	public void setPredecessor(Vertex<T> predecessor) {
		this.predecessor = predecessor;
	}

	/**
	 * Connects this vertex to the given vertex.
	 *
	 * @param destination - the vertex to be connected
	 */
	public void connectTo(Vertex<T> destination) {
		adj.add(destination);
		destination.adjustIncomingEdges(1);
	}

	/**
	 * @return the adjacency list of the vertex
	 */
	public LinkedList<Vertex<T>> getAdj(){
		return new LinkedList<>(adj);
	}

	/**
	 * @return the number of incoming edges.
	 */
	public int getIncomingEdges() {
		return incomingEdges;
	}

	/**
	 * Adjusts the number of incoming edges by value.
	 *
	 * @param value - the value of incoming edges to be adjusted.
	 */
	public void adjustIncomingEdges(int value) {
		incomingEdges += value;
	}
}