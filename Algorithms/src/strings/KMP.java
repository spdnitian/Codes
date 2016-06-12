package strings;

import java.util.ArrayList;
import java.util.Arrays;

public class KMP {
	public static ArrayList<Integer> findPatternKMP(String text, String pattern) { // returns list of starting indexes of pattern
		int[] prefixArr = KMPPrefix.prefixArr(pattern);
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int strlen = text.length(), patternlen = pattern.length(), j = 0;
		for(int i = 0; i < strlen; i++){
			while(j > patternlen - 1 || (j != 0 && pattern.charAt(j) != text.charAt(i))){
				j = prefixArr[j - 1];
			}
			if(pattern.charAt(j) == text.charAt(i)){
				j++;
			}
			if(j == patternlen){
				indices.add(i - j + 1);
				//j = 0;
			}
		}
		return indices;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(findPatternKMP("acacabacacabacacac", "acabaca").toArray()));
	}
}
