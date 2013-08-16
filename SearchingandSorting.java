package Cracking_The_Coding_Interview;

import java.util.Arrays;
import java.util.Random;

public class SearchingandSorting {
	private static final int SIZE = 10000;
	private static final Random r = new Random();
	
	public static void main(String[] args) {
		int[] a = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			a[i] = r.nextInt(SIZE);
		}
		heapSort(a);
		System.out.println(Arrays.toString(a));
	}

	/**
	 * Merges two sorted arrays into a. Assumes that there are a.length elements in total
	 * 
	 * @param a - Sorted array with buffer for b
	 * @param b - Sorted array to merge into a
	 */
	public static void merge(int[] a, int[] b) {
		int aIndex = a.length - b.length - 1;
		int bIndex = b.length - 1;
		for (int i = a.length - 1; i >= 0; i--) {
			if (aIndex < 0 || (bIndex >= 0 && b[bIndex] >= a[aIndex])) {
				a[i] = b[bIndex--];
			} else {
				a[i] = a[aIndex--];
			}
		}
	}
	
	/**
	 * Returns the index of find in a by doing a rotated binary search.
	 * 
	 * @param a - The sorted rotated array
	 * @param find - The element to find
	 * @return The index of find in a (-1 if find is not in a)
	 */
	public static int rotatedBinarySearch(int[] a, int find) {
		return rotatedBinarySearch(a, find, 0, a.length);
	}
	
	/**
	 * Returns the index of find in a by doing a rotated binary search.
	 * 
	 * @param a - The sorted rotated array
	 * @param find - The element to find
	 * @param min - The minimum index to search (inclusive)
	 * @param max - The maximum index to search (exclusive)
	 * @return The index of find in a (-1 if find is not in a)
	 */
	private static int rotatedBinarySearch(int[] a, int find, int min, int max) {
		if (max > min) {
			int mid = (max + min) / 2;
			if (a[mid] == find) {
				return mid;
			} else if (a[mid] > find && a[min] <= find || (a[mid] < find && a[max - 1] < find)) {
				return rotatedBinarySearch(a, find, min, mid);
			} else {
				return rotatedBinarySearch(a, find, mid + 1, max);
			}
		}
		return -1;
	}
	
	/**
	 * Returns the index of find in array a that is separated by empty strings.
	 * 
	 * @param a - The sorted array
	 * @param find - The element to find
	 * @return The index of find in a (-1 if not found)
	 */
	public static int stringSearch(String[] a, String find) {
		return stringSearch(a, find, 0, a.length);
	}

	/**
	 * Returns the index of find in array a that is separated by empty strings.
	 * 
	 * @param a - The sorted array 
	 * @param find - The element to find
	 * @param min - The minimum index (inclusive)
	 * @param max - The maximum index (exclusive)
	 * @return The index of find in a (-1 if not found)
	 */
	private static int stringSearch(String[] a, String find, int min, int max) {
		if (max > min) {
			int mid = (max + min) / 2;
			int oldIndex = mid;
			while (mid < max && a[mid].length() == 0) {
				mid++;
			}
			if (mid < max) {
				if (a[mid].equals(find)) {
					return mid;
				} else if (a[mid].compareTo(find) < 0) {
					return stringSearch(a, find, mid + 1, max);
				}
			}
			return stringSearch(a, find, min, oldIndex);
		}
		return -1;
	}
	
	/**
	 * Performs Heap Sort on the integer array.
	 * 
	 * @param a - The array to sort
	 */
	public static void heapSort(int[] a) {
		heapify(a);
		int last = a.length - 1;
		for (int i = 0; i < a.length; i++) {
			a[last] = extractMax(a, last);
			last--;
		}
	}
	
	/**
	 * Returns the maximum from the heapified array a.
	 * 
	 * @param a - The heap to get the max from
	 * @param last - The last index of the heap in the array
	 * @return The maximum value in the heap
	 */
	private static int extractMax(int[] a, int last) {
		int max = a[0];
		a[0] = a[last--];
		int index = 0;
		int child = getBiggerChildIndex(a, index);
		while (child <= last && a[child] > a[index]) {
			swap(a, index, child);
			index = child;
			child = getBiggerChildIndex(a, index);
		}
		return max;
	}
	
	/**
	 * Converts a into a heap by bubbling up from the leafs.
	 * 
	 * @param a - The array to convert into a heap
	 */
	private static void heapify(int[] a) {
		for (int i = a.length - 1; i >= 0; i--) {
			bubbleUp(a, i);
		}
	}
	
	/**
	 * Bubbles up the maximum elements from the index in the heap a
	 * 
	 * @param a - The heap
	 * @param index - The current index
	 */
	private static void bubbleUp(int[] a, int index) {
		int parent = getParentIndex(index);
		while (parent != -1 && a[parent] < a[index]) {
			swap(a, index, parent);
			index = parent;
			parent = getParentIndex(index);
		}
	}
	
	/**
	 * Swaps the two values at the indices in the array
	 * 
	 * @param a - The array
	 * @param first - The first element to swap
	 * @param second - The second element to swap
	 */
	private static void swap(int[] a, int first, int second) {
		int temp = a[first];
		a[first] = a[second];
		a[second] = temp;
	}
	
	/**
	 * Returns the parent index of the index in the heap.
	 * 
	 * @param index - The child
	 * @return The index of the parent (-1 if the index is 0 (the root))
	 */
	private static int getParentIndex(int index) {
		return index == 0 ? -1 : (index - 1) / 2;
	}
	
	/**
	 * Returns the index of the bigger child.
	 * 
	 * @param a - The heap
	 * @param index - The parent index
	 * @return The index of the larger child (a.length if none exists)
	 */
	private static int getBiggerChildIndex(int[] a, int index) {
		int first = index * 2 + 1;
		int second = index * 2 + 2;
		if (first < a.length || second < a.length) {
			if (first >= a.length || (second < a.length && a[second] > a[first])) {
				return second;
			} else {
				return first;
			}
		}
		return a.length;
	}
}
