package takensix.utils;

import java.util.Random;

public class Randomizer {
	static Random random;
	
	static {
		random = new Random();
	}
	
	public static int nextInt(int minIncluded, int maxIncluded) {
		return random.nextInt(maxIncluded - minIncluded + 1) + minIncluded;
	}
	
	public static int nextInt(int maxExcluded) {
		return random.nextInt(maxExcluded);
	}
}
