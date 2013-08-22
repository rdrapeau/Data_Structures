package Algorithms;

public class Mathematics {
	public static void main(String[] args) {
		System.out.println(lcm(4, 10));
	}

	/**
	 * Returns the greatest common divisor between two numbers.
	 * 
	 * @param a - The first number
	 * @param b - The second number
	 * @return The greatest common divisor
	 */
	public static int gcd(int a, int b) {
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
}
