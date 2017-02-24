package examples;

import static log.Logger.*;
import java.util.Arrays;

/**
 * This Alogorithm find missing numbers in ANY given series.
 * 
 * @author A159991(G Sunderam)
 */

public class FindAllMissingNumbers {
	private static final int END = 17;
	private static final int BEGIN = 1;
		
	public static void main(String[] args) {
		//Given series.
		int [] series = {BEGIN,9,7,6,4,10,11,5,16,12,15,13,END};
		Arrays.sort(series);
		printGivenSeries(series);
		
			for (int i = BEGIN - 1;i < series.length - 1;i++) {
				try {
					int diff = series[i + 1] - series[i];
					int temp = series[i];
					if (diff != 1) {
						for(;diff > 1;diff--) {
							stdout("Missing number: " + ++temp);
						}
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					stdout("Array index exception: " + e.getMessage());
				}
			}
	}

	private static void printGivenSeries(int[] series) {
		print("Sorted Series: {");
		for(int i = 0;i < series.length;i++) {
			print(series[i]);
			if (i != series.length - 1) print(",");
		}
		print("}\n\n");
	}
}
