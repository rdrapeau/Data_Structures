package Cracking_The_Coding_Interview;


public class Recursion {
	public static void main(String[] args) {
		printParens(6);
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
	
	/**
	 * Returns the number of paths from the bottom left corner to the top right corner.
	 * 
	 * @param matrix - The 2d array
	 * @return The number of paths from (0,0) to the top right corner
	 */
	public static int paths(int[][] matrix) {
		return paths(matrix, 0, 0, matrix.length);
	}
	
	/**
	 * Returns the number of paths from the bottom left corner to the top right corner in a
	 * 2d array - the path must consist of all zeros. (anything else is an obstacle)
	 * 
	 * @param matrix - The 2d array to check
	 * @param x - Current x position
	 * @param y - Current y position
	 * @param length - Size of the array
	 * @return The number of paths from (x,y) to the top right corner
	 */
	private static int paths(int[][] matrix, int x, int y, int length) {
		if (x + 1 == length && y + 1 == length) {
			return 1;
		} 
		int count = 0;
		if (x + 1 < length && matrix[y][x + 1] == 0) {
			count += paths(matrix, x + 1, y, length);
		}
		if (y + 1 < length && matrix[y + 1][x] == 0) {
			count += paths(matrix, x, y + 1, length);
		}
		return count;
	}
	
	/**
	 * Prints all the possible combinations of valid parens.
	 * 
	 * @param count - The number of complete parens
	 */
	public static void printParens(int count) {
		if (count < 0) {
			throw new IllegalArgumentException();
		}
		printParens(count, count, "");
	}
	
	/**
	 * Prints out all the possible combinations of left and right parens.
	 * 
	 * @param left - Number of left parens left
	 * @param right - Number of right parens left
	 * @param soFar - The paren arrangement so far
	 */
	private static void printParens(int left, int right, String soFar) {
		if (left == 0 && right == 0) {
			System.out.println(soFar);
		}
		if (left > 0) {
			printParens(left - 1, right, soFar + "(");
		}
		if (right > left) {
			printParens(left, right - 1, soFar + ")");
		}
	}
}
