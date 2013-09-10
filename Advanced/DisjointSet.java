package Advanced;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the DisjoinSet (Union-Find) data structure
 * @author RDrapeau
 *
 * @param <E>
 */
public class DisjointSet<E> {
	/**
	 * A forest representing the components.
	 */
	private Map<E, Node> convert; 
	
	/**
	 * Constructs a new DisjointSet.
	 */
	public DisjointSet() {
		this(null);
	}
	
	/**
	 * Constructs a new DisjointSet over the data.
	 */
	public DisjointSet(Collection<E> data) {
		convert = new HashMap<E, Node>();
		if (data != null) {
			for (E datum : data) {
				convert.put(datum, new Node());
			}
		}
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
	 * Replace components with their union.
	 * 
	 * @param first - The first element
	 * @param second - The second element
	 */
	public void union(E first, E second) {
		Node firstRoot = root(updateForest(first));
		Node secondRoot = root(updateForest(second));
		if (firstRoot.rank < secondRoot.rank) { // First tree is smaller
			firstRoot.parent = secondRoot;
			secondRoot.rank += firstRoot.rank;
		} else { // Second tree is smaller
			secondRoot.parent = firstRoot;
			firstRoot.rank += secondRoot.rank;
		}
	}
	
	/**
	 * Updates the forest to contain the element if it does not already.
	 * 
	 * @param element - The element to check
	 * @return The Node representing the element
	 */
	private Node updateForest(E element) {
		if (!convert.containsKey(element)) {
			convert.put(element, new Node());
		}
		return convert.get(element);
	}
	
	/**
	 * Returns the overall root of the child.
	 * 
	 * @param child - The child Node
	 * @return The overall root of the component
	 */
	private Node root(Node child) {
		if (child != null) {
			while (child.parent != child) { // Move up the tree
				child.parent.rank--; // Update Rank
				child.parent = child.parent.parent; // Collapse tree
				child = child.parent;
			}
		}
		return child;
	}
	
	/**
	 * Represents a root of a component in the forest.
	 * @author RDrapeau
	 */
	private class Node {
		/**
		 * The parent of this root.
		 */
		private Node parent;
		
		/**
		 * The rank of this tree.
		 */
		private int rank;
		
		/**
		 * Constructs a new Node with the element and a rank of 1 and a null parent.
		 * 
		 * @param element - The data of this Node
		 */
		public Node() {
			this.rank = 1;
			this.parent = this;
		}
	}
}
