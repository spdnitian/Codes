package dp;

import java.util.Scanner;

public class CountStringsWithoutConsecutive1 {
	public static long getCount(long n) {
		return getCount(n, -1);
	}
	
	public static long getCountDP(int n){
		if(n == 0){
			return 0;
		}
		long[] arr1 = new long[n]; // each index contains no of strings with length 'index + 1' ending with 0
		long[] arr2 = new long[n]; // each index contains no of strings with length 'index + 1' ending with 1
		
		arr1[0] = arr2[0] = 1;
		for(int i = 1; i < n ; i++){
			arr1[i] = arr1[i - 1] + arr2[i - 1];
			arr2[i] = arr1[i - 1];
		}
		
		return arr1[n - 1] + arr2[n - 1];
	}

	private static long getCount(long n, int lastVal) {
		if(n <= 1){
			return n;
		}
		if(lastVal == -1){
			return getCount(n, 0) + getCount(n , 1);
		}else if(lastVal == 0){
			return getCount(n - 1, 0) + getCount(n - 1 , 1);
		}else{
			return getCount(n - 1, 0);
		}
	}
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		for (int i = 0; i <= n; i++) {
			System.out.println(getCountDP(i));
		}
		System.out.println("Done");
		scn.close();
	}
}
