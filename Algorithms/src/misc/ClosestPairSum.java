package misc;

import java.util.Arrays;


public class ClosestPairSum {
	public int[] getClosetPair(int[] arr, int num){
		int[] pair = new int[2];
		int length = arr.length;
		int l = 0,r = length - 1;
		int diffAbs = 0,minDiffAbs = num + 1,diff = 0;
		while(l < r){
			diff = arr[l] + arr[r] - num;
			diffAbs = Math.abs(diff);
			if(diffAbs < minDiffAbs){
				pair[0] = arr[l];
				pair[1] = arr[r];
				minDiffAbs = diffAbs;
			}
			if(diff > 0){
				r--;
			}else {
				l++;
			}
		}
		return pair;
	}
	
	public int[] getClosetPair(int[] arr1,int[] arr2, int num){
		int[] pair = new int[2];
		int length1 = arr1.length;
		int length2 = arr2.length;
		int l = 0,r = length2 - 1;
		int diffAbs = 0,minDiffAbs = num + 1,diff = 0;
		while(l <= length1-1 && r>=0){
			diff = arr1[l] + arr2[r] - num;
			diffAbs = Math.abs(diff);
			if(diffAbs < minDiffAbs){
				pair[0] = arr1[l];
				pair[1] = arr2[r];
				minDiffAbs = diffAbs;
			}
			if(diff > 0){
				r--;
			}else {
				l++;
			}
		}
		return pair;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 4, 5, 7, 10, 20, 30, 40};
		int[] arr1 = {1, 4, 5, 7};
        int[] arr2 = {10, 20, 30, 40};
		ClosestPairSum closestPairSum = new ClosestPairSum();
		int[] pair = closestPairSum.getClosetPair(arr, 32);
		int[] pair2 = closestPairSum.getClosetPair(arr1, arr2, 32);
		System.out.println(Arrays.toString(pair));
		System.out.println(Arrays.toString(pair2));
		
	}
}
