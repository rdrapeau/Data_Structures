package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Support.Bucket;

/**
 * Given N jobs and K people - balance the work load so that each person has a similar load of work.
 * @author RDrapeau
 *
 */
public class BinProblem {
	public static void main(String[] args) {
		int[] jobs = {1, 1, 2, 3, 5, 4, 2, 3, 2, 1};
		System.out.println(packBuckets(jobs, 11));
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
	 * Packs items into bins of capacity - creating new ones when needed.
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
}
