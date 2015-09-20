package misc;

public class NoOf6 {
	public int n;
	public int number;
	public boolean printUtilSixSeries(int number, int pow){
		if(number > this.number){
			return false;
		}
		System.out.println(number);
		this.n++;
		int powOf10 = (int) Math.pow(10, pow);
		boolean left6 = true, right6 = true;
		for (int i = 0; i <= 9; i++) {
			if(i != 0  && left6){
				int startWith6 = i * powOf10 + number;
				left6 = printUtilSixSeries(startWith6, pow + 1);
			}
			if(right6){
				int endWith6 = number * powOf10 + i;
				right6 = printUtilSixSeries(endWith6, pow + 1);
			}
		}
		return true;
	}
	
	public static void sixSeries(int n) {
		NoOf6 noOf6 = new NoOf6();
		noOf6.number = n;
		if(n > 6)
		noOf6.printUtilSixSeries(6, 1);
		System.out.println(noOf6.n);
	}
	public static void main(String[] args) {
		sixSeries(100);
	}

}
