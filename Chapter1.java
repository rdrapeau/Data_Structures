package Cracking_The_Coding_Interview;

import java.util.Arrays;

public class Chapter1 {

	/**
	 * Returns whether or not the input has all unique characters (assumes the string is made up of
	 * 256 ASCII.
	 * 
	 * @param words - String to test
	 * @return True if the string has all unique characters and false otherwise
	 */
	public static boolean allUnique(String words) {
		char[] possible = new char[256];
		for (int i = 0; i < words.length(); i++) {
			if (possible[words.charAt(i)]++ > 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Returns whether or not the input has all unique characters (assumes the string is made up of
	 * 256 ASCII.
	 * 
	 * @param words - String to test
	 * @return True if the string has all unique characters and false otherwise
	 */
	public static boolean allUnique(char[] words) {
		Arrays.sort(words);
		for (int i = 1; i < words.length; i++) {
			if (words[i - 1] == words[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Reverses the input string (char[]).
	 * 
	 * @param letters - The string to reverse
	 */
	public static void reverse(char[] letters) {
		reverseSection(letters, 0, letters.length);
		int start = 0;
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == ' ') {
				reverseSection(letters, start, i);
				start = i + 1;
			}
		}
		reverseSection(letters, start, letters.length);
	}
	
	/**
	 * Reverses a section of the String.
	 * 
	 * @param letters - The string to reverse
	 * @param begin - Start index (inclusive)
	 * @param end - End index (exclusive)
	 */
	private static void reverseSection(char[] letters, int begin, int end) {
		char temp;
		for (int i = begin; i < (begin + end) / 2; i++) {
			temp = letters[i];
			letters[i] = letters[begin + end - i - 1];
			letters[begin + end - i - 1] = temp;
		}
	}
	
	/**
	 * Returns whether or not two words are anagrams of each other.
	 * 
	 * @param word - First word
	 * @param other - Second word
	 * @return True if the words are anagrams of each other and false otherwise
	 */
	public static boolean isAnagram(String word, String other) {
		if (word.length() == other.length()) {
			char[] first = count(word);
			char[] second = count(other);
			for (int i = 0; i < 256; i++) {
				if (first[i] != second[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Returns an array representing the counts of the letters in the word. Assumes 256 character ASCII
	 * 
	 * @param word - Word to count the letters of
	 * @return An array representing the counts of each character in the word
	 */
	private static char[] count(String word) {
		char[] letters = new char[256];
		for (int i = 0; i < word.length(); i++) {
			letters[word.charAt(i)]++;
		}
		return letters;
	}
	
	/**
	 * Returns whether or not two words are anagrams of each other.
	 * 
	 * @param word - First word
	 * @param other - Second word
	 * @return True if the words are anagrams of each other and false otherwise
	 */
	public static boolean isAnagram(char[] word, char[] other) {
		if (word.length == other.length) {
			Arrays.sort(word);
			Arrays.sort(other);
			for (int i = 0; i < word.length; i++) {
				if (word[i] != other[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Rotates a 2-D array by 90 degrees clockwise.
	 * 
	 * @param matrix - The matrix to rotate
	 */
	public static void rotateBy90(int[][] matrix) {
		int size = matrix.length;
		for (int layer = 0; layer < size / 2; layer++) {
			int first = layer;
			int last = size - layer - 1;
			
			for (int i = first; i < last; i++) {
				int offset = i - first;
				int topLeft = matrix[first][i]; // Perform a counterclockwise cyclic swap
				
				// Bottom-Left --> Top-Left
				matrix[first][i] = matrix[last - offset][first];
				// Bottom-Right --> Bottom-Left
				matrix[last - offset][first] = matrix[last][last - offset];
				// Top-Right --> Bottom-Right
				matrix[last][last - offset] = matrix[i][last];
				// Top-Left --> Top-Right
				matrix[i][last] = topLeft;				
			}
		}
	}
	
	/**
	 * Checks the matrix for any value of zero. If an element is zero, it's entire row and column 
	 * is also set to zero.
	 * 
	 * @param matrix - The matrix to set all rows and columns that contain a zero to zero
	 */
	public static void checkZeros(int[][] matrix) {
		int[] badRows = new int[matrix.length];
		int[] badCols = new int[findBiggestRow(matrix)];
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (matrix[row][col] == 0) {
					badRows[row]++;
					badCols[col]++;
				}
			}
		}
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix.length; col++) {
				if (badRows[row] + badCols[col] > 0) {
					matrix[row][col] = 0;
				}
			}
		}
	}
	
	/**
	 * Returns the length of the longest row in the matrix.
	 * 
	 * @param matrix - The matrix to find the largest row
	 * @return The length of the largest row 
	 */
	private static int findBiggestRow(int[][] matrix) {
		int max = 0;
		for (int row = 0; row < matrix.length; row++) {
			max = Math.max(matrix[row].length, max);
		}
		return max;
	}
}
