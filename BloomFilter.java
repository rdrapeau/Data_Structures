package Cracking_The_Coding_Interview;

import java.util.BitSet;

/**
 * Implementation of the Bloom Filter data structure (also known as the HashSet)
 * @author RDrapeau
 *
 */
public class BloomFilter<E> {
	/**
	 * The number of bits per object in the filter: n / |S| =  # of bits per object
	 */
	private static final int BITS_PER_OBJECT = 8;
	
	/**
	 * The elements of the BloomFilter.
	 */
	private BitSet elements;
	
	/**
	 * The number of elements in the BloomFilter.
	 */
	private int size;
	
	/**
	 * Constructs a new BloomFilter with 8 bits assigned to each object.
	 * 
	 * @param size - The number of objects to be added to the BloomFilter
	 */
	public BloomFilter(int size) {
		elements = new BitSet(size * BITS_PER_OBJECT);
	}
	
	public void add(E element) {
		elements.set(defaultHash(element));
		
		size++;
	}
	
	/**
	 * Returns the index of the hash code method element implements.
	 * 
	 * @param element - The element to add to the BloomFilter
	 * @return The index of the element in the set
	 */
	private int defaultHash(E element) {
		return element.hashCode() % elements.length();
	}
}
