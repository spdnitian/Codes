package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import misc.Palindrome;

public class MinPalindromeCut {
	
	public static int getMinPalindromicCut(String str) {
		return getMinPalindromicCutUtil(str, str.length() - 1);
	}
	
	public static int getMinPalindromicCutDP(String str) {
		int[] minPalindromCutDP = new int[str.length()];
		boolean[][] isPalindrome = getPalindromicConfig(str);
		for(int i = 0; i <= str.length() - 1; i++) {
			int min = str.length() - 1;
			for(int j = i; j >= 0; j--) {
				if(isPalindrome[j][i]) {
					if(j == 0) {
						min = 0;
					}else {
						min = Math.min(min, minPalindromCutDP[j - 1] + 1);
					}
				}
				minPalindromCutDP[i] = min;
			}
		}
		return minPalindromCutDP[str.length() - 1];
	}
	
	private static int getMinPalindromicCutUtil(String str, int lastIndex) {
		if(lastIndex < 1) {
			return 0;
		}
		int min = str.length() - 1;
		for(int i = lastIndex; i >= 0; i--) {
			if(Palindrome.isPalindrome(str, i, lastIndex)) {
				if(i == 0) {
					min = 0;
				}else {
					min = Math.min(min, getMinPalindromicCutUtil(str, i - 1) + 1);
				}
			}
		}
		return min;
	}

	private static boolean[][] getPalindromicConfig(String str) {
		boolean[][] isPalindrome = new boolean[str.length()][str.length()];
		for(int length = 1; length <= str.length(); length++) {
			for(int i = 0; i + length - 1 <= str.length() - 1; i++) {
				int leftIndex = i, rightIndex =  i + length - 1;
				if(length <= 2) {
					isPalindrome[leftIndex][rightIndex] = str.charAt(leftIndex) == str.charAt(rightIndex);
				}else {
					isPalindrome[leftIndex][rightIndex] = (str.charAt(leftIndex) == str.charAt(rightIndex))
															&& isPalindrome[leftIndex + 1][rightIndex - 1];
				}
			}
		}
		return isPalindrome;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Enter string : ");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String input = bReader.readLine();
		System.out.println("Min Palindromic Cut  : " + getMinPalindromicCut(input));
		System.out.println("Min Palindromic Cut (DP) : " + getMinPalindromicCutDP(input));
	}

}
