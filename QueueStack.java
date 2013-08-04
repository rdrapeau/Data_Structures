package Cracking_The_Coding_Interview;

/**
 * Implementation of the Queue data structure using two Stacks to store the elements.
 * @author RDrapeau
 *
 * @param <E>
 */
public class QueueStack<E extends Comparable> {
	/**
	 * The Stack that will act like the Queue.
	 */
	private StackLinkedList<E> stackOne;
	
	/**
	 * The Stack that will reverse the elements of StackOne.
	 */
	private StackLinkedList<E> stackTwo;
	
	/**
	 * Constructs a new Queue.
	 */
	public QueueStack() {
		this.stackOne = new StackLinkedList<E>();
		this.stackTwo = new StackLinkedList<E>();
	}
	
	/**
	 * Adds the element to the end of the Queue.
	 * 
	 * @param element - The element to add to the Queue
	 */
	public void enqueue(E element) {
		transferElements(stackOne, stackTwo); 
		stackTwo.push(element); // Add to the "end" of the Stack
		transferElements(stackTwo, stackOne);
	}
	
	/**
	 * Returns the first element in the Queue.
	 * 
	 * @return The first element in the Queue
	 */
	public E peek() {
		return stackOne.peek();
	}
	
	/**
	 * Returns and removes the first element in the Queue.
	 * 
	 * @return The first element in the Queue
	 */
	public E dequeue() {
		E element = stackOne.pop();
		return element;
	}
	
	/**
	 * Transfers all the elements in the from Stack to the to Stack.
	 * 
	 * @param from - Elements to move
	 * @param to - Place to move the elements to
	 */
	private void transferElements(StackLinkedList<E> from, StackLinkedList<E> to) {
		while (from.size() != 0) {
			to.push(from.pop());
		}
	}
	
	/**
	 * Returns the number of elements in the Queue.
	 * 
	 * @return The size of the Queue
	 */
	public int size() {
		return stackOne.size();
	}
}
