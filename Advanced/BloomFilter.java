package Advanced;

import java.util.BitSet;

/**
 * Implementation of the Bloom Filter data structure (also known as the HashSet)
 * 
 * NOTE: This was just to get an idea for how a BloomFilter can be implemented. 5+ hash functions
 * should be used in order to reduce the false positive probability - and I have not learned enough 
 * them to be able to implement 5 on my own. In this class, I have implemented 2 hash functions which 
 * reduce the probability to 2-5%. 
 * @author RDrapeau
 *
 */
public class BloomFilter<E> {
	/**
	 * The number of bits per object in the filter: n / |S| =  # of bits per object
	 */
	private static final int BITS_PER_OBJECT = 32;
	
	/**
	 * The length of the bit vector.
	 */
	private final int LENGTH;
	
	/**
	 * The elements of the BloomFilter.
	 */
	private BitSet elements;
	
	/**
	 * The number of elements in the BloomFilter.
	 */
	private int size;
	
	/**
	 * Constructs a new BloomFilter with n # bits assigned to each object.
	 * 
	 * @param size - The number of objects to be added to the BloomFilter
	 */
	public BloomFilter(int size) {
		LENGTH = size * BITS_PER_OBJECT;
		elements = new BitSet(LENGTH);
	}
	
	/**
	 * Adds an element into the BloomFilter to remember. 
	 * 
	 * @param element - The element to add to the set 
	 */
	public void add(E element) {
		elements.set(defaultHash(element));
		elements.set(secondaryHash(element));
		size++;
	}
	
	/**
	 * Returns whether or not an element is contained within the BloomFilter.
	 * 
	 * @param element - The element to check
	 * @return True if the element is in the bloom filter and false otherwise
	 */
	public boolean contains(E element) {
		return elements.get(defaultHash(element)) && elements.get(secondaryHash(element));
	}
	
	/**
	 * Returns the index of the hash code method element implements.
	 * 
	 * @param element - The element to add to the BloomFilter
	 * @return The index of the element in the set
	 */
	private int defaultHash(E element) {
		return element.hashCode() % LENGTH;
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
		return (h ^ (h >>> 7) ^ (h >>> 4)) % LENGTH;
	}
}
