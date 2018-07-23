package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import utils.CommonUtilities;
import utils.InputParser;

public class StockBuySellMaxTwice {
	
	public static int getMaxProfitBySellingTwice(Integer[] stockValues) {
		int[] rightGreatestValue = new int[stockValues.length];
		if(stockValues.length < 2) {
			throw new RuntimeException("Array must be of size greater than equal to 2");
		}
		rightGreatestValue[stockValues.length - 1] = Integer.MIN_VALUE;
		int maxValue = stockValues[stockValues.length - 1];
		for(int i = stockValues.length - 2; i >= 0 ; i--) {
			rightGreatestValue[i] = maxValue;
			if(stockValues[i] > maxValue) {
				maxValue = stockValues[i];
			}
		}
		int minValue = stockValues[0];
		int currentMaxProfit = Integer.MIN_VALUE;
		int totalMaxProfit = Integer.MIN_VALUE;
		for(int i = 0; i < stockValues.length - 1; i++) {
			int currentProfit = getProfit(minValue, stockValues[i]);
			currentMaxProfit = CommonUtilities.getMax(currentMaxProfit, currentProfit);
			totalMaxProfit = CommonUtilities.getMax(totalMaxProfit,currentMaxProfit + getProfit(stockValues[i+1], rightGreatestValue[i+1]));
		}
		return totalMaxProfit;
	}

	private static int getProfit(int left, int right) {
		if(right < left) {
			return 0;
		}
		return right - left;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Enter stocks values separated by comma : ");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Integer[] stockValues = InputParser.readIntegerInputWithSeparator(bufferedReader.readLine(), ",");
		System.out.println("Max profit by selling twice : " + getMaxProfitBySellingTwice(stockValues));
	}

}
