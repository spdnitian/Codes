package misc;

import java.util.Arrays;

import searchNsort.CommonUtilities;

public class UnionIntersection {
	public int[] union(int[] arr1, int[] arr2) {
		int arr1_length = arr1.length,arr2_length = arr2.length; 
		int length_new_arr = arr1_length + arr2_length;
		int[] arr = new int[length_new_arr];
		int arr_index = 0, i = 0, j = 0;
		for (; (i < arr1_length) && (j < arr2_length);) {
			if(arr1[i] < arr2[j]){
				arr[arr_index] = arr1[i];
				i++;
			}else if(arr1[i] > arr2[j]) {
				arr[arr_index] = arr2[j];
				j++;
			}else {
				arr[arr_index] = arr1[i]; //both are equal
				i++;
				j++;
			}
			arr_index++;
		}
		if(i < arr1_length){
			while (i< arr1_length) {
				arr[arr_index++] = arr1[i];
				i++;
			}
		}
		if(j < arr2_length){
			while (j< arr2_length) {
				arr[arr_index++] = arr2[j];
				j++;
			}
		}
		return Arrays.copyOfRange(arr, 0, arr_index);
	}
	
	public int[] intersection(int[] arr1, int[] arr2) {
		int arr1_length = arr1.length,arr2_length = arr2.length; 
		int length_new_arr = arr1_length + arr2_length;
		int[] arr = new int[length_new_arr];
		int arr_index = 0, i = 0, j = 0;
		for (; (i < arr1_length) && (j < arr2_length);) {
			if(arr1[i] < arr2[j]){
				i++;
			}else if(arr1[i] > arr2[j]) {
				j++;
			}else {
				arr[arr_index] = arr1[i]; //both are equal
				i++;
				j++;
				arr_index++;
			}
		}
		return Arrays.copyOfRange(arr, 0, arr_index);
	}
	
	public static void main(String[] args) {
		int[] arr1 = CommonUtilities.getRandomIntArr(10);
		int[] arr2 = CommonUtilities.getRandomIntArr(10);
		UnionIntersection ui = new UnionIntersection();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		int[] arr = ui.union(arr1, arr2);
		int[] arr_i = ui.intersection(arr1, arr2);
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(arr_i));
	}
}
