package Lists;

/**
 * Implementation of the Sorted List data structure.
 * @author RDrapeau
 *
 */
public class SortedList<E extends Comparable<E>> {
	private final int DEFAULT = 2;
	private E[] elements;
	private int size;
	
	public SortedList() {
		this.elements = (E[]) new Comparable[DEFAULT];
	}
	
	public void add(E element) {
		int index = binarySearch(element);
		if (index < 0) {
			index = -index - 1;
		}
		add(element, index);
	}
	
	public void add(E element, int index) {
		if (size == elements.length) {
			resize(size * 2);
		}
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}
	
	public boolean remove(E element) {
		int index = binarySearch(element);
		if (index >= 0) {
			return remove(index) == element;
		}
		return false;
	}
	
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException();
		}
		E element = elements[index];
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		size--;
		elements[size] = null;
		if (size <= elements.length / 4) {
			resize(elements.length / 2);
		}
		return element;
	}
	
	private void resize(int size) {
		E[] result = (E[]) new Comparable[size];
		for (int i = 0; i < this.size; i++) {
			result[i] = elements[i];
		}
		elements = result;
	}
	
	public int binarySearch(E element) {
		int left = 0;
		int right = size;
		int middle = left + (right - left) / 2;
		while (right > left) {
			if (elements[middle] == element) {
				return middle;
			} else if (element.compareTo(elements[middle]) < 1) {
				right = middle;
			} else {
				left = middle + 1;
			}
			middle = left + (right - left) / 2;
		}
		return -(middle + 1);
	}
}
