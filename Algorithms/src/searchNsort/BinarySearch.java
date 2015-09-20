package searchNsort;

import java.util.Arrays;
import java.util.Random;

public class BinarySearch {
	
	public static int getRandomInt() {
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(100);
	}
	
	public static int[] getRandomSortedArr(int length) {
		int[] arr = new int[length];
	    for (int idx = 1; idx < length-1; ++idx){
	      int randomInt = getRandomInt();
	      arr[idx] = randomInt % length;
	    }
	    Arrays.sort(arr);
	    return arr;
	}
	
	public int search(int[] arr,int searchItem){
		int index = -1;
		if(arr.length>0){
			int begin = 0, end = arr.length - 1;
			int mid;
			while (begin < end) {
				mid = (begin+end)/2;
				if(searchItem < arr[mid]){
					end = mid - 1;
				}else if (searchItem > arr[mid]) {
					begin = mid + 1;
				}else {
					return mid;
				}
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		int length = 10;
		int[] arr = getRandomSortedArr(length);
		System.out.println(Arrays.toString(arr));
		int searchItem = getRandomInt() % length;
		System.out.println("Search item "+searchItem);
		BinarySearch binarySearch = new BinarySearch();
		int foundIndex = binarySearch.search(arr, searchItem);
		System.out.println("Element "+searchItem+" found at "+foundIndex);
	}
}
