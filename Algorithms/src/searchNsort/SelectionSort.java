package searchNsort;

import java.util.Arrays;

public class SelectionSort {
	public void selectionSort(int[] arr) {
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			int minValIndex = i;
			for (int j = i+1; j < length; j++) {
				if(arr[j] <= arr[minValIndex]){
					minValIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minValIndex];
			arr[minValIndex] = temp; // find the minimum values and swap to the corresponding index
		}
	}
	
	public static void main(String[] args) {
		int[] arr = CommonUtilities.getRandomIntArr(20);
		System.out.println(Arrays.toString(arr));
		SelectionSort selectionSort = new SelectionSort();
		selectionSort.selectionSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
