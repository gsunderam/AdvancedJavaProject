package examples;

import java.util.Scanner;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Aug 31, 2016
 */
public class StringPermutation {
	
private static Scanner sc;

 public static void main(String[] args) {
		sc = new Scanner(System.in);
		int N = sc.nextInt();
		String [] charArray = new String [N];
		for(int i = 0; i < N ; i++){
		 charArray [i] = sc.next();
		}
		int k = sc.nextInt();
		calculatePermutations(k, charArray, "");
 }

	/**
	 * Idea is recursion and for loop. recursive calls will reduce k to 0 where as the loop will traverse all
	 * combinations.
	 * 
	 * @param k initial value
	 * @param charArray Chars that need to be permuted
	 * @param currentWord initial word
	 */
 private static void calculatePermutations(final int k, final String[] charArray, final String currentWord) {
	  if (k == 0) stdout(currentWord);
	  else {
				for(int i = 0; i < charArray.length; i++) {
					calculatePermutations(k - 1, charArray, currentWord + charArray[i]);
				}
		}
 }
}