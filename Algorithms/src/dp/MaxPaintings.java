package dp;

public class MaxPaintings {
	public static long getMaxNoOfPaintings(int white, int red, int green) {
		long[][][] dp = new long[white + 1][red + 1][green + 1];
		if(white > 0 && red > 0 && green > 0){
			dp[1][1][1] = 1;
			if(white >= 3){
				dp[3][0][0] = 1;
			}
			if(red >= 3){
				dp[0][3][0] = 1;
			}
			if(green >= 3){
				dp[0][0][3] = 1;
			}
			for(int i = 1; i <= white; i++){
				for(int j = 1; j <= red; j++){
					for(int k = 1; k <= green; k++){
						if(i != 1 || j != 1 || k!= 1){
							long max = 0;
							if(i - 3 >= 0){
								max = Math.max(max, dp[i - 3][j][k] + 1);
							}
							if(j - 3 >= 0){
								max = Math.max(max, dp[i][j - 3][k] + 1);
							}
							if(k - 3 >= 0){
								max = Math.max(max, dp[i][j][k - 3] + 1);
							}
							max = Math.max(max, dp[i - 1][j - 1][k - 1] + 1);
							dp[i][j][k] = max;
						}
					}
				}
			}
		}
		return dp[white][red][green];
	}
	
	public static void main(String[] args) {
		System.out.println(getMaxNoOfPaintings(11, 11, 8));
	}
}
