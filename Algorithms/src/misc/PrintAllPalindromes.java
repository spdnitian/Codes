package misc;

public class PrintAllPalindromes {
	private char[] pal;
	private int[] charCounts = new int[256]; // ASCII
	
	public PrintAllPalindromes(String str) {
		// TODO Auto-generated constructor stub
		pal = new char[str.length()];
	}
	
	private void printPalUtil(int left, int right) {
		if(left >= right){
			System.out.println(new String(pal));
		}else{
			for (int i = 0; i < charCounts.length; i++) {
				if(charCounts[i] > 0){
					pal[left] = pal[right] = (char)i;
					charCounts[i] -= 2;
					printPalUtil(left + 1, right - 1);
					charCounts[i] += 2;
				}
			}
		}
	}
	
	private boolean isPalPossible(String str){
		int len = str.length();
		for(int i = 0; i < len; i++){
			charCounts[str.charAt(i)]++;
		}
		
		int noOfOdds = 0;
		for(int i = 0; i < charCounts.length; i++){
			noOfOdds += (charCounts[i] & 1);
			if((charCounts[i] & 1) == 1){
				charCounts[i]--;
				pal[pal.length / 2] = (char) i;
			}
		}
		if(noOfOdds > 1){
			return false;
		}
		
		return true;
	}
	
	public static void printAllPalindromes(String str) {
		PrintAllPalindromes printAllPalindromes = new PrintAllPalindromes(str);
		if(printAllPalindromes.isPalPossible(str)){
			printAllPalindromes.printPalUtil(0, str.length() - 1);
		}else{
			System.out.println("Palindrome not possible!!!");
		}
	}
	
	public static void main(String[] args) {
		printAllPalindromes("aabbcadad");
	}
}
