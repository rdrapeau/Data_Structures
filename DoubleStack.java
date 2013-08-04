package Cracking_The_Coding_Interview;

public class DoubleStack<E> {
	private Object[] elements;
	private int size1;
	private int size2;
	
	public DoubleStack() {
		this.elements = new Object[4];
	}
	
	public void push1(E element) {
		if (size1 + size2 == elements.length) {
			setSize(elements.length * 2);
		}
		elements[size1++] = element;
	}
	
	public void push2(E element) {
		if (size1 + size2 == elements.length) {
			setSize(elements.length * 2);
		}
		elements[elements.length - 1 - size2++] = element;
	}
	
	public E peek1() {
		if (size1 == 0) {
			throw new IllegalStateException();
		}
		return (E) elements[size1 - 1];
	}
	
	public E peek2() {
		if (size2 == 0) {
			throw new IllegalStateException();
		}
		return (E) elements[elements.length - size2];
	}
	
	public E pop1() {
		if (size1 == 0) {
			throw new IllegalStateException();
		}
		E element = (E) elements[--size1];
		if (size1 + size2 < elements.length / 3) {
			setSize((size1 + size2) * 2);
		}
		return element;
	}
	
	public E pop2() {
		if (size2 == 0) {
			throw new IllegalStateException();
		}
		E element = (E) elements[elements.length - size2--];
		if (size1 + size2 < elements.length / 3) {
			setSize((size1 + size2) * 2);
		}
		return element;
	}
	
	private void setSize(int length) {
		Object[] newElements = new Object[length];
		for (int i = 0; i < size1; i++) {
			newElements[i] = elements[i];
		}
		int index = newElements.length - 1;
		for (int i = elements.length - 1; i >= elements.length - size2; i--) { 
			newElements[index--] = elements[i];
		}
		elements = newElements;
	}
	
	public int size() {
		return size1 + size2;
	}
}
