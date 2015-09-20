package dp;

import searchNsort.CommonUtilities;

public class LargestSumContigousSubArray {
	public int getLargestSumContigous(int[] arr){
		int largestSum = arr[0], currentSum = arr[0];
		for(int i = 1; i < arr.length; i++){
			currentSum = CommonUtilities.getMax(arr[i], arr[i] + currentSum);
			largestSum = CommonUtilities.getMax(currentSum, largestSum);
		}
		return largestSum;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 2, -1, 10, 5};
		LargestSumContigousSubArray largestSumContigousSubArray = new LargestSumContigousSubArray();
		int ls = largestSumContigousSubArray.getLargestSumContigous(arr);
		System.out.print(ls);
	}
}
