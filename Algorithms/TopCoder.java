package Algorithms;

public class TopCoder {
	/**
	 * Returns the number of people required to be in the room in order to guarantee that there is
	 * at least a targetPercent chance that two or more have the same birthday.
	 * 
	 * @param targetPercent - Desired percentage of two or more having the same birthday
	 * @param daysPerYear - Days in a year
	 * @return The number of people in the room
	 */
	public static int birthdayOdds(int targetPercent, int daysPerYear) {
		int count = daysPerYear;
		double currentPercent = 100;
		while (currentPercent > 100 - targetPercent && count > 0) {
			currentPercent *= count-- / (double) daysPerYear;
		}
		return daysPerYear - count;
	}
}
