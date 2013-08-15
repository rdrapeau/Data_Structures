package Cracking_The_Coding_Interview;

public class SearchingandSorting {
	public static void main(String[] args) {
		int[] a = {4, 5, 1, 2, 3};
		System.out.println(rotatedBinarySearch(a, 3));
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
}
