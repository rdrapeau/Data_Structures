package Cracking_The_Coding_Interview;

public class StackQueue<E> {
	private QueueLinkedList<E> queueOne;
	private QueueLinkedList<E> queueTwo;
	private int size;
	
	public StackQueue() {
		this.queueOne = new QueueLinkedList<E>();
		this.queueTwo = new QueueLinkedList<E>();
	}
	
	public void push(E element) {
		queueOne.enqueue(element);
		size++;
	}
	
	/* 
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
	 * 
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
	
	public int size() {
		return size;
	}
	
	private void transferElements(QueueLinkedList<E> from, QueueLinkedList<E> to) {
		while (from.size() != 0) {
			to.enqueue(from.dequeue());
		}
	}
}
