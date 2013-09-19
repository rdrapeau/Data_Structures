package Support;

/**
 * Represents an item for the knapsack problem.
 * @author RDrapeau
 *
 */
public class Item {
	/**
	 * Value of the item.
	 */
	private int value;
	
	/**
	 * Size of the item.
	 */
	private int size;
	
	/**
	 * Constructs a new item.
	 * 
	 * @param value - The value of the item
	 * @param size - The size of the item
	 */
	public Item(int value, int size) {
		this.value = value;
		this.size = size;
	}
	
	/**
	 * Returns the value of the item.
	 * 
	 * @return Value
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Returns the size of the item.
	 * 
	 * @return Size
	 */
	public int getSize() {
		return this.size;
	}
	
	public String toString() {
		return this.value + " : " + this.size;
	}
}
