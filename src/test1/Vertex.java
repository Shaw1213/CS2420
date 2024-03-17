package test1;

import java.util.LinkedList;

public class Vertex<T> {
    private T data;
    private LinkedList<Vertex<T>> neighbors;

    private int incomingEdges;
    private Vertex<T> predecessor;
    private boolean isVisited;

    /**
     * Initializes a vertex with the provided data value.
     */
    public Vertex(T data) {
        this.data = data;
        this.neighbors = new LinkedList<>();
        this.incomingEdges = 0;
        this.predecessor = null;
        this.isVisited = false;
    }

    /**
     * Returns the data associated with the vertex.
     *
     * @return The data stored in the vertex.
     */
    public T getData() {
        return data;
    }

    /**
     * Checks if the vertex has been visited during graph traversal.
     *
     * @return True if the vertex has been visited, false otherwise.
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
     * Gets the predecessor vertex of this vertex in a path.
     *
     * @return The predecessor vertex.
     */
    public Vertex<T> getPredecessor() {
        return predecessor;
    }

    /**
     * Sets the predecessor vertex for this vertex in a path.
     *
     * @param predecessor The predecessor vertex.
     */
    public void setPredecessor(Vertex<T> predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * Connects the vertex to another vertex by adding it to its list of neighbors.
     *
     * @param destination The vertex to which a connection is established.
     */
    public void connectTo(Vertex<T> destination) {
        neighbors.add(destination);
        destination.adjustIncomingEdges(1);
    }

    /**
     * Gets a copy of the list of neighboring vertices.
     *
     * @return A list of neighboring vertices.
     */
    public LinkedList<Vertex<T>> getNeighbors() {
        return new LinkedList<>(neighbors);
    }

    /**
     * Returns the count of incoming edges to the vertex.
     *
     * @return The count of incoming edges.
     */
    public int incomingEdgeCount() {
        return incomingEdges;
    }

    /**
     * Adjusts the count of incoming edges to the vertex.
     *
     * @param amount The amount by which to adjust the incoming edge count (e.g., +1 or -1).
     */
    public void adjustIncomingEdges(int amount) {
        this.incomingEdges += amount;
    }
}

