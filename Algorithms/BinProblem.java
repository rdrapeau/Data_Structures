package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Support.Bucket;
import Support.Item;

/**
 * Given N jobs and K people - balance the work load so that each person has a similar load of work.
 * @author RDrapeau
 *
 */
public class BinProblem {
	public static void main(String[] args) {
		Item[] items = new Item[3];
		items[0] = new Item(1, 1);
		items[1] = new Item(2, 2);
		items[2] = new Item(3, 3);
		System.out.println(knapSack(items, 6));
	}

	/**
	 * Distributes the jobs amongst k people.
	 * 
	 * @param jobs - Jobs to distribute
	 * @param k - Number of people or buckets to use
	 * @return A list of buckets that represent the distributed jobs
	 */
	public static List<Bucket> distribute(int[] jobs, int k) {
		List<Bucket> buckets = new ArrayList<Bucket>();
		Queue<Bucket> q = new PriorityQueue<Bucket>();
		for (int i = 0; i < k; i++) { // Init the Buckets O(k * log(k))
			Bucket b = new Bucket();
			buckets.add(b);
			q.add(b);
		}
		Arrays.sort(jobs); // O(n * log(n))
		for (int i = jobs.length - 1; i >= 0; i--) { // Distribute O(n * log(k))
			Bucket b = q.remove();
			b.add(jobs[i]);
			q.add(b);
		}
		return buckets;
	}
	
	/**
	 * Packs items into bins of size capacity - creating new ones when needed.
	 * 
	 * @param items - Items to pack
	 * @param capacity - Capacity of each bin
	 * @return A list of buckets
	 */
	public static List<Bucket> packBuckets(int[] items, int capacity) {
		Arrays.sort(items);
		List<Bucket> buckets = new ArrayList<Bucket>();
		for (int i = items.length - 1; i >= 0; i--) { // O(n * k)
			boolean done = items[i] > capacity ? true : false;
			int index = 0;
			Bucket b;
			while (!done && index < buckets.size()) {
				b = buckets.get(index++);
				if (b.sum() + items[i] <= capacity) {
					b.add(items[i]);
					done = true;
				}
			}
			if (!done) { // Create a new Bucket
				b = new Bucket();
				b.add(items[i]);
				buckets.add(b);
			}
		}
		return buckets;
	}
	
	/**
	 * Returns the optimal subset of items to pack that maximizes the value of everything.
	 * 
	 * @param items - The list of items to pack
	 * @param capacity - The size of the container
	 * @return A list of optimal items to put into the container
	 */
	public static List<Item> knapSack(Item[] items, int capacity) {
		int[][] matrix = new int[items.length + 1][capacity + 1];
		for (int row = 1; row < matrix.length; row++) {
			Item i = items[row - 1];
			for (int col = 1; col < matrix[row].length; col++) { // Non-zero sizes
				if (col >= i.getSize()) { // Nonnegative
					matrix[row][col] = Math.max(matrix[row - 1][col], matrix[row - 1][col - i.getSize()] + i.getValue());
				} else {
					matrix[row][col] = matrix[row - 1][col];
				}
			}
		}
		int row = items.length;
		int col = capacity;
		List<Item> result = new ArrayList<Item>();
		while (row > 0 && col > 0 && matrix[row][col] != 0) { // Reconstruct
			if (matrix[row - 1][col] != matrix[row][col]) {
				result.add(items[row - 1]);
				col -= items[row - 1].getSize();
			}
			row--;
		}
		return result;
	}
}
