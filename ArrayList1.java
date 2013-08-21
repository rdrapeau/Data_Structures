package Cracking_The_Coding_Interview;

import java.util.Arrays;
import java.util.Random;

/**
 * Implementation of the ArrayList data structure.
 * @author RDrapeau
 *
 * @param <E>
 */
public class ArrayList1<E extends Comparable> {
	/**
	 * The default capacity of the ArrayList.
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	/**
	 * Random object to use for quicksort
	 */
	private static final Random RANDOM = new Random();
	
	/**
	 * The array to store the data into.
	 */
	private Object[] elements;
	
	/**
	 * Size of the list.
	 */
	private int size;
	
	/**
	 * Constructs a new ArrayList of default capacity.
	 */
	public ArrayList1() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Constructs a new ArrayList of the input size.
	 * 
	 * @param size - The size of the initial array
	 */
	public ArrayList1(int size) {
		this.elements = new Object[size]; 
	}
	
	/**
	 * Adds the element to the end of the list.
	 * 
	 * @param data - Element to add
	 */
	public void add(E data) {
		if (size == this.elements.length) {
			changeSize(size * 2);
		}
		elements[size++] = data;
	}
	
	/**
	 * Adds the element to the list at the specified index.
	 * 
	 * @param index - Index to add the element at
	 * @param data - The element to add
	 * @throws IllegalArgumentException if index is invalid
	 */
	public void add(int index, E data) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException();
		}
		if (size == this.elements.length) {
			changeSize(size * 2);
		}
		for (int i = size; i > index; i--) { // Shift over elements
			elements[i] = elements[i - 1];
		}
		elements[index] = data;
		size++;
	}
	
	/**
	 * Removes and returns the data at the specified index.
	 * 
	 * @param index - The index of the element to remove
	 * @throws IllegalArgumentException if index is invalid
	 * @return The element removed from the list
	 */
	public E remove(int index) {
		if (index < 0 || index >= this.size) {
			throw new IllegalArgumentException();
		}
		E data = (E) this.elements[index];
		for (int i = index; i < size - 1; i++) {
			this.elements[i] = this.elements[i + 1];
		}
		size--;
		return data;
	}
	
	/**
	 * Removes the element that is equal to the data.
	 * 
	 * @param data - The data of the element to remove
	 * @return True if the element was removed and false otherwise
	 */
	public boolean remove(E data) {
		for (int i = 0; i < size; i++) {
			if ((E) this.elements[i] == data) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns whether or not the list contains the data.
	 * 
	 * @param data - The data to check
	 * @return True if the list contains the data and false otherwise
	 */
	public boolean contains(E data) {
		for (int i = 0; i < size; i++) {
			if ((E) this.elements[i] == data) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sorts the ArrayList.
	 */
	public void sort() {
		changeSize(size);
		quicksort(this.elements, 0, size);
	}
	
	/**
	 * Runs the mergesort algorithm on the input data.
	 * 
	 * @param data - The array to sort
	 */
	private void mergesort(Object[] data) {
		if (data.length > 1) {
			int middle = data.length / 2;
			Object[] left = Arrays.copyOfRange(data, 0, middle);
			Object[] right = Arrays.copyOfRange(data, middle, data.length);
			mergesort(left);
			mergesort(right);
			merge(data, left, right);
		}
	}
	
	/**
	 * Merges two sorted arrays into one array.
	 * 
	 * @param data - The destination for the two sorted arrays
	 * @param left - The sorted left array
	 * @param right - The sorted right array
	 */
	private void merge(Object[] data, Object[] left, Object[] right) {
		int leftIndex = 0;
		int rightIndex = 0;
		for (int i = 0; i < data.length; i++) {
			if (rightIndex >= right.length || (leftIndex < left.length && ((E) left[leftIndex]).compareTo((E) right[rightIndex]) < 1)) {
				data[i] = left[leftIndex++];
			} else {
				data[i] = right[rightIndex++];
			}
		}
	}
	
	/**
	 * Runs the quicksort algorithm on the input data between left and right.
	 * 
	 * @param data - The array to sort
	 * @param left - The minimum index to sort (inclusive)
	 * @param right - The maximum index to sort (exclusive)
	 */
	private void quicksort(Object[] data, int left, int right) {
		if (right > left) {
			int pivotIndex = RANDOM.nextInt(right - left) + left;
			E pivot = (E) data[pivotIndex];
			swap(data, left, pivotIndex);
			int larger = left + 1;
			for (int i = larger; i < right; i++) {
				if (((E) data[i]).compareTo(pivot) < 1) {
					swap(data, larger++, i);
				}
			}
			pivotIndex = larger - 1;
			swap(data, left, pivotIndex);
			quicksort(data, left, pivotIndex);
			quicksort(data, pivotIndex + 1, right);
		}
	}
	
	/**
	 * Swaps the positions of two elements within an array.
	 * 
	 * @param data - The array to perform the swap on
	 * @param index - The index of the first element
	 * @param other - The index of the second element
	 */
	private void swap(Object[] data, int index, int other) {
		Object temp = data[index];
		data[index] = data[other];
		data[other] = temp;
	}
	
	/**
	 * Changes the size of the elements array to be the new size.
	 * 
	 * @param newSize - New size of the array
	 */
	private void changeSize(int newSize) {
		Object[] newElements = new Object[newSize];
		for (int i = 0; i < Math.min(newSize, this.size); i++) {
			newElements[i] = elements[i];
		}
		this.elements = newElements;
	}
	
	/**
	 * Returns the size of the ArrayList.
	 * 
	 * @return The size of the ArrayList
	 */
	public int size() {
		return this.size;
	}
	
	public String toString() {
		String result = "[" + (size > 0 ? elements[0] : "");
		for (int i = 1; i < size; i++) {
			result += ", " + elements[i];
		}
		return result + "]";
	}
}
