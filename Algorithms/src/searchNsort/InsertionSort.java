package searchNsort;

import java.util.Arrays;

public class InsertionSort {
	
	private void insert(int[] arr,int start,int end,int insertingIndex) {
		while (end >=0 && arr[insertingIndex] < arr[end]) {
			int temp = arr[insertingIndex];
			arr[insertingIndex] = arr[end];
			arr[end] = temp;
			end --;insertingIndex--;//insert in-between array values
		}
	}
	
	public void insertionSort(int[] arr) {
		int length = arr.length;
		for (int i = 1; i < length; i++) {
			insert(arr, 0, i-1, i);
		}
	}
	
	public static void main(String[] args) {
		InsertionSort insertionSort = new InsertionSort();
		int[] arr = CommonUtilities.getRandomIntArr(30);
		System.out.println(Arrays.toString(arr));
		insertionSort.insertionSort(arr);
		System.out.println(Arrays.toString(arr));

	}

}
