package examples;

import static log.Logger.stdout;

/**
 * This algorithm finds missing numbers in an arithmetic series ONLY if the missing numbers are 
 * seperated by other numbers by 1. i.e. series = 1,3,4,5,6,7,9,11,12,13,15. This finds 2,8,10,14 
 * 
 * @author A159991(G Sunderam)
 */
public class MissingNumbers {
	private static final int END = 18;
	private static final int BEGIN = 1;
	
	public static void main(String[] args) {
		//Given sorted series
		int [] series = {BEGIN,3,4,5,6,7,8,10,11,12,13,14,16,END};
		int [] numbers = new int[END];
		
		for (int i = BEGIN - 1;i < END;i++) {
			numbers[i] = i + 1;
		}
		
		//compareNumbers(series, numbers);
		compareNumbersV2(series, numbers);
	}

	private static void compareNumbersV2(int[] series, int[] numbers) {
		int number_of_missing_numbers = numbers.length - series.length;
		//stdout("No of missing numbers: " + number_of_missing_numbers);
		int start = 1;
		try {
			for(int i = BEGIN - 1;i < END;i++) {
				int diff = series[i] - numbers[i];
				//stdout("Differences in counts: " + diff + " at index = " + i);
				for(int j = start;j <= number_of_missing_numbers;j++) {
					if (diff == j) {
						stdout("Missing number " + j + " = " + (i + j));
						++start;
					} 
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) { //TODO: Fix this
			stdout("Ignore this for now " + e.getMessage());
		}
	}

//	private static void compareNumbers(int[] series, int[] numbers) {
//		boolean first = true, second = true, third = true, fourth = true;
//		try {
//			for(int i = BEGIN - 1;i < END;i++) {
//				int diff = series[i] - numbers[i];
//				//stdout("Differences in counts: " + diff + " at index = " + i);
//				if (diff == 1 && first) {
//					stdout("First missing number " + (i + 1));
//					first = false;
//				} else if (diff == 2 && second) {
//					stdout("Second missing number: " + (i + 2));
//					second = false;
//				} else if (diff == 3 && third) {
//					stdout("Third missing number: " + (i + 3));
//					third = false;
//				} else if (diff == 4 && fourth) {
//					stdout("fourth missing number: " + (i + 4));
//					fourth = false;
//				} 
//			}
//		} catch(ArrayIndexOutOfBoundsException e) { //TODO: Fix this
//			stdout("Ignore this for now " + e.getMessage());
//		}
//	}

}
