package Cracking_The_Coding_Interview;

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
		tail = head == null ? null : tail; // Adjust tail if the list is null
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
	
	/**
	 * Returns a String representing the Queue.
	 */
	public String toString() {
		String result = "[" + (head != null ? head.data : "");
		Node current = head != null ? head.next : null;
		while (current != null) {
			result += ", " + current.data;
			current = current.next;
		}
		return result + "]";
	}
	
	private class Node {
		private E data;
		private Node next;
		
		public Node(E data) {
			this.data = data;
		}
	}
}
