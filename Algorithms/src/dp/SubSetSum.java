package dp;

public class SubSetSum {
	
	private static boolean isSumPresent(int[] arr, int sum, int index) {
		if(sum == 0){
			return true;
		}
		if(sum < 0 || index < 0){
			return false;
		}
		boolean isSumPresent = isSumPresent(arr, sum - arr[index], index - 1) || isSumPresent(arr, sum, index - 1);
		return isSumPresent;
	}
	
	public static boolean subsetSum(int[] arr, int sum){
		return isSumPresent(arr, sum, arr.length - 1);
	}
	
	public static void main(String[] args) {
		int set[] = {3, 34, 4, 12, 5, 2};
		System.out.println(subsetSum(set, 56));
	}
}
