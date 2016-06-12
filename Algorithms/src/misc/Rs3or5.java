package misc;

import java.util.Scanner;

public class Rs3or5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		System.out.println(isPossibleWith3or5(n));
		scanner.close();
	}

	public static boolean isPossibleWith3or5(int n) {
		if(n < 0){
			return false;
		}
		if(n == 0){
			return true;
		}
		return isPossibleWith3or5(n - 3) || isPossibleWith3or5(n - 5);
	}
}
