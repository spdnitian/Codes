package misc;

import java.io.IOException;
import java.util.Scanner;

public class PalindromicSubStrings {    
    public static int findLongestPalindrome(String str){
    	int max = 0;
    	if(str == null){
    		return max;
    	}
    	int strLen = str.length();
    	int left = 0,right = 0;
    	for(int i = 0; i < strLen; i++){
    		int maxPal = findMaxPalindromeCenteredAt(str, i, i);
    		if(maxPal > max){
    			max = maxPal;
    			left = i - max / 2;
    			right = i + max / 2;
    		}
    		maxPal = findMaxPalindromeCenteredAt(str, i, i + 1);
    		if(maxPal > max){
    			max = maxPal;
    			left = i - (max - 2)/2;
    			right = i + 1 + (max - 2)/2;
    		}
    	}
    	System.out.println(str.substring(left, right + 1));
    	return max;
    }

    private static int findMaxPalindromeCenteredAt(String str, int leftCentre, int rightCentre) {
		int length = str.length();
		int maxPalCentredAt = 0; 
    	while(leftCentre >= 0 && rightCentre <= length - 1){
    		if(str.charAt(leftCentre) == str.charAt(rightCentre)){
    			maxPalCentredAt = rightCentre - leftCentre + 1;
    			leftCentre--;
    			rightCentre++;
    		}else {
				break;
			}
    	}
		return maxPalCentredAt;
	}
	public static void main(String[] args) throws IOException{
    	System.out.println("Enter input: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(findLongestPalindrome(input));
        in.close();
    }
}
