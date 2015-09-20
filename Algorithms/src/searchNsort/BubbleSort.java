package searchNsort;

import java.util.Arrays;

public class BubbleSort {
	
	public void bubbleSort(int[] arr) {
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			for (int j = 1; j < length - i; j++) {
				if(arr[j] < arr[j-1]){ // swap adjacent values
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		BubbleSort bubbleSort = new BubbleSort();
		int[] arr = CommonUtilities.getRandomIntArr(20);
		System.out.println(Arrays.toString(arr));
		bubbleSort.bubbleSort(arr);
		System.out.println(Arrays.toString(arr));

	}

}
