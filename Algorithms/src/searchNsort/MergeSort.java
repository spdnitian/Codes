package searchNsort;

import java.util.Arrays;

public class MergeSort {
	
	public static void merge(int[] array, int left, int middle, int right) {
		int arr1_length = middle - left + 1, arr2_length = right - middle;
		int arr_length = right - left + 1;
		int[] arr = new int[arr_length];
		int arr_index = 0, i = left, j = middle + 1;
		for (; (i <= left + arr1_length -1 ) && (j <= middle + arr2_length);) {
			if(array[i]< array[j]){
				arr[arr_index] = array[i];
				i++;
			}else {
				arr[arr_index] = array[j];
				j++;
			}
			arr_index++;
		}
		if(i < left + arr1_length){
			while (i < left + arr1_length) {
				arr[arr_index++] = array[i];
				i++;
			}
		}
		if(j < middle + arr2_length + 1){
			while (j < middle + arr2_length + 1) {
				arr[arr_index++] = array[j];
				j++;
			}
		}
		for(i = 0 ; i < arr_length ; i++){
			array[i+left] = arr[i];
		}
	}
	
	public void mergeSort(int[] arr, int left, int right){
		if(left < right){
			int middle = (left + right)/2;
			mergeSort(arr, left, middle);
			mergeSort(arr, middle+1, right);
			merge(arr, left, middle, right);
		}
	}

	public static void main(String[] args) {
		int[] arr1 = CommonUtilities.getRandomIntArr(10);
		/*int[] arr2 = CommonUtilities.getRandomIntArr(11);
		ShellSort shellSort = new ShellSort();
		shellSort.shellSort(arr1, 5);
		shellSort.shellSort(arr2, 5);
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		int[] arr = CommonUtilities.getMergedArray(arr1, arr2);
		System.out.println(Arrays.toString(arr));*/
		MergeSort mergeSort = new MergeSort();
		System.out.println(Arrays.toString(arr1));
		mergeSort.mergeSort(arr1, 0, arr1.length - 1);
		System.out.println(Arrays.toString(arr1));
	}

}
