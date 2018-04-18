package misc;

public class Palindrome {
	public static boolean isPalindrome(String string, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			return string.charAt(leftIndex) == string.charAt(rightIndex)
					&& isPalindrome(string, leftIndex + 1, rightIndex - 1);
		}
		return true;
	}
}
