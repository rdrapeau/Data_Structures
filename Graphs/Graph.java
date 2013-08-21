package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * This class represents a Graph object. The user may add edges and vertices until this object
 * represents the graph he or she wants to use to run various graph algorithms on.
 * 
 * @author RDrapeau
 */
public class Graph {
	
	/**
	 * All the vertices / nodes in the graph.
	 */
	private Map<Integer, Vertex> vertices;
	
	/**
	 * All the edges of the current graph.
	 */
	private List<Edge> edges;
	
	/**
	 * Construct a new Graph object to represent an adjacency list
	 */
	public Graph() {
		this.vertices = new HashMap<Integer, Vertex>();
		this.edges = new ArrayList<Edge>();
	}
	
	/**
	 * Adds the Vertex to the graph.
	 * 
	 * @param v The Vertex to add to the graph
	 * @throws IllegalArgumentException if the input is null
	 */
	public void addVertex(Vertex v) {
		if (v == null) {
			throw new IllegalArgumentException("v is null");
		}
		vertices.put(v.getID(), v);
	}
	
	/** 
	 * Removes the Vertex that matches the input id from the graph.
	 * 
	 * @param id ID of the vertex to remove
	 * @return The Vertex removed from the graph
	 */
	public Vertex removeVertex(int id) {
		return vertices.remove(id);
	}
	
	/**
	 * Returns the Vertex that matches the input id, if no match is found then a new Vertex is 
	 * created with the id and it is then placed in the graph.
	 * 
	 * @param id The ID of the Vertex wanted
	 * @return The Vertex with an ID of the input
	 */
	public Vertex getVertex(int id) {
		Vertex v = vertices.get(id);
		if (v == null) {
			v = new Vertex(id);
			addVertex(v);
		}
		return v;
	}

	/**
	 * Adds the edge into the list of all the edges in the graph.
	 * 
	 * @throws IllegalArgumentException if e is null
	 * @param e The Edge to be added to the graph
	 */
	public void addEdge(Edge e) {
		if (e == null) {
			throw new IllegalArgumentException("e is null.");
		}
		edges.add(e);
	}
	
	/**
	 * Removes the Edge at the index within the graph.
	 * 
	 * @throws IllegalArgumentException if the index is invalid
	 * @param index The index of the Edge to remove from the graph
	 * @return The Edge removed from the graph
	 */
	public Edge removeEdge(int index) {
		if (index < 0 || index > this.edges.size()) {
			throw new ArrayIndexOutOfBoundsException("Index is out of bounds. Size: " + this.edges.size() + ", Index: " + index);
		}
		return this.edges.remove(index);
	}
	
	/**
	 * Remove the input Edge from the graph.
	 * 
	 * @param e The Edge to remove from the graph
	 * @return A boolean representing if the remove was successful or not (true if yes, false if no)
	 */
	public boolean removeEdge(Edge e) {
		return this.edges.remove(e);
	}
	
	/**
	 * Updates the mapping of vertices to use the new ID of each Vertex and resets the explored value
	 * of each Vertex to be false. 
	 */
	public void updateIDs() {
		Map<Integer, Vertex> updated = new HashMap<Integer, Vertex>();
		for (Vertex v : this.vertices.values()) {
			updated.put(v.getID(), v);
			v.setExplored(false);
		}
		this.vertices = updated;
	}
	
	/**
	 * Returns whether or not there is a path from s to t in the graph using BFS.
	 * 
	 * @param s - Start Vertex
	 * @param t - End Vertex
	 * @return True if a path was found and false otherwise
	 */
	public boolean isPathBFS(Vertex s, Vertex t) {
		updateIDs(); // Reset explored status
		Queue<Vertex> q = new LinkedList<Vertex>();
		q.add(s);
		while (!q.isEmpty()) {
			Vertex v = q.remove();
			v.setExplored(true);
			if (t == v) {
				return true;
			}
			for (Edge e : v.getEdges()) {
				if (e.getTail() == v && !e.getHead().isExplored()) {
					q.add(e.getHead());
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns whether or not there is a path from s to t in the graph using DFS.
	 *  
	 * @param s - Start Vertex
	 * @param t - End Vertex
	 * @return True if a path was found and false otherwise
	 */
	public boolean isPathDFS(Vertex s, Vertex t) {
		s.setExplored(true);
		for (Edge e : s.getEdges()) {
			if (e.getTail() == s && !e.getHead().isExplored()) {
				if (e.getHead() == t || isPathDFS(e.getHead(), t)) {
					return true;
				} 
			}
		}
		return false;
	}

	/**
	 * Returns the size of the graph (number of vertices / nodes).
	 * 
	 * @return The number of vertices in the graph
	 */
	public int numberOfVertices() {
		return vertices.size();
	}
	
	/**
	 * Returns the number of edges in the graph.
	 * 
	 * @return The number of edges in the graph
	 */
	public int numberOfEdges() {
		return this.edges.size();
	}
	
	/**
	 * Outputs the contents of the graph to System.out.
	 */
	public void print() {
		for (Vertex v : this.vertices.values()) {
			System.out.println(v.toString());
		}
	}
	
	/**
	 * Returns a copy of the current graph.
	 * 
	 * @return A copy of the current Graph such that this != the graph returned
	 */
	public Graph clone() {
		Graph other = new Graph();
		for (int id : this.vertices.keySet()) { // Copy over all the vertices
			Vertex v = this.vertices.get(id);
			Iterator<Edge> iter = v.getIterator();
			while (iter.hasNext()) { // Copy over all the edges
				Edge e = iter.next();
				Vertex tail = other.getVertex(e.getTail().getID());
				Vertex head = other.getVertex(e.getHead().getID());
				if (head.getEdgeTo(tail) == null) {
					Edge otherE = new Edge(head, tail);
					tail.addEdge(otherE);
					head.addEdge(otherE);
					other.addEdge(otherE);
				}
			}
		}
		return other;
	}
}