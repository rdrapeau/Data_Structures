package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mathematics {
	/**
	 * Returns the greatest common divisor between two numbers.
	 * 
	 * @param a - The first number
	 * @param b - The second number
	 * @return The greatest common divisor
	 */
	public static int gcd(int a, int b) {
		if (a < 0 || b < 0) {
			throw new IllegalArgumentException();
		}
		if (b > a) {
			return gcd(b, a);
		} else if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	/**
	 * Returns the greatest common divisor between two numbers.
	 * 
	 * @param a - The first number
	 * @param b - The second number
	 * @return The greatest common divisor
	 */
	public static int gcdR(int a, int b) {
		int temp;
		while (b != 0) {
			temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
	
	/**
	 * Returns the least common multiple between two numbers.
	 * 
	 * @param a - The first number
	 * @param b - The second number
	 * @return The least common multiple
	 */
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b); // a * b = gcd(a, b) * lcm(a, b)
	}
	
	/**
	 * Returns the square root of a number a.
	 * 
	 * @param a - The number
	 * @return The square root of the number
	 */
	public static double sqrt(double a) {
		if (a < 0) {
			throw new IllegalArgumentException();
		} else if (a == 0 || a == 1) {
			return a;
		}
		double left = 0;
		double right = a;
		double middle;
		double squared;
		double epsilon = 0.00001; // Allowable error for the result
		while (right - left > epsilon) {
			middle = (right + left) / 2;
			squared = middle * middle;
			if (Math.abs(squared - a) < epsilon) {
				return middle;
			} else if (squared < a) {
				left = middle;
			} else {
				right = middle;
			}
		}
		return (left + right) / 2;
	}
	
	/**
	 * Returns whether or not two numbers add up to n in the array.
	 * 
	 * @param a - The array
	 * @param n - The target sum
	 * @return True if two distinct numbers in the array add to sum and false otherwise
	 */
	public static boolean twoSum(int[] a, int n) {
		Map<Integer, Integer> sum = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) {
			sum.put(a[i], i);
		}
		for (int i = 0; i < a.length; i++) {
			int num = n - a[i];
			if (sum.containsKey(num) && sum.get(num) != i) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns whether or not three numbers add up to n in the array.
	 * 
	 * @param a - The array (Will be sorted after finishing)
	 * @param n - The target sum
	 * @return True if three distinct numbers in the array add up to sum and false otherwise
	 */
	public static boolean threeSum(int[] a, int n) {
		Arrays.sort(a);
		int b;
		int c;
		for (int i = 0; i < a.length - 2; i++) {
			b = i + 1;
			c = a.length - 1;
			while (b < c) {
				int sum = a[i] + a[b] + a[c];
				if (sum == n) {
					return true;
				} else if (sum < n) {
					b++;
				} else {
					c--;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns a list of primes under the limit.
	 * 
	 * @param limit - The maximum number (exclusive)
	 * @return A list of primes below the limit
	 */
	public static List<Integer> getPrimes(int limit) {
		List<Integer> primes = new ArrayList<Integer>();
		BitSet numbers = new BitSet(limit);		
		for (int i = 2; i < limit; i++) {
			for (int j = i + i; j < limit; j += i) {
				numbers.set(j);
			}
		}
		for (int i = 2; i < limit; i++) {
			if (!numbers.get(i)) {
				primes.add(i);
			}
		}
		return primes;
	}
	
	/**
	 * Returns whether or not the number is prime.
	 * 
	 * @param number - The number to test
	 * @return True if the number is prime and false otherwise
	 */
	public static boolean isPrime(int number) {
		if (number % 2 == 0) return false;
		for (int i = 3; i < Math.sqrt(number); i += 2) {
			if (number % i == 0) return false;
		}
		return true;
	}
	
	/**
	 * Returns whether or not a number is prime based on fermats little theorem.
	 * 
	 * @param number - The number to test
	 * @return True if the number is prime or a carmichael number and false otherwise
	 */
	public static boolean fermat(int number) {
		return pow(2, number - 1) % number == 1;
	}
	
	/**
	 * Computes the base raised to the exp power.
	 * 
	 * @param base - The base of the exponent
	 * @param exp - The exponent
	 * @return The result of base to the exponent power
	 */
	public static double pow(double base, int exp) {
		if (exp == 0) {
			return 1;
		} else if (exp < 0) {
			return 1 / pow(base, exp * -1);
		} else if (exp == 1) {
			return base;
		}
		double half = pow(base, exp / 2);
		return half * half * pow(base, exp % 2);		
	}
}
