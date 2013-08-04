package Cracking_The_Coding_Interview;

/**
 * Implementation of the DoubleStack data structure. This object uses only 1 array to represent
 * two Stacks.
 * @author RDrapeau
 *
 * @param <E>
 */
public class DoubleStack<E> {
	/**
	 * Initial capacity of the array.
	 */
	private static final int DEFAULT_CAPACITY = 4;
	
	/**
	 * The elements of both Stacks.
	 */
	private Object[] elements;
	
	/**
	 * The number of elements in Stack 1.
	 */
	private int sizeOne;
	
	/**
	 * The number of elements in Stack 2.
	 */
	private int sizeTwo;
	
	/**
	 * Constructs a new DoubleStack of DEFAULT_CAPACITY.
	 */
	public DoubleStack() {
		this.elements = new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * Pushes an element onto the first Stack.
	 * 
	 * @param element - The element to add to the first Stack
	 */
	public void pushOntoStackOne(E element) {
		if (sizeOne + sizeTwo == elements.length) {
			setSize(elements.length * 2);
		}
		elements[sizeOne++] = element;
	}
	
	/**
	 * Pushes an element onto the second Stack.
	 * 
	 * @param element - The element to add to the second Stack
	 */
	public void pushOntoStackTwo(E element) {
		if (sizeOne + sizeTwo == elements.length) {
			setSize(elements.length * 2);
		}
		elements[elements.length - 1 - sizeTwo++] = element;
	}
	
	/**
	 * Returns the element on the top of the first Stack.
	 * 
	 * @throws IllegalStateException if the first Stack is empty
	 * @return The top element on the first Stack
	 */
	public E peekFromStackOne() {
		if (sizeOne == 0) {
			throw new IllegalStateException();
		}
		return (E) elements[sizeOne - 1];
	}
	
	/**
	 * Returns the element on the top of the second Stack.
	 * 
	 * @throws IllegalStateException if the second Stack is empty
	 * @return The top element on the second Stack
	 */
	public E peekFromStackTwo() {
		if (sizeTwo == 0) {
			throw new IllegalStateException();
		}
		return (E) elements[elements.length - sizeTwo];
	}
	
	/**
	 * Returns and removes the top element on the first Stack.
	 * 
	 * @throws IllegalStateException if the first Stack is empty
	 * @return The top element on the first Stack
	 */
	public E popFromStackOne() {
		if (sizeOne == 0) {
			throw new IllegalStateException();
		}
		E element = (E) elements[--sizeOne];
		if (sizeOne + sizeTwo < elements.length / 3) {
			setSize((sizeOne + sizeTwo) * 2);
		}
		return element;
	}
	
	/**
	 * Returns and removes the top element on the second Stack.
	 * 
	 * @throws IllegalStateException if the second Stack is empty
	 * @return The top element on the second Stack
	 */
	public E popFromStackTwo() {
		if (sizeTwo == 0) {
			throw new IllegalStateException();
		}
		E element = (E) elements[elements.length - sizeTwo--];
		if (sizeOne + sizeTwo < elements.length / 3) {
			setSize((sizeOne + sizeTwo) * 2);
		}
		return element;
	}
	
	/**
	 * Sets the size of the elements array to be the new length. Copies over all elements from
	 * the previous array into the new one. 
	 * 
	 * @param length - The new length of the array
	 */
	private void setSize(int length) {
		Object[] newElements = new Object[length];
		for (int i = 0; i < sizeOne; i++) {
			newElements[i] = elements[i];
		}
		int index = newElements.length - 1;
		for (int i = elements.length - 1; i >= elements.length - sizeTwo; i--) { 
			newElements[index--] = elements[i];
		}
		elements = newElements;
	}
	
	/**
	 * Returns the number of elements in both of the Stacks.
	 * 
	 * @return The number of elements in both of the Stack
	 */
	public int totalSize() {
		return sizeOne + sizeTwo;
	}
}
