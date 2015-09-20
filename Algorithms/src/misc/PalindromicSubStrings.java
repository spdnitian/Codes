package misc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PalindromicSubStrings {
  /*
 * Complete the function below.
 */
	static boolean isOdd(int number){
		return (number & 1) == 1;
	}
    static boolean isPalindrome(String str){
        int start = 0, end = str.length() - 1;
        while(start < end){
            if(str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    static boolean isPalindrome(String str, int centerIndex,int startIndex, int endIndex){
        int start = 0, end = str.length() - 1;
        while(start < end){
            if(str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    static int countPalindromesCenteredAt(String str, int centerIndex, int modifiedLength, HashMap<String, Integer> lookUpMap){
        int count = 0;
        int window = (centerIndex <= modifiedLength - centerIndex - 1) ? centerIndex : modifiedLength - centerIndex - 1;
        int step = isOdd(centerIndex) ? 1 : 0;
        for(; step <= window; step += 2){
            int startIndex = (centerIndex - step) >> 1;
            int endIndex = (centerIndex + step) >> 1;
            String subStr = str.substring(startIndex, endIndex + 1);
            if(isPalindrome(subStr)){
                if(lookUpMap.get(subStr) == null){
                    count++;
                    lookUpMap.put(subStr, 1);
                    System.out.println(subStr);
                }else{
                    lookUpMap.put(subStr, lookUpMap.get(subStr) + 1);
                }
            }
        }
        return count;
    }
    static int palindrome(String str) {
        int str_len = str.length();
        int count = 0;
        HashMap<String, Integer> distintSubPalindromes = new HashMap<String, Integer>();
        int modifiedLength = 2 * str_len - 1;
        for(int i=0; i < modifiedLength; i++){
            count+= countPalindromesCenteredAt(str, i, modifiedLength, distintSubPalindromes);
        }
        System.out.println(count);
        return count;
    }
    
    public static int longestPalindromicSubString(String str, int centerIndex, int modifiedLength){
    	int window = (centerIndex <= modifiedLength - centerIndex - 1) ? centerIndex : modifiedLength - centerIndex - 1;
        int step = isOdd(centerIndex) ? 1 : 0;
        int maxLength = 0;;
        boolean isPalindrome = true;
        for(; isPalindrome && step <= window; step += 2){
            int startIndex = (centerIndex - step) >> 1;
            int endIndex = (centerIndex + step) >> 1;
        	if(str.charAt(startIndex) == str.charAt(endIndex)){
        		maxLength = endIndex - startIndex + 1;
        	}else {
				isPalindrome = false;
			}
        }
        return maxLength;
    }
    
    public static void printLongestPalindromicSubString(String str){
    	int totalMax = 1,max = 0;
    	int modifiedLength = 2 * str.length() - 1;
    	int originalIndex = 0;
        for(int i = 0; i < modifiedLength; i++){
            max = longestPalindromicSubString(str, i, modifiedLength);
            if(max > totalMax){
            	originalIndex = i >> 1;
            	totalMax = max;
            }
        }
        int leftIndex = originalIndex - (totalMax - (totalMax >> 1) - 1);
        int rightIndex = originalIndex + (totalMax >> 1);
        System.out.println(str.substring(leftIndex, rightIndex + 1));
        System.out.println(totalMax);
    }
    
    public static void main(String[] args) throws IOException{
    	System.out.println("Enter input: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        //palindrome(input);
        printLongestPalindromicSubString(input);
        in.close();
    }
}
