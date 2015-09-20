package dp;

import java.util.Stack;

public class LCS {
	Stack<Character> lcs = new Stack<Character>();
	char[][] path;
	private int max(int int1, int int2) {
		if(int1 > int2){
			return int1;
		}else {
			return int2;
		}
	}
	
	public int lcs(char[] A, int end_A, char[] B, int end_B){
		int LCS = 0;
		if(end_A < 0 || end_B < 0){
			return 0;
		}
		if(A[end_A] == B[end_B]){
			LCS = 1 + lcs(A, end_A - 1, B, end_B - 1);
		}else {
			LCS = max(lcs(A, end_A, B, end_B - 1), lcs(A, end_A - 1, B, end_B));
		}
		return LCS;
	}
	
	public static void main(String[] args) {
		char[] A = "AGGTAB".toCharArray();
		char[] B = "GXTXAYB".toCharArray();
		LCS lcs = new LCS();
		lcs.path = new char[A.length][B.length];
		System.out.println(lcs.lcs(A, A.length - 1, B, B.length - 1));
	}
}
