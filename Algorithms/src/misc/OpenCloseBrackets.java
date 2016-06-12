package misc;

public class OpenCloseBrackets {
	public static void printBrackets(int n) {
		char[] brackets = new char[2 * n];
		printOpenCloseBraces(brackets, 0 , 0, 0);
	}

	private static void printOpenCloseBraces(char[] brackets, int start, int openBracksCount, int closeBracketsCount) {
		if(openBracksCount + closeBracketsCount == brackets.length){
			System.out.println(new String(brackets));
			return;
		}
		if(openBracksCount <= (brackets.length/2 - 1)){
			brackets[start] = '{';
			printOpenCloseBraces(brackets, start + 1, openBracksCount + 1, closeBracketsCount);
		}
		if(closeBracketsCount < openBracksCount){
			brackets[start] = '}';
			printOpenCloseBraces(brackets, start + 1, openBracksCount , closeBracketsCount + 1);
		}
		
	}
	
	public static void main(String[] args) {
		printBrackets(10);
	}
}
