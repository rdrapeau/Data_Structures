package Cracking_The_Coding_Interview;

/**
 * Implementation of the Stack data structure using two queues.
 * @author RDrapeau
 *
 * @param <E>
 */
public class StackQueue<E> {
	/**
	 * Queue that will pretend to be a Stack.
	 */
	private QueueLinkedList<E> queueOne;
	
	/**
	 * Temporary storage for Queue one.
	 */
	private QueueLinkedList<E> queueTwo;
	
	/**
	 * The number of elements in the Stack.
	 */
	private int size;
	
	/**
	 * Constructs a new Stack.
	 */
	public StackQueue() {
		this.queueOne = new QueueLinkedList<E>();
		this.queueTwo = new QueueLinkedList<E>();
	}
	
	/**
	 * Pushes the element onto the top of the Stack.
	 * 
	 * @param element - The element to add to the Stack
	 */
	public void push(E element) {
		queueOne.enqueue(element);
		size++;
	}
	
	/* Alternate way of using this implementation that allows O(1) pop but O(n) push
	 * 
	 * public void push(E element) {
	 * 		queueTwo.enqueue(element);
	 * 		transferElements(queueOne, queueTwo);
	 * 		QueueLinkedList<E> temp = queueOne;
	 *		queueOne = queueTwo;
	 *		queueTwo = temp;
	 *		size++;
	 * }
	 * 
	 * public E pop() {
	 * 		E element = queueOne.dequeue();
	 * 		size--;
	 * 		return element;
	 * }
	 */
	
	/**
	 * Returns and removes the element on top of the Stack (the one at the back of the first Queue).
	 * 
	 * @return The top element on the Stack
	 */
	public E pop() {
		while (queueOne.size() > 1) {
			queueTwo.enqueue(queueOne.dequeue());
		}
		QueueLinkedList<E> temp = queueOne;
		queueOne = queueTwo;
		queueTwo = temp;
		size--;
		return temp.dequeue();
	}
	
	/**
	 * Returns the number of elements in the Stack.
	 * 
	 * @return The size of the Stack
	 */
	public int size() {
		return size;
	}
	
	/* Used in the alternate implmentation
	 * private void transferElements(QueueLinkedList<E> from, QueueLinkedList<E> to) {
	 *	while (from.size() != 0) {
	 *		to.enqueue(from.dequeue());
	 *	}
 	 *  }
	 */
}
