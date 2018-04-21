package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class StockSpan {
	
	public static int[] getStockSpans(int[] stocks) {
		int[] stockSpans = new int[stocks.length];
		for(int i = 0; i < stocks.length; i++) {
			int span = 1;
			while(i >= span && stocks[i - span] <= stocks[i]) {
				span += stockSpans[i - span];
			}
			stockSpans[i] = span;
		}
		return stockSpans;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter stocks values separated by comma : ");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String[] input = bufferedReader.readLine().split(",");
		int i = 0;
		int[] stockValues = new int[input.length];
		for(String stocksStr : input) {
			stockValues[i++] = Integer.parseInt(stocksStr.trim());
		}
		System.out.println("Stock span : " + Arrays.toString(getStockSpans(stockValues)));
	}
}
