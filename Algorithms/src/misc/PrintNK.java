package misc;

import java.util.Arrays;

public class PrintNK {
	public static void printNK(int n, int k) {
		int[] kArr = new int[k];
		printUtil(kArr, n, k, 1);
	}
	private static void printUtil(int[] kArr, int n ,int k, int start) {
		if(k == 0){
			System.out.println(Arrays.toString(kArr));
		}else {
			for(int i = start; i <= n; i++){
				int nextNum = i + 1;
				kArr[kArr.length - k] = i;
				printUtil(kArr, n, k - 1, nextNum);
			}
		}
	}
	public static void main(String[] args) {
		printNK(5, 3);
	}

}
