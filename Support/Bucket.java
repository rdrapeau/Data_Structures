package Support;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for the bucket filling problem.
 * @author RDrapeau
 *
 */
public class Bucket implements Comparable<Bucket> {
	/**
	 * Contents of the bucket.
	 */
	private List<Integer> contents;
	
	/**
	 * Sum of the contents.
	 */
	private int sum;
	
	/**
	 * Constructs a new Bucket.
	 */
	public Bucket() {
		this.contents = new ArrayList<Integer>();
	}
	
	/**
	 * Adds an item to the bucket.
	 * 
	 * @param job - Item to add
	 */
	public void add(int job) {
		contents.add(job);
		this.sum += job;
	}
	
	/**
	 * Removes an item from the bucket.
	 * 
	 * @param job - Item to remove
	 */
	public void remove(int job) {
		if (contents.remove(job) == job) {
			this.sum -= job;
		}
	}
	
	/**
	 * The sum of the bucket is the sum of the buckets contents.
	 * @return The sum of the bucket
	 */
	public int sum() {
		return sum;
	}
	
	/**
	 * Buckets are compared by their sum of their contents.
	 */
	public int compareTo(Bucket other) {
		return this.sum - other.sum;
	}
}
