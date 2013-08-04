package Cracking_The_Coding_Interview;

/**
 * This class represents an Edge object. An Edge is a connection between two Vertices or Nodes. 
 * This is designed for an undirected graph and therefore does not have any weight.
 * 
 * @author RDrapeau
 */
public class Edge {
	/**
	 * The tail and the head of the Edge.
	 */
	private Vertex[] ends; 
	
	/**
	 * The weight of the Edge.
	 */
	private double weight;
	
	/**
	 * Constructs an Edge given the head and tail Vertices as input.
	 * 
	 * @param first - The tail of the Edge
	 * @param second - The head of the Edge
	 * @throws IllegalArgumentException if either of the inputs are null
	 */
	public Edge(Vertex tail, Vertex head) {
		this(tail, head, 0);
	}
	
	/**
	 * Constructs an Edge given the head and tail Vertices as input.
	 * 
	 * @param first - The tail of the Edge
	 * @param second - The head of the Edge
	 * @param weight - The weight of the Edge
	 * @throws IllegalArgumentException if either of the inputs are null
	 */
	public Edge(Vertex tail, Vertex head, double weight) {
		if (tail == null || head == null) {
			throw new IllegalArgumentException("Vertices are null.");
		}
		this.ends = new Vertex[2];
		ends[0] = tail;
		ends[1] = head;
		this.weight = weight;
	}
	
	/**
	 * Returns the tail of the Edge.
	 * 
	 * @return The tail Vertex of the Edge
	 */
	public Vertex getTail() {
		return ends[0];
	}
	
	/**
	 * Returns the head of the Edge.
	 * 
	 * @return The head Vertex of the Edge
	 */
	public Vertex getHead() {
		return ends[1];
	}
	
	/**
	 * Returns the weight of the Edge.
	 * 
	 * @return The weight of the Edge
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * Returns whether or not the Edge is made up of the input Vertices.
	 * 
	 * @param first - A Vertex to check
	 * @param second - The second Vertex to check
	 * @return True if the Edge is made up of the input Vertices and false otherwise
	 */
	public boolean contains(Vertex first, Vertex second) {
		return ((ends[0] == first && ends[1] == second) || (ends[0] == second && ends[1] == first));
	}
	
	/**
	 * Returns the tail if passed the head and returns the head if passed the tail of the Edge.
	 * 
	 * @param v - The head or tail of the Edge
	 * @throws IllegalArgumentException if the Edge does not contain the Vertex v
	 * @return The opposite Vertex of the Edge
	 */
	public Vertex getOpposite(Vertex v) {
		if (ends[0] != v && ends[1] != v) {
			throw new IllegalArgumentException("Edge does not contain " + v);
		}
		return ends[0] == v ? ends[1] : ends[0]; 
	}
	
	/**
	 * Contracts the Edge by replacing the old Vertex with the new Vertex.
	 * @param oldV - The Vertex to replace
	 * @param newV - The new Vertex
	 * @throws IllegalArgumentException if oldV is not in the edge
	 */
	public void replaceVertex(Vertex oldV, Vertex newV) {
		if (ends[0] != oldV && ends[1] != oldV) {
			throw new IllegalArgumentException();
		}
		if (ends[0] == oldV) {
			ends[0] = newV;
		} else { // ends[1] == oldV
			ends[1] = newV;
		}
	}

	public String toString() {
		return "(" + ends[0].getID() + ", " + ends[1].getID() + ")";
	}
}
