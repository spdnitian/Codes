package strings;

import java.util.Arrays;
import java.util.Scanner;

public class KMPPrefix {
	public static int[] prefixArr(String pattern) {
		int[] prefixArr = new int[pattern.length()];
		prefixArr[0] = 0;
		int j = 0;
		for(int i = 1; i < prefixArr.length;i++){
			while(j != 0 && pattern.charAt(i) != pattern.charAt(j)){
				j = prefixArr[j - 1];
			}
			if(pattern.charAt(i) == pattern.charAt(j)){
				prefixArr[i] = ++j;
			}
		}
		return prefixArr;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(Arrays.toString(prefixArr(scanner.nextLine())));
		scanner.close();
	}
}
