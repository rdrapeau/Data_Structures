package Algorithms;

/**
 * A Class that represents a person height and weight for the toppling gymnast problem.
 * @author RDrapeau
 *
 */
public class Person implements Comparable<Person> {
	public int weight;
	public int height;
	
	public Person(int height, int weight) {
		this.weight = weight;
		this.height = height;
	}
	
	public String toString() {
		return height + " : " + weight;
	}

	public int compareTo(Person other) {
		if (other.height == this.height) {
			return this.weight - other.weight;
		}
		return this.height - other.height;
	}
}
