package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Random practice from chapter 1 of Cracking the Coding Interview.
 * @author RDrapeau
 */
public class StringsandArrays {

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
	
	/**
	 * Returns the index of needle in the haystack.
	 * 
	 * @param haystack - The String to search in
	 * @param needle - The String to search for
	 * @return The index of the first character in needle if there is a match (-1 otherwise)
	 */
	public static int indexOf(String haystack, String needle) {
		return kmp(haystack, needle, createDFA(haystack, needle));
	}
	
	/**
	 * Runs the KMP String matching algorithm to determine the first index of the match.
	 * 
	 * @param haystack - The String to search in
	 * @param needle - The String to search for
	 * @param next - The DFA of needle
	 * @return The index of the first character in needle if there is a match (-1 otherwise)
	 */
	private static int kmp(String haystack, String needle, int[] next) {
		int j = 0;
		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				j++;
			} else {
				j = next[j];
			}
			if (j == needle.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}
	
	/**
	 * Creates a DFA for the needle with haystack - if there is a mismatch what is the longest
	 * sequence possible so far with needle and haystack.
	 * 
	 * @param haystack - The String to search in
	 * @param needle - The String to search for
	 * @return The DFA for needle and haystack
	 */
	private static int[] createDFA(String haystack, String needle) {
		int i = 0;
		int[] next = new int[needle.length()];
		for (int j = 1; j < needle.length(); j++) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				next[j] = next[i++];
			} else {
				next[j] = i + 1;
				i = next[i];
			}
		}
		return next;
	}
	
	/**
	 * Returns the longest common substring between two Strings. 
	 * 
	 * @param haystack - First String
	 * @param needle - Second String
	 * @return The longest common substring between two Strings
	 */
	public static String longestCommonSubstring(String haystack, String needle) {
		String common = "";
		String[][] matrix = new String[2][needle.length()];
		for (int i = 0; i < haystack.length(); i++) {
			for (int j = 0; j < needle.length(); j++) {
				if (haystack.charAt(i) == needle.charAt(j)) {
					int row = i % 2;
					// Add the results from the previous computation
					matrix[row][j] = (j == 0 ? "" : matrix[(row == 0 ? 1 : 0)][j - 1]) + haystack.charAt(i);
					if (matrix[row][j].length() > common.length()) {
						common = matrix[row][j];
					}
				}
			}
		}
		return common;
	}
	
	/**
	 * Computes the edit distance required to make these two strings equivalent.
	 * 
	 * @param word - First String
	 * @param other - Second String
	 * @param change - Cost to change a character
	 * @param delete - Cost to delete a character
	 * @param insert - Cost to insert a character
	 * @return The minimum required edits
	 */
	public static int editDistance(String word, String other, int change, int delete, int insert) {
		word = " " + word;
		other = " " + other;
		int[][] matrix = new int[word.length()][other.length()];
		for (int row = 0; row < word.length(); row++) {		
			matrix[row][0] = row * insert;
		}
		for (int col = 0; col < other.length(); col++) {
			matrix[0][col] = col * delete;
		}
		int[] best = new int[3]; // [change, delete, insert]
		for (int i = 1; i < word.length(); i++) {
			for (int j = 1; j < other.length(); j++) {
				best[0] = matrix[i - 1][j - 1] + (word.charAt(i) == other.charAt(j) ? 0 : change);
				best[1] = matrix[i - 1][j] + delete;
				best[2] = matrix[i][j - 1] + insert;
				int min = best[0];
				for (int k = 1; k < best.length; k++) {
					if (best[k] < min) {
						min = best[k];
					}
				}
				matrix[i][j] = min;
			}
		}
		return matrix[word.length() - 1][other.length() - 1];
	}
	
	/**
	 * Returns all the permutations of the String.
	 * 
	 * @param word - String to permute
	 * @return A Set of permutations
	 */
	public static Set<String> permute(String word) {
		Set<String> permutations = new HashSet<String>();	
		if (word.length() <= 1) {
			permutations.add(word);
		} else {
			Set<String> words = permute(word.substring(1));
			for (String w : words) {
				for (int i = 0; i <= w.length(); i++) {
					permutations.add(w.substring(0, i) + word.charAt(0) + w.substring(i));
				}
			}
		}
		return permutations;
	}
	
	/**
	 * Returns the pivot in the rotated sorted array.
	 * 
	 * @param a - The array
	 * @return The index of the pivot
	 */
	public static int findPivot(int[] a) {
		int left = 0;
		int right = a.length;
		while (left < right) {
			int middle = left + (right - left) / 2;
			if ((middle == a.length - 1 || a[middle] < a[middle + 1]) && (middle == 0 || a[middle] < a[middle - 1])) {
				return middle;
			} else if (a[middle] > a[left] && a[middle] <= a[right - 1] || a[middle] < a[left] && a[middle] <= a[right - 1]) {
				right = middle;
			} else {
				left = middle + 1;
			}
		}
		return -1;
	}
	
	/**
	 * Returns an array of row, col entries for the words in the grid.
	 * 
	 * @param grid - The word search to use
	 * @param words - The words to search for
	 * @return Array of row, col for each word in words (null if it is not in the grid)
	 */
	public static String[] find(String[] grid, String[] words) {
		String[] results = new String[words.length];
		for (int i = 0; i < words.length; i++) {
			results[i] = find(grid, words[i], 0, 0, 0);
		}
		return results;
	}
	
	/**
	 * Returns the first index of the word in the grid as if the grid were a word find.
	 * 
	 * @param grid - The grid of words to search through
	 * @param word - The word to search for
	 * @param index - The current index in the word
	 * @param row - The current row of the puzzle
	 * @param col - The current column of the puzzle
	 * @return The row and col of the first matching letter in the matching sequence
	 */
	private static String find(String[] grid, String word, int index, int row, int col) {
		if (index == word.length()) return row + " " + col;
		String result = null;
		if (row < grid.length && col < grid[row].length()) { // In bounds
			if (word.charAt(index) == grid[row].charAt(col)) { // Matching letter
				boolean right = col + word.length() - index - 1 < grid[row].length();
				boolean down = row + word.length() - index - 1 < grid.length;
				index++;
				if (right) {
					result = find(grid, word, index, row, col + 1); // Check right
				}
				if (result == null && down) {
					result = find(grid, word, index, row + 1, col); // Check down
				}
				if (result == null && down && right) {
					result = find(grid, word, index, row + 1, col + 1); // Check down right
				}
				if (result != null) {
					result = row + " " + col; // Return the starting indices
				} else {
					index = 0; // Reset back to the beginning
				}
			} 
			if (index == 0) { // If back at the beginning
				if (col + 1 < grid[row].length()) {
					result = find(grid, word, index, row, col + 1);
				} else if (result == null && row + 1 < grid.length) {
					result = find(grid, word, index, row + 1, 0);
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns the maximum weighted independent set of the integer array.
	 * 
	 * @param a - The weights of each vertex
	 * @return The maximum independent set
	 */
	public static List<Integer> weightedIndependentSet(int[] a) {
		int[] max = new int[a.length + 1];
		max[1] = a[0];
		for (int i = 2; i <= a.length; i++) {
			max[i] = Math.max(max[i - 1], max[i - 2] + a[i - 1]);
		}
		List<Integer> result = new ArrayList<Integer>();
		for (int i = max.length - 1; i > 1; i--) {
			if (max[i - 2] + a[i - 1] >= max[i - 1]) {
				result.add(a[i - 1]);
				i--;
			} 
			if (i == 2) {
				result.add(a[0]);
			}
		}
		return result;
	}
	
	/**
	 * Computes the Needleman-Wunsch Score for the two strings.
	 * 
	 * @param a - First String
	 * @param b - Second String
	 * @param change - Penalty for changing a character
	 * @param gap - Penalty for inserting a gap
	 * @param reconstruct - Whether to print out the reconstruction or not
	 * @return The needleman-wunsch score
	 */
	public static int needlemanWunsch(String a, String b, int change, int gap, boolean reconstruct) {
		int[][] matrix = new int[a.length() + 1][b.length() + 1];
		for (int row = 0; row < matrix.length; row++) {
			matrix[row][0] = row * gap;
		}
		for (int col = 0; col < matrix[0].length; col++) {
			matrix[0][col] = col * gap;
		}
		for (int row = 1; row < matrix.length; row++) {
			for (int col = 1; col < matrix[row].length; col++) {
				int match = matrix[row - 1][col - 1] + (a.charAt(row - 1) == b.charAt(col - 1) ? 0 : change);
				int gapA = matrix[row - 1][col] + gap;
				int gapB = matrix[row][col - 1] + gap;
				matrix[row][col] = Math.min(match, Math.min(gapA, gapB));
			}
		}
		if (reconstruct) {
			int row = a.length();
			int col = b.length();
			while (row > 0 && col > 0) {
				if (matrix[row - 1][col - 1] + change == matrix[row][col]) {
					row--;
					col--;
				} else if (matrix[row - 1][col] + gap == matrix[row][col]) {
					b = b.substring(0, col) + "_" + b.substring(col, b.length());
					row--;
				} else if (matrix[row][col - 1] + gap == matrix[row][col]) {
					a = a.substring(0, row) + "_" + a.substring(row, a.length());
					col--;
				} else {
					row--;
					col--;
				}
			}
			System.out.println(a);
			System.out.println(b);
		}
		return matrix[matrix.length - 1][matrix[0].length - 1];
	}
	
	/**
	 * Returns the first integer array that sums to 0.
	 * 
	 * @param a - The integer array
	 * @return The first integer array that sums to 0
	 */
	public static int[] zeroSum(int [] a) {
		Map<Integer, Integer> done = new HashMap<Integer, Integer>();
		int curSum = 0;
		for (int i = 0; i < a.length; i++) {
			curSum += a[i];
			if (done.containsKey(curSum)) {
				return Arrays.copyOfRange(a, done.get(curSum) + 1, i + 1);
			} else if (curSum == 0) {
				return Arrays.copyOfRange(a, 0, i + 1);
			}
			done.put(curSum, i);
		}
		return null;
	}
}