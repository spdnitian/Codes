package mathmatical;

public class DivideBy3 {
	public boolean isDivisible(int num){
		if(num < 0){
			num = -num;
		}
		if(num == 0){
			return true;
		}
		if(num == 1){
			return false;
		}
		int oddCount = 0,eventCount = 0;
		while(num != 0){
			if((num & 1) != 0){
				eventCount++;
			}
			num = num >> 1;
			if((num & 1) != 0){
				oddCount++;
			}
			num = num >> 1;
		}
		return isDivisible(Math.abs(eventCount - oddCount));
	}
	
	public static void main(String[] args) {
		DivideBy3 divideBy3 = new DivideBy3();
		System.out.println(divideBy3.isDivisible(1221211221));
	}
}
