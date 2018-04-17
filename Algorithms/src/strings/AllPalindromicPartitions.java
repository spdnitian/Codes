package strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class AllPalindromicPartitions {
	
	private static boolean isPalindrome(String input, int startIndex, int endIndex) {
		if(startIndex < endIndex) {
			return input.charAt(startIndex) == input.charAt(endIndex)
					&& isPalindrome(input, startIndex + 1, endIndex - 1);
		}
		return true;
	}

	private static void printPartitions(Stack<String> palPartitions) {
		if(!palPartitions.isEmpty()) {
			String palParttion = palPartitions.pop();
			printPartitions(palPartitions);
			System.out.print(palParttion + " ");
			palPartitions.push(palParttion);
		}
	}
	
	private static void printAllPalindromicPartitionsUtil(String input, Stack<String> palPartitions, int startIndex) {
		if(startIndex > input.length() - 1) {
			printPartitions(palPartitions);
			System.out.println();
		}else {
			for (int size = 1; size <= input.length() - startIndex; size++) {
				if(isPalindrome(input, startIndex, startIndex + size - 1)) {
					palPartitions.push(input.substring(startIndex, startIndex + size));
					printAllPalindromicPartitionsUtil(input, palPartitions, startIndex + size);
					palPartitions.pop();
				}
			}
		}
	}

	public static void printAllPalindromicPartitions(String input) {
		printAllPalindromicPartitionsUtil(input, new Stack<String>(), 0);
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Enter string : ");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String input = bReader.readLine();
		System.out.println("Palindromic Substrings : ");
		printAllPalindromicPartitions(input);
	}

}
