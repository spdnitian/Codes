package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import searchNsort.CommonUtilities;

public class KPainterPartition {
	
	private static int getTimeTakenToPaint(int[] sections, int fromIndex, int toIndex) {
		int totalTime = 0;
		if(fromIndex <= toIndex) {
			for (int i = fromIndex; i <= toIndex; i++) {
				totalTime += sections[i];
			}
		}else {
			for (int i = fromIndex; i >= toIndex; i--) {
				totalTime += sections[i];
			}
		}
		return totalTime;
	}
	
	private static int getMinTimeToPaintUtil(int[] sections, int painters, int fromSectionIndex) {
		if(fromSectionIndex < 0) {
			return 0;
		}
		if(painters == 0) {
			return Integer.MAX_VALUE;
		}
		int minTime = Integer.MAX_VALUE;
		int timeTaken = 0;
		for(int toSectionIndex = fromSectionIndex; toSectionIndex >= 0; toSectionIndex--) {
			/*
			 * Here timeTaken is time taken to paint the sections starting from fromSectionIndex to 0 
			 * where one partition is painted by one painter from fromSectionIndex to toSectionIndex and other partitions are painted by painters - 1 from toSectionIndex - 1 to 0
			 * */
			timeTaken = CommonUtilities.getMax(getTimeTakenToPaint(sections, fromSectionIndex, toSectionIndex), getMinTimeToPaintUtil(sections, painters - 1, toSectionIndex - 1));
			minTime = CommonUtilities.getMin(minTime, timeTaken);
		}
		return minTime;
	}

	public static int getMinTimeToPaint(int[] sections, int painters) {
		return getMinTimeToPaintUtil(sections, painters, sections.length - 1);
	}
	
	public static int getMinTimeToPaintDP(int[] sections, int painters) {
		int[][] dp = new int[sections.length + 1][painters + 1];
		for(int i = 1; i <= sections.length; i++) {
			dp[i][0] = Integer.MAX_VALUE;
		}
		for(int dpFromSectionIndex = 1; dpFromSectionIndex <= sections.length; dpFromSectionIndex++) {
			for(int dpPainters = 1; dpPainters <= painters; dpPainters++) {
				int minTime = Integer.MAX_VALUE;
				int timeTaken = 0;
				for(int dpToSectionIndex = dpFromSectionIndex; dpToSectionIndex > 0; dpToSectionIndex--) {
					/*
					 * Here timeTaken is time taken to paint the sections starting from fromSectionIndex to 0 
					 * where one partition is painted by one painter from fromSectionIndex to toSectionIndex and other partitions are painted by dpPainters - 1 from toSectionIndex - 1 to 0 .
					 * */
					int fromSectionIndex = dpFromSectionIndex - 1;
					int toSectionIndex = dpToSectionIndex - 1;
					timeTaken = CommonUtilities.getMax(getTimeTakenToPaint(sections, fromSectionIndex, toSectionIndex), dp[dpToSectionIndex - 1][dpPainters - 1]);
					minTime = CommonUtilities.getMin(minTime, timeTaken);
				}
				dp[dpFromSectionIndex][dpPainters] = minTime;
			}
		}
		return dp[sections.length][painters];
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter sections : ");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String[] inputLine = bReader.readLine().split(",");
		int[] input = new int[inputLine.length];
		for(int i = 0; i < inputLine.length; i++) {
			input[i] = Integer.parseInt(inputLine[i].trim());
		}
		System.out.println("Enter total Painters : ");
		int painters = Integer.parseInt(bReader.readLine().trim());
		System.out.println("Min Time to paint : ");
		System.out.println(getMinTimeToPaint(input, painters));
		System.out.println("Min Time to paint(dp) : ");
		System.out.println(getMinTimeToPaintDP(input, painters));
		
	}
}
