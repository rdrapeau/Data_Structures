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
	
	/**
	 * Adds an element into the BloomFilter to remember. 
	 * 
	 * @param element - The element to add to the set 
	 */
	public void add(E element) {
		int initial = defaultHash(element);
		int offset = secondaryHash(element);
		for (int i = initial; i < elements.length(); i += offset) {
			elements.set(i);
		}
		size++;
	}
	
	/**
	 * Returns whether or not an element is contained within the BloomFilter.
	 * 
	 * @param element - The element to check
	 * @return True if the element is in the bloom filter and false otherwise
	 */
	public boolean contains(E element) {
		int initial = defaultHash(element);
		int offset = secondaryHash(element);
		for (int i = initial; i < elements.length(); i += offset) {
			if (!elements.get(i)) {
				return false;
			}
		}
		return true;
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
	
	/**
	 * Applies a supplemental hash function to a given hashCode, which
	 * defends against poor quality hash functions.  This is critical
	 * because HashMap uses power-of-two length hash tables, that
	 * otherwise encounter collisions for hashCodes that do not differ
	 * in lower bits. Note: Null keys always map to hash 0, thus index 0.
	 * 
	 * @param element - The element to add to the BloomFilter
	 * @return A secondary index of the element
	 */
	private int secondaryHash(E element) {
		int h = element.hashCode();
		h ^= (h >>> 20) ^ (h >>> 12);
		return (h ^ (h >>> 7) ^ (h >>> 4)) % elements.length();
	}
}
