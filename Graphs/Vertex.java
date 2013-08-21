package Graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents a Vertex object to be used in an undirected graph. Each Vertex has an ID
 * and a set of edges that consist of this Vertex.
 * 
 * @author RDrapeau
 */
public class Vertex implements Comparable {
	/**
	 * The ID of the Vertex.
	 */
	private int id;
	
	/**
	 * The minimum distance to this object from a vertex.
	 */
	private double minimumLength;
	
	/**
	 * A boolean representing if this Vertex has been explored or not.
	 */
	private boolean explored;
	
	/**
	 * The Edges that are connected with this Vertex.
	 */
	private Set<Edge> edges;
	
	/**
	 * Constructs a new Vertex that is unexplored.
	 * 
	 * @param id - The ID of the Vertex
	 */
	public Vertex(int id) {
		this.id = id;
		this.edges = new HashSet<Edge>();
		this.explored = false;
		this.minimumLength = Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Adds the Edge to the Edges this Vertex is made up of.
	 * 
	 * @param e - The Edge to add
	 * @throws IllegalArgumentException if e is null
	 */
	public void addEdge(Edge e) {
		if (e == null) {
			throw new IllegalArgumentException("e is null.");
		}
		edges.add(e);
	}
	
	/**
	 * Removes an Edge from the Edges this Vertex is made up of.
	 * 
	 * @param e - The Edge to remove
	 * @return True if successful and false otherwise
	 */
	public boolean removeEdge(Edge e) {
		return this.edges.remove(e);
	}
	
	/**
	 * Returns the Edge that this Vertex and the input Vertex make up if any.
	 * 
	 * @param other - The second Vertex to make up the Edge
	 * @return The Edge that is made between the two Vertices (null if there are none)
	 */
	public Edge getEdgeTo(Vertex other) {
		for (Edge e : edges) {
			if (e.contains(this, other)) {
				return e;
			}
		}
		return null; 
	}
	
	/**
	 * Returns the Edges that are connected with this Vertex.
	 * 
	 * @return All the Edges connected with this Vertex
	 */
	public Set<Edge> getEdges() {
		return this.edges;
	}
	
	/**
	 * Returns an Iterator for the Edges that this Vertex is connected with.
	 * 
	 * @return An Iterator for the Edges that this Vertex is connected with
	 */
	public Iterator<Edge> getIterator() {
		return this.edges.iterator();
	}
	
	/**
	 * Returns whether or not the Vertex has been explored.
	 * 
	 * @return True if the Vertex has been explored and false otherwise
	 */
	public boolean isExplored() {
		return this.explored;
	}
	
	/**
	 * Sets the explored state of the Vertex to be the input.
	 * 
	 * @param explored - Whether or not this node has been explored
	 */
	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	
	/**
	 * Returns the ID of the Vertex.
	 * 
	 * @return The ID that this Vertex contains
	 */
	public int getID() {
		return this.id;
	}
	
	/**
	 * Sets the Vertex's ID to be id.
	 * 
	 * @param id - The new ID of the Vertex
	 * @see Make sure to update the mapping of the Graph in order to stay consistant 
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the minimum length of the path from another Vertex to this one.
	 * 
	 * @return The minimum distance from another Vertex
	 */
	public double getMinimumLength() {
		return this.minimumLength;
	}
	
	/**
	 * Sets the Vertex's minimum length to be length.
	 * 
	 * @param length - The new minimum length of the path from another Vertex to this one
	 */
	public void setMinimumLength(double length) {
		this.minimumLength = length;
	}
	
	public String toString() {
		return id + ": " + edges;
	}

	public int compareTo(Object other) {
		return (int) Math.signum(this.minimumLength - ((Vertex) other).minimumLength);
	}
}