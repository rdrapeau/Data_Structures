package Cracking_The_Coding_Interview;

/**
 * Implementation of the Queue data structure using an Array to store the elements.
 * @author RDrapeau
 *
 * @param <E>
 */
public class QueueArray<E> {
	/**
	 * The default capacity of the Queue.
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * The elements of queue.
	 */
	private Object[] elements;
	
	/**
	 * The number of elements in the queue.
	 */
	private int size;
	
	/**
	 * The index of the front of the queue.
	 */
	private int front;
	
	/**
	 * Constructs a new Queue with a default size of 2.
	 */
	public QueueArray() {
		elements = new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * Adds an item to the end of the queue.
	 * 
	 * @param data - The element to add to the end of the queue
	 */
	public void enqueue(E data) {
		if (size == elements.length) {
			setSize(size * 2);
		}
		elements[size++] = data;
	}
	
	/**
	 * Returns the first element in the queue.
	 * 
	 * @throws IllegalStateException if there is nothing in the queue
	 * @return The first element in the queue
	 */
	public E peek() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		return (E) elements[front];
	}
	
	/**
	 * Returns and removes the first element in the queue.
	 * 
	 * @throws IllegalStateException if there is nothing in the queue
	 * @return The first element in the queue
	 */
	public E dequeue() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		size--;
		E element = (E) elements[front++];
		if (size < elements.length / 2) { // Make the Queue smaller
			setSize(size * 2);
		}
		return element;
	}
	
	/**
	 * Resizes the elements array to have the input size.
	 * 
	 * @param size - The new size of the array
	 */
	private void setSize(int size) {
		Object[] newElements = new Object[size];
		int position = 0;
		for (int i = front; i < this.size + front; i++) {
			newElements[position++] = elements[i];
		}
		front = 0;
		elements = newElements;
	}
	
	/**
	 * Returns the number of elements in the Queue.
	 * 
	 * @return The size of the Queue
	 */
	public int size() {
		return size;
	}
	
	public String toString() {
		String result = "[" + (size != 0 ? elements[front] : "");
		for (int i = front + 1; i < size + front; i++) {
			result += ", " + elements[i];
		}
		return result + "]";
	}
}
