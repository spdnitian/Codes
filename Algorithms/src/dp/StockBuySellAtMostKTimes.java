package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import utils.CommonUtilities;
import utils.InputParser;

public class StockBuySellAtMostKTimes {
	
	public static int getMaxProfitBySellingAtMostKTimes(Integer[] stockValues, int k) {
		return getMaxProfitBySellingAtMostKTimesUtil(stockValues, 0, k);
	}

	private static int getMaxProfitBySellingAtMostKTimesUtil(Integer[] stockValues, int startIndex, int k) {
		if(k == 0 || startIndex == stockValues.length) {
			return 0;
		}
		int max = 0;
		int minValue = stockValues[startIndex];
		int profitWithOneTxn = 0;
		for(int i = startIndex + 1; i < stockValues.length; i++) {
			profitWithOneTxn = CommonUtilities.getMax(profitWithOneTxn, getProfit(minValue, stockValues[i]));
			int profitWithNextMaxKminus1Txns = getMaxProfitBySellingAtMostKTimesUtil(stockValues, i + 1, k - 1);
			max = CommonUtilities.getMax(max, profitWithOneTxn + profitWithNextMaxKminus1Txns);
			minValue = CommonUtilities.getMin(minValue, stockValues[i]);
		}
		return max;
	}
	
	// TIme Complexity : O(n*n*k)
	private static int getMaxProfitBySellingAtMostKTimesDP(Integer[] stockValues, final int k) {
		int[][] dp = new int[stockValues.length + 1][k + 1];
		for(int index = stockValues.length; index >= 0; index--) {
			for(int k1 = 0; k1 <= k; k1++) {
				if(k1 == 0 || index == stockValues.length) {
					dp[index][k1] = 0;
				}else {
					int max = 0;
					int profitWithOneTxn = 0;
					int minValue = stockValues[index];
					for(int i = index + 1; i < stockValues.length; i++) {
						profitWithOneTxn = CommonUtilities.getMax(profitWithOneTxn, getProfit(minValue, stockValues[i]));
						int profitWithNextMaxKminus1Txns = dp[i + 1][k1 - 1];
						max = CommonUtilities.getMax(max, profitWithOneTxn + profitWithNextMaxKminus1Txns);
						minValue = CommonUtilities.getMin(minValue, stockValues[i]);
					}
					dp[index][k1] = max;
				}
			}
		}
		return dp[0][k];
	}

	public static int getProfitWithOneTxn(Integer[] stockValues, int startIndex, int endIndex) {
		int min = stockValues[startIndex];
		int maxProfit = 0;
		for (int i = startIndex + 1; i <= endIndex; i++) {
			int profit = getProfit(min, stockValues[i]);
			maxProfit = CommonUtilities.getMax(maxProfit, profit);
			min = CommonUtilities.getMin(min, stockValues[i]);
		}
		return maxProfit;
	}

	private static int getProfit(int left, int right) {
		if(right < left) {
			return 0;
		}
		return right - left;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter stocks values separated by comma : ");
		Integer[] stockValues = InputParser.readIntegerInputWithSeparator(bufferedReader.readLine(), ",");
		System.out.println("Enter k : ");
		Integer k = InputParser.readIntegerInputWithSeparator(bufferedReader.readLine(), "")[0];
		System.out.println("Max profit by selling " + k + " times : " + getMaxProfitBySellingAtMostKTimes(stockValues, k));
		System.out.println("Max profit by selling " + k + " times (DP): " + getMaxProfitBySellingAtMostKTimesDP(stockValues, k));
	}
}
