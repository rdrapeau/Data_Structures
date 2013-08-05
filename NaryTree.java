package Cracking_The_Coding_Interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Implementation of the N-ary Tree data structure.
 * @author RDrapeau
 *
 * @param <E>
 */
public class NaryTree<E> {
	/**
	 * The number of children a Node can have.
	 */
	private int n;
	
	/**
	 * The overall root of the tree
	 */
	private Node root;
	
	/**
	 * Random object used to determine where to place new Nodes.
	 */
	private Random rand;
	
	/**
	 * Constructs a new N-Ary Tree.
	 * 
	 * @param n - The number of children a Node can have
	 */
	public NaryTree(int n) {
		this.n = n;
		rand = new Random();
	}
	
	/**
	 * Adds an element into the tree.
	 * 
	 * @param element - The element to add
	 */
	public void add(E element) {
		root = add(element, root);
	}
	
	/**
	 * Adds an element into the tree.
	 * 
	 * @param element - The element to add
	 * @param root - The root of the subtree
	 * @return The root of the subtree with the new Node added
	 */
	private Node add(E element, Node root) {
		if (root == null) {
			root = new Node(element);
		} else if (root.numberOfChildren < root.children.length) {
			root.addChild(element);
		} else {
			int index = rand.nextInt(root.numberOfChildren);
			Node next = root.childAt(index);
			next = add(element, next);
		}
		return root;
	}
	
	/**
	 * Returns whether or not the tree contains the element using BFS or DFS (for practice)
	 * 
	 * @param element - The element to search for
	 * @return True if the element is in the tree and false otherwise
	 */
	public boolean contains(E element) {
		if (rand.nextBoolean()) { // Just for practice of BFS and DFS
			return BFS(element, root) != null;
		}
		return DFS(element, root) != null;
	}
	
	/**
	 * Removes the element from the tree.
	 * 
	 * @param element - The element to remove
	 */
	public void remove(E element) {
		root = remove(element, root);
	}
	
	/**
	 * Removes the element from the subtree at root. 
	 * 
	 * @param element - The element to remove
	 * @param root - The root of the subtree
	 * @return The root of the subtree with all occurrences of element removed
	 */
	private Node remove(E element, Node root) {
		if (root != null) {
			if (element == root.element) {
				if (root.numberOfChildren == 0) {
					root = null;
				} else if (root.numberOfChildren == 1) {
					root = root.childAt(0);
				} else {
					Node leaf = getLeaf(root);
					E temp = root.element;
					root.element = leaf.element;
					leaf.element = temp;
					root = remove(temp, root);
					root.numberOfChildren--;
				}
			} else {
				for (int i = 0; i < root.numberOfChildren; i++) {
					root.children[i] = remove(element, root.childAt(i));
				}
			}
		}
		return root;
	}
	
	/**
	 * Returns the farthest right leaf node of the subtree.
	 * 
	 * @param root - The root of the subtree
	 * @return The leaf Node on the far right of the subtree (null if none)
	 */
	private Node getLeaf(Node root) {
		if (root != null) {
			if (root.numberOfChildren == 0) {
				return root;
			} else {
				return getLeaf(root.childAt(root.numberOfChildren - 1));
			}
		}
		return null;
	}
	
	/**
	 * Performs a Breadth-First-Search on the subtree looking for the element.
	 * 
	 * @param element - The element to search for
	 * @param root - The root of the subtree
	 * @return The Node containing the element
	 */
	private Node BFS(E element, Node root) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while (!q.isEmpty()) {
			Node s = q.remove();
			if (element == s.element) {
				return s;
			}
			for (int i = 0; i < s.numberOfChildren; i++) {
				q.add(s.childAt(i));
			}
		}
		return null;
	}
	
	/**
	 * Performs a Depth-First-Search on the subtree looking for the element.
	 * 
	 * @param element - The element to search for
	 * @param root - The root of the subtree
	 * @return The Node containing the element
	 */
	private Node DFS(E element, Node root) {
		if (root != null) {
			if (element == root.element) {
				return root;
			}
			for (int i = 0; i < root.numberOfChildren; i++) {
				Node s = DFS(element, root.childAt(i));
				if (s != null) {
					return s;
				}
			}
		}
		return null;
	}
	
	/**
	 * Represents a single Node in the tree.
	 * @author RDrapeau
	 */
	private class Node {
		/**
		 * The children of this Node.
		 */
		private Object[] children;
		
		/**
		 * The data of this Node.
		 */
		private E element;
		
		/**
		 * The number of immediate children this Node has.
		 */
		private int numberOfChildren;
		
		/**
		 * Constructs a new Node with element as the data.
		 * 
		 * @param element - The data of the element
		 */
		public Node(E element) {
			this.children = new Object[n];
			this.element = element;
		}
		
		/**
		 * Returns the child Node at a specific index.
		 * 
		 * @param index - The index of the child Node
		 * @throws IllegalStateException if the index is invalid
		 * @return The child Node at the index
		 */
		public Node childAt(int index) {
			if (index < 0 || index >= numberOfChildren) {
				throw new IllegalStateException();
			}
			return (Node) this.children[index];
		}
		
		/**
		 * Adds a new Node as the child of this one.
		 * 
		 * @param element - The element of the child Node to be added
		 * @throws IllegalStateException if the array of children Nodes is already full 
		 */
		public void addChild(E element) {
			if (numberOfChildren == children.length) {
				throw new IllegalStateException();
			}
			children[numberOfChildren++] = new Node(element);
		}
		
		public String toString() {
			return element + ": " + Arrays.toString(children);
		}
	}
}
