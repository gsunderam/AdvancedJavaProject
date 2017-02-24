package examples;

import java.util.Arrays;
import java.util.Scanner;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Aug 31, 2016
 */
public class ProductMaximize {
	private static Scanner sc;

 public static void main(String[] args) {
  sc = new Scanner(System.in);
  int n = sc.nextInt();
  int [] arr = new int [n];
  for(int i = 0; i < n ; i++){
   arr [i] = sc.nextInt();
  }
  productMaximizeV3(n, arr);
 }

	/**
	 * This is the second best. two values of max are calculated and compared. Slightly less
	 * efficient than V3 because of the multiplications
	 * @param n
	 * @param arr
	 */
	private static void productMaximizeV2(int n, int[] arr) {
  	if(n < 3){
   		System.out.println(0);
  	}else{
   		 Arrays.sort(arr);
			 int max = 0, prod2 = 0, prod1 = 0;
			 if(arr[0] < 0 && arr[1] < 0) {
				max = (prod2 = Math.abs(arr[0]) * Math.abs(arr[1]) * arr[n-1]) >= (prod1 = arr[n-1] * arr[n-2] * arr[n-3]) ?
				 		prod2 : prod1;
			 } else {
				  max = arr[n-3]*arr[n-2]*arr[n-1];
			 }
			
			 System.out.println(max);
  }
 }

	/**
	 * This is by far the efficient algorithm to accomplish this task. Max is calculated only once.
	 * @param n
	 * @param arr
	 */
	private static void productMaximizeV3(int n, int[] arr) {
  	if(n < 3){
   		System.out.println(0);
  	}else{
   		 Arrays.sort(arr);
			 int max = 0, prod2 = 0, prod1 = 0;
			 if(arr[0] < 0 && arr[1] < 0) {
				max = (prod2 = Math.abs(arr[0]) * Math.abs(arr[1])) >= (prod1 = arr[n-2] * arr[n-3]) ?
				 		prod2 * arr[n-1] : prod1 * arr[n-1];
			 } else {
				  max = arr[n-3]*arr[n-2]*arr[n-1];
			 }
			
			 System.out.println(max);
  }
 }

	/**
	 * This algorithm does NOT work in a few cases. But works fine otherwise.
	 * @param n
	 * @param arr
	 */
	private static void productMaximize(int n, int[] arr) {
  if(n < 3){
   System.out.println(0);
  } else {
   	Arrays.sort(arr);
		stdout(arr);
   	int max = 0;
   	if(arr[0] < 0 && arr[1] < 0) {
    	if(Math.abs(arr[0]) >= arr[n-1] || Math.abs(arr[0]) >= arr[n-2]) {
     		max = arr[0]*arr[1]*arr[n-1];
    	}	else {
     		max = arr[n-3]*arr[n-2]*arr[n-1];
      }
   } else {
    	max = arr[n-3]*arr[n-2]*arr[n-1];
   }
   System.out.println(max);
  }
 }
}
