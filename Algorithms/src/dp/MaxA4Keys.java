package dp;

public class MaxA4Keys {
	public static int getMaxA(int nStroke) {
		if(nStroke <= 0){
			return 0;
		}
		int maxStroke = 0;
		if(nStroke <= 6){
			maxStroke = nStroke;
		}else{
			for(int i = nStroke - 3, multiplier = 2; i >= 1; i--,multiplier++){
				int currentStroke = getMaxA(i) * multiplier;
				if(maxStroke < currentStroke){
					maxStroke = currentStroke;
				}
			}
		}
		return maxStroke;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 15; i++) {
			System.out.println(getMaxA(i));
		}
	}
}
