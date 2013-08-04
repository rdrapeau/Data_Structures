package Cracking_The_Coding_Interview;

public class QueueStack<E extends Comparable> {
	private StackLinkedList<E> stackOne;
	private StackLinkedList<E> stackTwo;
	private int size;
	
	public QueueStack() {
		this.stackOne = new StackLinkedList<E>();
		this.stackTwo = new StackLinkedList<E>();
	}
	
	public void enqueue(E element) {
		transferElements(stackOne, stackTwo);
		stackTwo.push(element);
		transferElements(stackTwo, stackOne);
		size++;
	}
	
	public E peek() {
		return stackOne.peek();
	}
	
	public E dequeue() {
		E element = stackOne.pop();
		size--;
		return element;
	}
	
	private void transferElements(StackLinkedList<E> from, StackLinkedList<E> to) {
		while (from.size() != 0) {
			to.push(from.pop());
		}
	}
	
	public int size() {
		return size;
	}
}
