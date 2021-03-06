package searchNsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CommonUtilities {
	public static boolean isOdd(int num) {
		return ((num & 1) == 1 ? true : false);
	}
	public static int findMinValueIndex(int value){
			return 0;
	}
	
	public static int[] getRandomIntArr(int length) {
		int[] arr = new int[length];
	    for (int idx = 1; idx < length-1; ++idx){
	      int randomInt = getRandomInt();
	      arr[idx] = randomInt % length;
	    }
	    return arr;
	}
	
	public static int[] getUniqueRandomArr(int length) {
		ArrayList<Integer> a = new ArrayList<>(length);
		for (int i = 0; i < length; i++){
		    a.add(i);
		}
		Collections.shuffle(a);
		int a_length = a.size();
		int[] arr = new int[a_length];
		for (int i = 0; i < length; i++) {
			arr[i] = a.get(i);
		}
		return arr;
	}
	
	public static void swapInArr(int[] arr, int i, int j) {
		int num = arr[i];
		arr[i] = arr[j];
		arr[j] = num;
	}
	
	public static int[] getArrOfRandom01(int length) {
		ArrayList<Integer> a = new ArrayList<>(length);
		for (int i = 0; i < length; i++){
		    a.add(i);
		}
		Collections.shuffle(a);
		int a_length = a.size();
		int[] arr = new int[a_length];
		for (int i = 0; i < length; i++) {
			arr[i] = a.get(i) & 1;
		}
		return arr;
	}
	
	public static int getRandomInt() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(100);
	}
	
	public static int[] getMergedArray(int[] arr1, int[] arr2) {
		int arr1_length = arr1.length,arr2_length = arr2.length; 
		int length_new_arr = arr1_length + arr2_length;
		int[] arr = new int[length_new_arr];
		int arr_index = 0, i = 0, j = 0;
		for (; (i < arr1_length) && (j < arr2_length);) {
			if(arr1[i]< arr2[j]){
				arr[arr_index] = arr1[i];
				i++;
			}else {
				arr[arr_index] = arr2[j];
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
		return arr;
	}
	
	public static int getMax(int...vals) {
		int max = Integer.MIN_VALUE;
		for (int i : vals) {
			if(i > max){
				max = i;
			}
		}
		return max;
	}
	
	public static int getMin(int...vals) {
		int min = Integer.MAX_VALUE;
		for (int i : vals) {
			if(i < min){
				min = i;
			}
		}
		return min;
	}
}
