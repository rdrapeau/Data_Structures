package Cracking_The_Coding_Interview;

/**
 * Implementation of the Hash Table data structure.
 * @author RDrapeau
 *
 */
public class HashTable<K, V> {
	/**
	 * The default starting size of the HashTable.
	 */
	private static final int DEFAULT_SIZE = 19;
	
	/**
	 * The elements in the HashTable.
	 */
	private Node[] elements;
	
	/**
	 * The number of elements in the HashTable.
	 */
	private int size;
	
	/**
	 * Constructs a new HashTable.
	 */
	public HashTable() {
		elements = (Node[]) new Object[DEFAULT_SIZE];
	}
	
	/**
	 * Puts the key, value pair into the HashTable (Duplicates not supported).
	 * 
	 * @param key - The key of the value
	 * @param value - The value of the entry
	 */
	public void put(K key, V value) {
		int index = getIndexOf(key);
		elements[index] = new Node(key, value, elements[index]);
		size++;
	}
	
	/**
	 * Returns the index of the key in the HashTable.
	 * 
	 * @param key - The key to find the index of
	 * @return The index of the key
	 */
	private int getIndexOf(K key) {
		return key.hashCode() % elements.length;
	}
	
	
	/**
	 * Implementation of a LinkedList Node for a HashTable.
	 * @author RDrapeau
	 *
	 */
	private class Node {
		/**
		 * The key of the Node.
		 */
		private K key;
		
		/**
		 * The value of the Node.
		 */
		private V value;
		
		/**
		 * The Node immediately after this one. 
		 */
		private Node next;
		
		/**
		 * Constructs a new Node with a key and a value with no immediate following Node.
		 * 
		 * @param key - The key for the value
		 * @param value - The value of the Node
		 */
		public Node(K key, V value) {
			this(key, value, null);
		}
		
		/**
		 * Constructs a new Node with a key and a value and next as the next Node.
		 * 
		 * @param key - The key for the value
		 * @param value - The value of the Node
		 * @param next - The Node immediately after this one
		 */
		public Node(K key, V value, Node next) {
			this.key = key;
			this.next = next;
		}
	}
}
