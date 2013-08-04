package Cracking_The_Coding_Interview;

/**
 * Implementation of the DoublyLinkedList data structure.
 * @author RDrapeau
 *
 * @param <E>
 */
public class DoubleLinkedList<E extends Comparable> {
	/**
	 * The first node in the list.
	 */
	private Node head;
	
	/**
	 * The last node in the list.
	 */
	private Node tail;
	
	/**
	 * The number of elements in the list.
	 */
	private int size;
	
	/**
	 * Adds the element to the end of the list.
	 * 
	 * @param data - The element to add
	 */
	public void add(E data) {
		add(data, size);
	}
	
	/**
	 * Adds the element to the index in the list - shifting everything else to the right.
	 * 
	 * @param data - The element to add
	 * @param index - The index to add the element at (0 based)
	 * @throws IllegalArgumentException if the index is not valid
	 */
	public void add(E data, int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException();
		}
		Node newNode = new Node(data);
		if (index <= size / 2) { // Contains empty front case
			if (index == 0) { // Index is 0
				newNode.next = head;
				head = newNode;
				if (tail == null) {
					tail = newNode;
				}
			} else {
				Node current = head;
				for (int i = 0; i < index; i++) {
					current = current.next;
				}
				insert(newNode, current);
			}
		} else { 
			if (index == size) {
				tail.next = newNode;
				newNode.previous = tail;
				tail = newNode;
			} else {
				Node current = tail;
				for (int i = size - 1; i > index; i--) {
					current = current.previous;
				}
				insert(newNode, current);
			}
		}
		size++;
	}
	
	/**
	 * Inserts the node in place at the current node shifting current and everything else to the right.
	 * 
	 * @param insert - The node to add
	 * @param current - The node to be shifted to the right
	 */
	private void insert(Node insert, Node current) {
		insert.next = current;
		insert.previous = current.previous;
		current.previous = insert;
		insert.previous.next = insert;
	}
	
	/**
	 * Returns the element of the Node that is removed at the index.
	 * 
	 * @param index - The index of the Node to remove from the list
	 * @throws IllegalArgumentException if the index is not valid
	 * @return The data of the element removed
	 */
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		Node current;
		if (index <= size / 2) {
			current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
		} else {
			current = tail;
			for (int i = size - 1; i > index; i--) {
				current = current.previous;
			}
		}
		removeNode(current);
		return current.data;
	}
	
	/**
	 * Removes the Node in the list that contains the data.
	 * 
	 * @param data - The data of the element to remove from the list
	 * @return True if the element was removed from the list and false otherwise
	 */
	public boolean remove(E data) {
		Node current = head;
		while (current != null && current.data != data) {
			current = current.next;
		}
		if (current != null) {
			removeNode(current);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the current Node from the list.
	 * 
	 * @param current - The Node to remove
	 */
	private void removeNode(Node current) {
		if (current.previous != null) {
			current.previous.next = current.next;
		} else { // Front of the List
			head = current.next;
			if (head != null) {
				head.previous = null;
			}
		}
		if (current.next != null) {
			current.next.previous = current.previous;
		} else { // End of the List
			tail = tail.previous;
			tail.next = null;
		}
		size--;
	}
	
	/**
	 * Returns the size of the list.
	 * 
	 * @return The size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns a String of the list.
	 */
	public String toString() {
		String result = "[" + (head == null ? "" : head.data);
		Node current = head == null ? null : head.next;
		while (current != null) {
			result += ", " + current.data;
			current = current.next;
		}
		return result + "]";
	}
	
	/**
	 * This represents a single Node in the DoublyLinkedList
	 * @author RDrapeau
	 */
	private class Node {
		/**
		 * The Node immediately after this Node.
		 */
		private Node next;
		
		/**
		 * The Node immediately before this Node.
		 */
		private Node previous;
		
		/**
		 * The data of this Node.
		 */
		private E data;
		
		/**
		 * Constructs a new Node with no Nodes connected to it.
		 * @param data
		 */
		public Node(E data) {
			this(data, null, null);
		}
		
		/**
		 * Constructs a new Node in between next and previous.
		 * 
		 * @param data - The data of the Node
		 * @param next - The node after this one
		 * @param previous - The node before this one
		 */
		public Node(E data, Node next, Node previous) {
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
	}
}
