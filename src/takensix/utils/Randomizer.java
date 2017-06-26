package takensix.utils;

import java.util.Random;

public class Randomizer {
	static Random random;
	
	static {
		random = new Random();
	}
	
	public static int nextInt(int minIncluded, int maxIncuded) {
		return random.nextInt(maxIncuded) + minIncluded;
	}
}
