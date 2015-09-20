package misc;

public class GCD {
	
	public static boolean isEven(int a) {
		return (a & 1) == 0;
	}
	
	public static int gcd(int a, int b) {
		if( (a & b) == 0){
			return a | b;
		}
		if(a == b){
			return a;
		}
		if(isEven(a) && isEven(b)){
			return 2 * gcd(a >>> 1, b >>> 1);
		}
		if(isEven(a)){
			return gcd(a >>> 1, b);
		}
		if(isEven(b)){
			return gcd(a, b >>> 1);
		}
		return a > b ? gcd(a - b, b) : gcd(a, b - a);
	}

	public static void main(String[] args) {
		System.out.println(gcd(0, 2));
	}

}
