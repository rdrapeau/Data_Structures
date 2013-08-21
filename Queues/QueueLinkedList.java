package Queues;

/**
 * Implementation of the Queue data structure using a Linked List to store the elements.
 * @author RDrapeau
 *
 * @param <E>
 */
public class QueueLinkedList<E> {
	/**
	 * The front of the Queue.
	 */
	private Node head;
	
	/**
	 * The end of the Queue.
	 */
	private Node tail;
	
	/**
	 * The size of the Queue.
	 */
	private int size;
	
	/**
	 * Adds the element to the end of the Queue.
	 * 
	 * @param element - The element to add to the end of the Queue.
	 */
	public void enqueue(E element) {
		if (size == 0) {
			head = new Node(element);
			tail = head;
		} else {
			tail.next = new Node(element);
			tail = tail.next;
		}
		size++;
	}
	
	/**
	 * Returns the first element in the Queue.
	 * 
	 * @throws IllegalStateException if the Queue is empty
	 * @return The first element in the Queue
	 */
	public E peek() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		return head.data;
	}
	
	/**
	 * Returns and removes the first element in the Queue.
	 * 
	 * @throws IllegalStateException if the Queue is empty
	 * @return The first element in the Queue
	 */
	public E dequeue() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		E element = head.data;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		size--;
		return element;
	}
	
	/**
	 * Returns the size of the Queue.
	 * 
	 * @return The size of the Queue
	 */
	public int size() {
		return size;
	}
	
	public String toString() {
		String result = "[" + (head != null ? head.data : "");
		Node current = head != null ? head.next : null;
		while (current != null) {
			result += ", " + current.data;
			current = current.next;
		}
		return result + "]";
	}
	
	/**
	 * Represents a Node in the Queue.
	 * @author RDrapeau
	 */
	private class Node {
		/**
		 * The data of this Node.
		 */
		private E data;
		
		/**
		 * The next Node after this one.
		 */
		private Node next;
		
		/**
		 * Constructs a new Node with no Nodes immediately after this one.
		 * 
		 * @param data - The data of this Node
		 */
		public Node(E data) {
			this.data = data;
		}
	}
}
