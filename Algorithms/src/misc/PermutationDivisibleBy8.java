package misc;

import java.util.Scanner;

public class PermutationDivisibleBy8 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String num = scn.nextLine();
		printIsDivisible(num);
		scn.close();
	}

	private static void printIsDivisible(String number) {
		boolean isDivisible = false;
		int length = number.length();
		if(length < 3){
			isDivisible = Integer.parseInt(number) % 8 == 0;
		}else {
			boolean[] digits = new boolean[10];
			for (int i = 0; i < length; i++) {
				int digit = Integer.parseInt(number.charAt(i) + "");
				digits[digit] = true;
			}
	outer : for (int i = 0; i < digits.length; i++) {
				for (int j = 0; j < digits.length; j++) {
					for (int k = 0; k < digits.length; k++) {
						if(i != j && i != k && j != k && digits[i] && digits[j] && digits[k]){
							int num = 100 * i + 10 * j + k;
							isDivisible = num % 8 == 0;
							if(isDivisible){
								break outer;
							}
						}
					}
				}
			}
		}
		if(isDivisible){
			System.out.println("Yes");
		}else {
			System.err.println("No");
		}
		
	}
}
