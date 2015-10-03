package misc;

import java.util.Arrays;

import searchNsort.CommonUtilities;

public class Seg01 {
	public static void seg01(int[] arr) {
		int prev0 = -1, cur0 = -1, arr_length = arr.length;
		for(int i = 0; i < arr_length ; i++){
			if(arr[i] == 0){
				prev0 = cur0;
				cur0 = i;
				if(cur0 - prev0 > 1){
					CommonUtilities.swapInArr(arr, ++prev0, cur0);
					cur0 = prev0;
				}
			}
		}
	}
	
	private static int getNextLast2FrtomIndex(int[] arr, int index){
		int last2 = index;
		while (last2 >= 0) {
			if(arr[last2 - 1] != 2){
				break;
			}
			last2--;
		}
		return last2;
	}
	
	public static void seg012(int[] arr) {
		int prev0 = -1, cur0 = -1, arr_length = arr.length, last2 = arr_length;
		for(int i = 0; i < last2 ; i++){
			last2 = getNextLast2FrtomIndex(arr, last2);
			if(arr[i] == 0){
				prev0 = cur0;
				cur0 = i;
				if(cur0 - prev0 > 1){
					CommonUtilities.swapInArr(arr, ++prev0, cur0);
					cur0 = prev0;
				}
			}else if (arr[i] == 2) {
				CommonUtilities.swapInArr(arr, cur0 + 1, i);
				CommonUtilities.swapInArr(arr, cur0 + 1, --last2);
				if(arr[cur0 + 1] == 0){
					cur0++;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int arr_length = 105;
		int[] arr = CommonUtilities.getArrOfRandom01(arr_length);
		System.out.println(Arrays.toString(arr));
		seg01(arr);
		System.out.println(Arrays.toString(arr));
		arr = CommonUtilities.getRandomIntArr(arr_length);
		//arr = new int[]{0, 0, 1, 2, 0, 0, 0, 2, 2, 0, 2, 1, 2, 1, 0};
		for(int i = 0; i < arr_length ; i++){
			arr[i] = arr[i] % 3;
		}
		System.out.println(Arrays.toString(arr));
		seg012(arr);
		System.out.println(Arrays.toString(arr));
	}
}
