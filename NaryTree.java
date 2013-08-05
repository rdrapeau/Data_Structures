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
	private int n;
	private Node root;
	private Random rand;
	
	public NaryTree(int n) {
		this.n = n;
		rand = new Random();
	}
	
	public void add(E element) {
		root = add(element, root);
	}
	
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
	
	public boolean contains(E element) {
		if (rand.nextBoolean()) { // Just for practice of BFS and DFS
			return BFS(element, root) != null;
		}
		return DFS(element, root) != null;
	}
	
	public void remove(E element) {
		root = remove(element, root);
	}
	
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
	
	private class Node {
		private Object[] children;
		private E element;
		private int numberOfChildren;
		
		public Node(E element) {
			this.children = new Object[n];
			this.element = element;
		}
		
		public Node childAt(int index) {
			return (Node) this.children[index];
		}
		
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
