package Cracking_The_Coding_Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementation of the BinarySearchTree data structure.
 * @author RDrapeau
 *
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable> {
	/**
	 * The overall root of the tree.
	 */
	private Node root;
	
	/**
	 * Overloads the second constructor so the default constructor can be used.
	 */
	public BinarySearchTree() {
		// Overload the second constructor
	}
	
	/**
	 * Constructs a new BinarySearchTree over the Comparable Array.
	 * 
	 * @param a - The array of elements to create the tree out of. This array will be sorted.
	 */
	public BinarySearchTree(Comparable[] a) {
		Arrays.sort(a);
		root = createTree(a, 0, a.length, null);
	}
	
	/**
	 * Creates a perfectly balanced binary tree from the elements in a. The median element is 
	 * recursively chosen as the root to build the tree.
	 * 
	 * @param a - The array of elements to build the tree
	 * @param min - The minimum index to use (inclusive)
	 * @param max - The maximum index to use (exclusive)
	 * @param parent - The parent of the Node to be made
	 * @return The overall root of the subtree 
	 */
	private Node createTree(Comparable[] a, int min, int max, Node parent) {
		Node root = null;
		if (min < max) {
			int middle = (max + min) / 2;
			root = new Node((E) a[middle], parent);
			root.left = createTree(a, min, middle, root);
			root.right = createTree(a, middle + 1, max, root);
			root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
		}
		return root;
	}
	
	/**
	 * Adds the element into the Binary Search Tree.
	 * 
	 * @param element - The element to add
	 */
	public void add(E element) {
		root = add(element, root, null);
	}
	
	/**
	 * Adds the element into the BST with the correct parent.
	 * 
	 * @param element - The element to add
	 * @param root - The root of the subtree 
	 * @param parent - The parent of the current root
	 * @return The root of the subtree with the element added
	 */
	private Node add(E element, Node root, Node parent) {
		if (root == null) {
			root = new Node(element, parent);
		} else {
			if (element.compareTo(root.element) < 1) { // Equivalents go on left
				root.left = add(element, root.left, root);
			} else {
				root.right = add(element, root.right, root);
			}
			root.size++;
		}
		return root;
	}
	
	/**
	 * Removes the element from the BST. The BST is unaffected if the element is not present.
	 * 
	 * @param element - The element to remove
	 */
	public void remove(E element) {
		root = remove(element, root);
	}
	
	/**
	 * Removes the element from the subtree.
	 * 
	 * @param element - The element to remove
	 * @param root - The root of the subtree
	 * @return The root of the subtree with the element removed
	 */
	private Node remove(E element, Node root) {
		if (root != null) {
			if (element == root.element) {
				if (root.left == null) { // Swap right tree
					root = root.right;
				} else if (root.right == null) { // Swap left tree
					root = root.left;
				} else { // Swap max element from left subtree and delete it
					E maxElement = findMax(root.left);
					root.element = maxElement;
					root.left = remove(maxElement, root.left);
				}
			} else if (element.compareTo(root.element) < 1) {
				root.left = remove(element, root.left);
			} else {
				root.right = remove(element, root.right);
			}
			if (root != null) {
				root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
			}
		}
		return root;
	}
	
	/**
	 * Returns whether or not the subtree contains the element.
	 * 
	 * @param element - The element to search for
	 * @return True if the element is in the tree and false otherwise
	 */
	public boolean contains(E element) {
		return contains(element, root);
	}
	
	/**
	 * Returns whether or not the subtree contains the element.
	 * 
	 * @param element - The element to search for
	 * @param root - The root of the subtree
	 * @return True if the element is in the subtree and false otherwise
	 */
	private boolean contains(E element, Node root) {
		if (root != null) {
			if (element == root.element) {
				return true;
			}
			int match = element.compareTo(root.element);
			if (match < 1) {
				return contains(element, root.left);
			} else {
				return contains(element, root.right);
			}
		}
		return false;
	}
	
	/**
	 * Returns whether or not the node is a child of the subtree.
	 * 
	 * @param node - The child
	 * @param root - The parent
	 * @return True if the node is a child of the root and false otherwise
	 */
	private boolean isChild(Node node, Node root) {
		if (root != null) {
			if (node == root) {
				return true;
			}
			int match = node.element.compareTo(root.element);
			if (match < 1) {
				return isChild(node, root.left);
			} else {
				return isChild(node, root.right);
			}
		}
		return false;
	}
	
	/**
	 * Returns the highest common ancestor Node between two Nodes.
	 * 
	 * @param nodeOne - The first Node
	 * @param nodeTwo - The second Node
	 * @param root - The root of the tree to check
	 * @return The first common ancestor between the two Nodes (null if none are found)
	 */
	private Node commonAncestor(Node nodeOne, Node nodeTwo, Node root) {
		if (isChild(nodeOne, root.left) && isChild(nodeTwo, root.left)) {
			return commonAncestor(nodeOne, nodeTwo, root.left);
		} else if (isChild(nodeOne, root.right) && isChild(nodeOne, root.right)) {
			return commonAncestor(nodeOne, nodeTwo, root.right);
		}
		return root;
	}
	
	/**
	 * Returns the element that comes immediately before the element in the tree.
	 * 
	 * @param element - The successor to the predecessor
	 * @return The element that comes immediately before the element
	 */
	public E predecessor(E element) {
		return predecessor(element, root);
	}
	
	/**
	 * Returns the element that comes immediately before the element in the subtree.
	 * 
	 * @param element - The successor to the predecessor
	 * @param root - The root of the subtree
	 * @return The element that comes immediately before the element
	 */
	private E predecessor(E element, Node root) {
		if (root != null) {
			if (root.element == element) {
				if (root.left != null) {
					return findMax(root.left);
				} else {
					Node current = root;
					do {
						current = current.parent;
					} while (current != null && current.element.compareTo(element) >= 0);
					if (current != null) {
						return current.element;
					}
				}
			} else if (element.compareTo(root.element) < 1) {
				return predecessor(element, root.left);
			} else {
				return predecessor(element, root.right);
			}
		}
		return null;
	}
	
	/**
	 * Returns the element that comes immediately after the input element in the tree.
	 * 
	 * @param element - The predecessor to the successor
	 * @return The element immediately after
	 */
	public E successor(E element) {
		return successor(element, root);
	}
	
	/**
	 * Returns the element that comes immediately after the input element in the subtree.
	 * 
	 * @param element - The predecessor to the successor
	 * @param root - The root of the subtree
	 * @return The element immediately after
	 */
	private E successor(E element, Node root) {
		if (root != null) {
			if (root.element == element) {
				if (root.right != null) {
					return findMin(root.right);
				} else {
					Node current = root;
					do {
						current = current.parent;
					} while (current != null && current.element.compareTo(element) <= 0);
					if (current != null) {
						return current.element;
					}
				}
			} else if (element.compareTo(root.element) < 1) {
				return successor(element, root.left);
			} else {
				return successor(element, root.right);
			}
		}
		return null;
	}
	
	/**
	 * Returns the ith smallest element in the tree (0 < 1 <= size(root)).
	 * 
	 * @param i - The order statistic
	 * @return The ith smallest element in the tree
	 */
	public E ithStat(int i) {
		return ithStat(i, root);
	}
	
	/**
	 * Returns the ith smallest element in the tree (0 < 1 <= size(root)).
	 * 
	 * @param i - The order statistic
	 * @param root - The root of the subtree
	 * @return The ith smallest element in the subtree
	 */
	private E ithStat(int i, Node root) {
		if (root != null) {
			int leftSize = root.left == null ? 0 : root.left.size;
			if (i == leftSize + 1) {
				return root.element;
			} else if (i <= leftSize) {
				return ithStat(i, root.left);
			} else {
				return ithStat(i - leftSize - 1, root.right);
			}
		}
		return null;
	}
	
	/**
	 * Returns the max element of the BST.
	 * 
	 * @return The max element
	 */
	public E findMax() {
		return findMax(root);
	}
	
	/**
	 * Returns the max element in the subtree.
	 * 
	 * @param root - The root of the subtree
	 * @return The max element in the subtree
	 */
	private E findMax(Node root) {
		if (root != null) {
			if (root.right != null) {
				return findMax(root.right);
			}
			return root.element;
		}
		return null;
	}
	
	/**
	 * Returns the minimum element of the BST.
	 * 
	 * @return The minimum element
	 */
	public E findMin() {
		return findMin(root);
	}
	
	/**
	 * Returns the minimum element in the subtree.
	 * 
	 * @param root - The root of the subtree
	 * @return The minimum element in the subtree
	 */
	private E findMin(Node root) {
		if (root != null) {
			if (root.left != null) {
				return findMin(root.left);
			}
			return root.element;
		}
		return null;
	}
	
	/**
	 * Returns whether or not the tree is balanced.
	 * 
	 * @return True if the tree is balanced and false otherwise
	 */
	public boolean isBalanced() {
		return isBalanced(root);
	}
	
	/**
	 * Returns whether or not the subtree is balanced (Max and Min heights differ by at most 1).
	 * 
	 * @param root - The root of the subtree
	 * @return True if the tree is balanced and false otherwise
	 */
	private boolean isBalanced(Node root) {
		return maxDepth() - minDepth() <= 1;
	}
	
	/**
	 * Returns whether or not the tree holds a valid binary search tree structure.
	 * 
	 * @return True if the tree is a binary search tree and false otherwise
	 */
	public boolean isBinarySearchTree() {
		return isBinarySearchTree(root, findMax(), findMin());
	}
	
	/**
	 * Returns whether or not the subtree holds a valid binary search tree structure.
	 * 
	 * @param root - The root of the subtree
	 * @param max - The maximum possible element
	 * @param min - The smallest possible element
	 * @return True if the tree is a binary search tree and false otherwise
	 */
	private boolean isBinarySearchTree(Node root, E max, E min) {
		if (root == null) {
			return true;
		} else {
			return isBinarySearchTree(root.left, root.element, min) && root.element.compareTo(max) < 1 
					&& root.element.compareTo(min) > -1 && isBinarySearchTree(root.right, max, root.element);
		}
	}
	
	/**
	 * Returns whether or not the node is a leaf node (has no children).
	 * 
	 * @param root - The root of the subtree
	 * @return True if the root has no children and false otherwise
	 */
	private boolean isLeaf(Node root) {
		return root != null && root.left == null && root.right == null;
	}
	
	/**
	 * Returns a List of lists that contain each element on each level of the tree.
	 * 
	 * @return A list of lists of elements by level
	 */
	public List<List<E>> listByLevel() {
		List<List<E>> levels = new ArrayList<List<E>>();
		Queue<Node> q = new LinkedList<Node>(); // Current Parents
		Queue<Node> children = new LinkedList<Node>(); // Current Children
		if (root != null) {
			q.add(root);
			levels.add(new ArrayList<E>());
		}
		while (!q.isEmpty()) { // Like BFS
			Node n = q.remove();
			if (n.left != null) {
				children.add(n.left);
			}
			if (n.right != null) {
				children.add(n.right);
			}
			levels.get(levels.size() - 1).add(n.element);
			if (q.isEmpty() && !children.isEmpty()) { // Move down one level
				while (!children.isEmpty()) {
					q.add(children.remove());
				}
				levels.add(new ArrayList<E>());
			}
		}
		return levels;
	}
	
	/**
	 * Prints the tree out level by level to standard out.
	 */
	public void printByLevel() {
		List<List<E>> elements = listByLevel();
		for (List<E> level : elements) {
			for (E node : level) {
				System.out.print(node + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Performs an in-order traversal of the tree printing out each element
	 */
	public void inOrder() {
		inOrder(root);
		System.out.println();
	}
	
	/**
	 * Performs an in-order traversal of the tree starting at the root of the subtree.
	 * 
	 * @param root - The root of the subtree
	 */
	private void inOrder(Node root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(" " + root.element + " ");
			inOrder(root.right);
		}
	}
	
	/**
	 * Returns the number of nodes in the tree.
	 * 
	 * @return The number of nodes
	 */
	public int size() {
		return size(root);
	}
	
	/**
	 * Returns the number of nodes in the subtree.
	 * 
	 * @param root - The root of the subtree
	 * @return The number of nodes at the subtree
	 */
	private int size(Node root) {
		int count = 0;
		if (root != null) {
			count += 1 + size(root.left) + size(root.right);
		}
		return count;
	}
	
	/**
	 * Returns the height of the overall tree (Number of nodes in the longest path).
	 * 
	 * @return The height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	/**
	 * Returns the height of the subtree.
	 * 
	 * @param root - The root of the subtree
	 * @return The height of the subtree
	 */
	private int height(Node root) {
		int count = 0;
		if (root != null) {
			count = 1 + Math.max(height(root.left), height(root.right));
		}
		return count;
	}
	
	/**
	 * Returns the maxmimum depth in the tree.
	 * 
	 * @return The maximum depth
	 */
	public int maxDepth() {
		return height() - 1;
	}
	
	/**
	 * Returns the minimum depth in the tree.
	 * 
	 * @return The minimum height
	 */
	public int minDepth() {
		return minDepth(root) - 1;
	}
	
	/**
	 * Returns the minimum depth in the tree.
	 * 
	 * @param root - The root of the subtree
	 * @return The minimum depth
	 */
	private int minDepth(Node root) {
		int count = 0;
		if (root != null) {
			count = 1 + Math.min(minDepth(root.left), minDepth(root.right));
		}
		return count;
	}
	
	/**
	 * This represents a Node in the tree.
	 * @author RDrapeau
	 */
	private class Node {
		/**
		 * The parent of this Node.
		 */
		private Node parent;
		
		/**
		 * The left child of this Node.
		 */
		private Node left;
		
		/**
		 * The right child of this Node.
		 */
		private Node right;
		
		/**
		 * The size of the subtree with the root as this node. (size = size(left) + size(right) + 1)
		 */
		private int size;
		
		/**
		 * The data element stored at this node.
		 */
		private E element;
		
		/**
		 * Constructs a new default Node with element as data and parent as the parent.
		 * 
		 * @param element - The data of the Node
		 * @param parent - Node's parent
		 */
		public Node(E element, Node parent) {
			this(element, parent, null, null, 1);
		}
		
		/**
		 * Constructs a new Node.
		 * 
		 * @param element - The data of the Node
		 * @param parent - Node's parent
		 * @param left - Left child
		 * @param right - Right child
		 * @param size - Size of the node
		 */
		public Node (E element, Node parent, Node left, Node right, int size) {
			this.element = element;
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.size = size;
		}
		
		public String toString() {
			return element.toString();
		}
	}
}