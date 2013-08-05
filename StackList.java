package Cracking_The_Coding_Interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ToplingStack data structure. Any time a Stack gets too high, a new one is 
 * formed.
 * 
 * @author RDrapeau
 *
 * @param <E>
 */
public class StackList<E extends Comparable> {
	/**
	 * The maximum number of elements allowed per stack.
	 */
	public static final int MAX = 3;
	
	/**
	 * A list of all the stacks.
	 */
	private List<StackLinkedList<E>> stacks;

	/**
	 * The number of elements across all of the stacks. 
	 */
	private int size;
	
	/**
	 * Constructs a new Stack.
	 */
	public StackList() {
		stacks = new ArrayList<StackLinkedList<E>>();
	}
	
	/**
	 * Pushes the element onto the last Stack in the list, if that Stack is full a new one is made.
	 * 
	 * @param element - The element to add to the Stacks.
	 */
	public void push(E element) {
		if (stacks.isEmpty() || stacks.get(stacks.size() - 1).size() >= MAX) {
			stacks.add(new StackLinkedList<E>());
		}
		stacks.get(stacks.size() - 1).push(element);
		size++;
	}
	
	/**
	 * Returns the top element on the last Stack.
	 * 
	 * @throws IllegalStateException if the Stacks are empty
	 * @return The top element
	 */
	public E peek() {
		if (stacks.isEmpty()) {
			throw new IllegalStateException();
		}
		return stacks.get(stacks.size() - 1).peek();
	}
	
	/**
	 * Removes and returns the top element on the last Stack.
	 * 
	 * @throws IllegalStateException if the Stacks are empty
	 * @return The top element on the last Stack
	 */
	public E pop() {
		if (stacks.isEmpty()) {
			throw new IllegalStateException();
		}
		StackLinkedList<E> s = stacks.get(stacks.size() - 1);
		E element = s.pop();
		if (s.size() == 0) {
			stacks.remove(stacks.size() - 1);
		}
		size--;
		return element; 
	}
	
	/**
	 * Removes and returns the top element on the Stack at the input index.
	 * 
	 * @param index - The index of the Stack
	 * @throws IllegalArgumentException if the index is not valid
	 * @return The top element on the Stack at the index
	 */
	public E pop(int index) {
		if (index < 0 || index >= stacks.size()) {
			throw new IllegalArgumentException();
		}
		E element = stacks.get(index).pop();
		size--;
		return element;
	}
	
	/**
	 * Returns the number of elements across all of the Stacks.
	 * 
	 * @return The size of the Stack 
	 */
	public int size() {
		return size;
	}
}
