package Cracking_The_Coding_Interview;

public class StackArray<E> {
	/**
	 * The initial capacity of the Stack.
	 */
	private static final int DEFAULT_CAPACITY = 2;
	
	/**
	 * When the size of the Stack becomes less than 1 / RATE of the length of the array, the array
	 * is resized so that less space is wasted.
	 */
	private static final int RATE = 3;
	
	/**
	 * The elements of the Stack.
	 */
	private Object[] elements;
	
	/**
	 * The number of elements in the Stack.
	 */
	private int size;
	
	/**
	 * Constructs a new Stack object with a size of DEFAULT_CAPACITY.
	 */
	public StackArray() {
		elements = new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * Adds the element to the top of the Stack.
	 * 
	 * @param element - The element to add to the top of the stack
	 */
	public void push(E element) {
		if (size == elements.length) {
			setSize(size * 2);
		}
		elements[size++] = element;
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
		return (E) elements[size - 1];
	}
	
	/**
	 * Removes and returns the top element of the Stack. The Stack is resized if the number of 
	 * elements in the Stack is less than 1 / RATE * the length of the array.
	 * 
	 * @throws IllegalStateException if the Stack is empty
	 * @return The top Element on the Stack
	 */
	public E pop() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		E element = (E) elements[--size];
		if (size < elements.length / RATE) {
			setSize(size * 2);
		}
		return element;
	}
	
	/**
	 * Resizes the array of the Stack to be a length of size.
	 * 
	 * @param size - The new size of the array (Must be as big as the number of elements)
	 */
	private void setSize(int size) {
		Object[] newElements = new Object[size];
		for (int i = 0; i < this.size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	
	/**
	 * Returns the number of elements in the Stack.
	 * 
	 * @return The size of the Stack
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns a String representation of the Stack.
	 */
	public String toString() {
		String result = "[" + (size != 0 ? elements[0] : "");
		for (int i = 1; i < size; i++) {
			result += ", " + elements[i];
		}
		return result + "]";
	}
}
