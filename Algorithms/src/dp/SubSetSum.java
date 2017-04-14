package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubSetSum {
	
	private static boolean isSumPresent(int[] arr, int sum, int index) {
		if(sum == 0){
			return true;
		}
		if(index < 0 && sum != 0){
			return false;
		}
		boolean isSumPresent = isSumPresent(arr, sum - arr[index], index - 1) || isSumPresent(arr, sum, index - 1);
		return isSumPresent;
	}
	
	public static boolean subsetSum(int[] arr, int sum){
		return isSumPresent(arr, sum, arr.length - 1);
	}
	
	public static boolean subsetSumDP(int[] arr, int sum){
		boolean[][] isSumPresent = new boolean[sum + 1][arr.length];
		for (int cur_sum = 0; cur_sum <= sum; cur_sum++) {
			for (int i = 0; i < arr.length; i++) {
				if(cur_sum == 0 || cur_sum == arr[i]) {
					isSumPresent[cur_sum][i] = true;
				}else if(i > 0){
					boolean sum_includes_index_i = false, sum_excludes_index_i = false;
					if(cur_sum - arr[i] > 0) {
						sum_includes_index_i = isSumPresent[cur_sum - arr[i]][i - 1];
					}
					if(!sum_includes_index_i) {
						sum_excludes_index_i = isSumPresent[cur_sum][i - 1];
					}
					isSumPresent[cur_sum][i] = sum_includes_index_i || sum_excludes_index_i;
				}
			}
		}
		return isSumPresent[sum][arr.length - 1];
	}
	
	public static void main(String[] args) {
		int totalElements = 20;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int i = 0; i < totalElements; i++) {
			arr.add(i);
		}
		Collections.shuffle(arr);
		ArrayList<Integer> sumList = new ArrayList<Integer>();
		for (int i = 0; i < totalElements - 4; i++) {
			sumList.add(arr.get(i));
		}
		Collections.shuffle(sumList);
		int sum = 0;
		for (int i = 0; i < sumList.size(); i++) {
			sum += sumList.get(i);
		}
		System.out.println("Arr : " + Arrays.toString(arr.toArray()) + " \nSum : " + sum);
		int[] inputArr = new int[totalElements];
		for (int i = 0; i < totalElements; i++) {
			inputArr[i] = arr.get(i);
		}
		System.out.println("Recursive - Sum Exists : " + subsetSum(inputArr, sum));
		System.out.println("DP - Sum Exists : " + subsetSumDP(inputArr, sum));
	}
}
