package examples;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Oct 11, 2015
 */
public class GenericTests {
	public static void main(String[] args) {
		String test = "Test";
		modifyTest(test);

		//Strings are immutable
		stdout("Test is " + test);
	}

	/**
	 * Change won't be visible in main, unless the string is returned
	 * @param test
	 */
	private static void modifyTest(String test) {
		test = test + " New";
	}
}
