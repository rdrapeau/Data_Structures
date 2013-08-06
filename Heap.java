package Cracking_The_Coding_Interview;

/**
 * Implementation of the Heap data structure.
 * @author RDrapeau
 *
 * @param <E>
 */
public class Heap<E extends Comparable> {
	/**
	 * Elements of the heap.
	 */
	private Object[] elements;
	
	/**
	 * True if the Heap returns the minimum and false if it returns the maximum.
	 */
	private boolean isMinHeap;
	
	/**
	 * Number of elements in the Heap.
	 */
	private int size;
	
	/**
	 * Constructs a new heap of the input size.
	 * 
	 * @param size - The number of elements in the heap
	 * @param isMinHeap - True if the Heap will return the minimum and false otherwise
	 */
	public Heap(int size, boolean isMinHeap) {
		this.elements = new Object[size];
		this.isMinHeap = isMinHeap;
	}
	
	/**
	 * Inserts an element into the Heap.
	 * 
	 * @param element - The element to insert
	 * @throws IllegalStateException if the Heap is full
	 */
	public void insert(E element) {
		if (size == elements.length) {
			throw new IllegalStateException();
		}
		int index = size++;
		elements[index] = element;
		int parent = getParentIndex(index);
		while (index != 0 
				&& ((isMinHeap && ((E) elements[index]).compareTo((E) elements[parent]) < 0)
						|| (!isMinHeap && ((E) elements[index]).compareTo((E) elements[parent]) > 0))) { // Bubble Up
			swap(index, parent);
			index = parent;
			parent = getParentIndex(index);
		}
	}
	
	/**
	 * Removes and returns the smallest or biggest element in the Heap.
	 * 
	 * @throws IllegalStateException if the Heap is empty
	 * @return The smallest or biggest element in the Heap
	 */
	public E extract() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		E root = (E) elements[0];
		elements[0] = elements[--size];
		elements[size] = null;
		int index = 0;
		int child = getIndexOfChild(index);
		while (child < size 
				&& ((isMinHeap && ((E) elements[index]).compareTo((E) elements[child]) > 0)
						|| (!isMinHeap && ((E) elements[index]).compareTo((E) elements[child]) < 0))) { // Bubble down
			swap(index, child);
			index = child;
			child = getIndexOfChild(index);
		}
		return root;
	}
	
	/**
	 * Returns the minimum or maximum element in the Heap.
	 * 
	 * @throws IllegalStateException if the Heap is empty
	 * @return The smallest element
	 */
	public E peek() {
		if (size == 0) {
			throw new IllegalStateException();
		}
		return (E) elements[0];
	}
	
	/**
	 * Returns the number of elements in the Heap.
	 * 
	 * @return The size of the Heap
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Swaps two elements in the Heap.
	 * 
	 * @param index - The index of the first element
	 * @param other - The index of the second element
	 * @throws IllegalArgumentException if either of the indices is invalid
	 */
	private void swap(int index, int other) {
		if (index < 0 || other < 0 || index >= size || other >= size) {
			throw new IllegalArgumentException();
		}
		Object temp = elements[index];
		elements[index] = elements[other];
		elements[other] = temp;
	}
	
	/**
	 * Returns the index of the smaller or bigger child of the parent at the input index.
	 * 
	 * @param index - The index of the parent
	 * @return The index of the smaller or bigger child of the parent. If the parent does not have any children
	 * then size + 1 is returned.
	 */
	private int getIndexOfChild(int index) {
		int first = getFirstChildIndex(index);
		int second = getSecondChildIndex(index);
		if (first >= size || second >= size || (elements[first] == null && elements[second] == null)) { // Fail safe
			return size + 1;
		}
		if (elements[first] == null || (elements[second] != null 
				&& ((isMinHeap && ((E) elements[first]).compareTo((E) elements[second]) > 0)
						|| (!isMinHeap && ((E) elements[first]).compareTo((E) elements[second]) < 0)))) {
			return second;
		} else {
			return first;
		} 
	}
	
	/**
	 * Returns the index of the parent of the child index.
	 * 
	 * @param index - The index of the child
	 * @return The index of the parent
	 */
	private int getParentIndex(int index) {
		return (index + 1) / 2 - 1;
	}
	
	/**
	 * Returns the index of the first child.
	 * 
	 * @param index - The index of the parent
	 * @return The index of the first child
	 */
	private int getFirstChildIndex(int index) {
		return index * 2 + 1;
	}
	
	/**
	 * Returns the index of the second child.
	 *
	 * @param index - The index of the parent
	 * @return The index of the second child
	 */
	private int getSecondChildIndex(int index) {
		return getFirstChildIndex(index) + 1;
	}
}
