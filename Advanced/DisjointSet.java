package Advanced;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<E> {
	private Map<E, Node> convert;
	
	/**
	 * Constructs a new DisjoinSet
	 */
	public DisjointSet() {
		convert = new HashMap<E, Node>();
	}
	
	/**
	 * Returns whether or not the two elements belong to the same component.
	 * 
	 * @param first - The first element
	 * @param other - The second element
	 * @return True if the two elements belong to the same component and false otherwise
	 */
	public boolean find(E first, E other) {
		return root(convert.get(first)) == root(convert.get(other));
	}
	
	/**
	 * Returns the overall root of the child and compresses the tree.
	 * 
	 * @param child - The child Node
	 * @return The overall root of the component
	 */
	private Node root(Node child) {
		if (child != null) {
			while (child.parent != null) {
				child.parent = child.parent.parent;
				child = child.parent;
			}
		}
		return child;
	}
	
	private class Node {
		private Node parent;
		private E element;
		private int rank;
		
		public Node(E element, int rank, Node parent) {
			this.element = element;
			this.rank = rank;
			this.parent = parent;
		}
	}
}
