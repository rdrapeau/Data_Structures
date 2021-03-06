package Stacks;

/**
 * Implementation of the Stack data structure using a linked list to store the elements.
 * @author RDrapeau
 *
 * @param <E>
 */
public class StackLinkedList<E extends Comparable> {
	/**
	 * The top of the Stack.
	 */
	private Node top;
	
	/**
	 * The number of elements in the Stack.
	 */
	private int size;
	
	/**
	 * The top of the second Stack that keeps track of the minimum element.
	 */
	private Node minTop;
	
	/**
	 * Adds the element to the top of the Stack.
	 * 
	 * @param element - The element to add to the top of the Stack
	 */
	public void push(E element) {
		if (minTop == null || minTop.data.compareTo(element) > 0) {
			minTop = new Node(element, minTop);
		}
		top = new Node(element, top);
		size++;
	}
	
	/**
	 * Returns the top element on the Stack.
	 * 
	 * @throws IllegalStateException if the Stack is empty
	 * @return The top element on the Stack
	 */
	public E peek() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		return top.data;
	}
	
	/**
	 * Removes and returns the top element on the Stack.
	 * 
	 * @throws IllegalStateException if the Stack is empty
	 * @return The top element on the Stack
	 */
	public E pop() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		E element = top.data;
		if (minTop.data == element) {
			minTop = minTop.next;
		}
		top = top.next;
		size--;
		return element;
	}
	
	/**
	 * Returns the minimum element in the Stack.
	 * 
	 * @return The smallest element in the Stack
	 */
	public E findMin() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		return minTop.data;
	}
	
	/**
	 * Returns the number of elements in the Stack.
	 * 
	 * @return The number of elements in the Stack
	 */
	public int size() {
		return size;
	}
	
	public String toString() {
		String result = "[" + (size != 0 ? top.data : "");
		Node current = top != null ? top.next : null;
		while (current != null) {
			result += ", " + current.data;
			current = current.next;
		}
		return result + "]";
	}
	
	/**
	 * Represents one Node on the Stack.
	 * @author RDrapeau
	 *
	 */
	private class Node {
		/**
		 * The next element below this element.
		 */
		private Node next;
		
		/**
		 * The data of the Node.
		 */
		private E data;
		
		/**
		 * Constructs a new Node with nothing below it.
		 * 
		 * @param data - The data of the Node
		 */
		public Node(E data) {
			this(data, null);
		}
		
		/**
		 * Constructs a new Node with another Node below it.
		 * 
		 * @param data - The data of the Node
		 * @param next - The Node below this one
		 */
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}
}
