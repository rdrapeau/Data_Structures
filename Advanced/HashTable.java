package Advanced;

/**
 * Implementation of the Hash Table data structure.
 * @author RDrapeau
 *
 */
public class HashTable<K, V> {
	/**
	 * The default starting size of the HashTable.
	 */
	private static final int DEFAULT_SIZE = 11;
	
	/**
	 * The threshold of when to resize the HashTable to be bigger.
	 */
	private static final double LOAD = 0.80;
	
	/**
	 * The elements in the HashTable.
	 */
	private Object[] elements;
	
	/**
	 * The number of elements in the HashTable.
	 */
	private int size;
	
	/**
	 * Constructs a new HashTable.
	 */
	public HashTable() {
		elements = new Object[DEFAULT_SIZE];
	}
	
	/**
	 * Puts the key, value pair into the HashTable (Duplicates not supported).
	 * 
	 * @param key - The key of the value
	 * @param value - The value of the entry
	 */
	public void put(K key, V value) {
		if (getLoad() > LOAD) {
			resize(elements.length * 2 + 1);
		}
		int index = getIndexOf(key);
		elements[index] = new Node(key, value, (Node) elements[index]);
		size++;
	}
	
	/**
	 * Returns whether or not the key is in the HashTable.
	 * 
	 * @param key - The key to check
	 * @return True if the key is in the HashTable and false otherwise.
	 */
	public boolean containsKey(K key) {
		Node current = (Node) elements[getIndexOf(key)];
		while (current != null) {
			if (current.key == key) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	/**
	 * Resizes the length of the HashTable to be as big as length.
	 * 
	 * @param length - The new length of the HashTable
	 */
	private void resize(int length) {
		Object[] newElements = new Object[length];
		for (int i = 0; i < elements.length; i++) {
			Node current = (Node) elements[i];
			while (current != null) {
				int index = getIndexOf(current.key);
				newElements[index] = new Node(current.key, current.value, (Node) newElements[index]);
				current = current.next;
			}
		}
		elements = newElements;
	}
	
	/**
	 * Returns the index of the key in the HashTable.
	 * 
	 * @param key - The key to find the index of
	 * @return The index of the key
	 */
	private int getIndexOf(K key) {
		return getIndexOf(key, elements.length);
	}
	
	/**
	 * Returns the index of the key in a array of length.
	 * 
	 * @param key - The key to find the index of
	 * @param length - The length of the array
	 * @return The index of the key
	 */
	private int getIndexOf(K key, int length) {
		int index = key.hashCode() % length;
		if (index < 0) {
			index += length;
		}
		return index;
	}
	
	/**
	 * Returns the size of the HashTable.
	 * 
	 * @return The size of the HashTable
	 */
	private int size() {
		return size;
	}
	
	/**
	 * Returns the alpha parameter of the HashTable: a = n / |S|
	 * 
	 * @return The number of objects per index on average
	 */
	private double getLoad() {
		return size / (double) elements.length;
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
			this.value = value;
			this.next = next;
		}
		
		public String toString() {
			return "[" + key + " : " + value + (next != null ? ", " + next.toString() : "") + "]";
		}
	}
}
