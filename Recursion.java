package Cracking_The_Coding_Interview;


public class Recursion {
	public static void main(String[] args) {
		System.out.println(permutations("Hello"));
	}
	
	/**
	 * Returns the nth fibonacci number.
	 * 
	 * @param n - The index of the fibonacci number
	 * @return The nth fibonacci number
	 */
	public static int fibonacci(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		return fibonacci(n, 0, 1);
	}
	
	/**
	 * Returns the nth fibonacci number
	 * 
	 * @param n - The index of the fiboacci number
	 * @param a - fibonacci(n - 2)
	 * @param b - fibonacci(n - 1)
	 * @return The nth fibonacci number
	 */
	private static int fibonacci(int n, int a, int b) {
		if (n == 0) {
			return a;
		} else if (n == 1) {
			return b;
		}
		return fibonacci(n - 1, b, a + b);
	}
	
	/**
	 * Returns the nth fibonacci number.
	 * 
	 * @param n - The index of the fibonacci number
	 * @return The nth fibonacci number
	 */
	public static int fib(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int a = 0;
		int b = 1;
		int c;
		if (n == 0) {
			return a;
		}
		for (int i = 1; i < n; i++) {
			c = b;
			b += a;
			a = c;
		}
		return b;
	}
	
	/**
	 * Returns all the permutations of the word.
	 * 
	 * @param word - The word to get the permutations of
	 * @return A list of all the permutations
	 */
	public static java.util.ArrayList<String> permutations(String word) {
		java.util.ArrayList<String> permutations = new java.util.ArrayList<String>(); // Since I have implemented ArrayList in this folder
		if (word == null) { 
			return null;
		} else if (word.length() <= 1) { // Only permutation is itself
			permutations.add(word);
		} else {
			char first = word.charAt(0); 
			word = word.substring(1);
			java.util.ArrayList<String> words = permutations(word); // Permute everything else
			for (String subWord : words) {
				for (int i = 0; i <= subWord.length(); i++) { // Insert first in all possible positions
					permutations.add(subWord.substring(0, i) + first + subWord.substring(i));
				}
			}
		}
		return permutations;
	}
	
	/**
	 * Returns base raised to the exp power.
	 * 
	 * @param base - Base of the exponent
	 * @param exp - Power
	 * @return The base raised to the exp power
	 */
	public static double pow(double base, double exp) {
		if (exp < 0) {
			return 1 / pow(base, exp * -1);
		} else if (exp == 0) {
			return 1;
		} else if (exp == 1) {
			return base;
		} else {
			double half = pow(base, exp / 2);
			return half * half * pow(base, exp % 2);
		}
	}
}
