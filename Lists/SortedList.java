package Lists;

/**
 * Implementation of the Sorted List data structure.
 * @author RDrapeau
 *
 */
public class SortedList<E extends Comparable<E>> {
	/**
	 * Default size of the list.
	 */
	private final int DEFAULT = 2;
	
	/**
	 * Elements of the list.
	 */
	private E[] elements;
	
	/**
	 * The number of elements in the list.
	 */
	private int size;
	
	/**
	 * Constructs a new list.
	 */
	public SortedList() {
		this.elements = (E[]) new Comparable[DEFAULT];
	}
	
	/**
	 * Adds an element into the list at the correct position in sorted order.
	 * 
	 * @param element - The element to add
	 */
	public void add(E element) {
		int index = binarySearch(element);
		if (index < 0) {
			index = -index - 1;
		}
		add(element, index);
	}
	
	/**
	 * Adds an element into the list at the index, shifting all other elements to the right.
	 * 
	 * @param element - The element to add
	 * @param index - The index of the new element
	 */
	private void add(E element, int index) {
		if (size == elements.length) {
			resize(size * 2);
		}
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}
	
	/**
	 * Removes the element that matches the input from the list.
	 * 
	 * @param element - The element to remove
	 * @return True if the element was successfully removed and false otherwise
	 */
	public boolean remove(E element) {
		int index = binarySearch(element);
		if (index >= 0) {
			return remove(index) == element;
		}
		return false;
	}
	
	/**
	 * Removes the element at the index, shifting all other elements to the left.
	 * 
	 * @param index - The index of the element to remove
	 * @return The element removed
	 */
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
	
	/**
	 * Resizes the array to be the new size.
	 * 
	 * @param size - The new size of the array
	 */
	private void resize(int size) {
		E[] result = (E[]) new Comparable[size];
		for (int i = 0; i < this.size; i++) {
			result[i] = elements[i];
		}
		elements = result;
	}
	
	/**
	 * Performs a binary search on the list searching for the element.
	 * 
	 * @param element - The element to search for
	 * @return The index of the element, (index + 1) * -1 if the element was not in the list.
	 */
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
