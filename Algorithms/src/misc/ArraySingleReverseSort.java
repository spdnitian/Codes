package misc;

import java.util.Arrays;
import java.util.Scanner;

public class ArraySingleReverseSort {

	public static boolean canBeSortedWithSingleSubArrReverse(int[] arr) {
		if(arr.length <= 2) {
			return true;
		}
		int[] arrCopy = Arrays.copyOf(arr, arr.length);
		int initialLocalMinIndex = getLocalMinIndexFrom(arr, 0);
		if(initialLocalMinIndex == arr.length - 1) {
			return true;
		}
		int afterLocalMaxIndex = getLocalMaxIndexFrom(arr, initialLocalMinIndex + 1);
		int swapFrom =-1;
		int swapTo = -1;
		boolean isDecreasingAfterMaxElemIndex = isDecreasingFrom(arr, afterLocalMaxIndex);
		if(isDecreasingAfterMaxElemIndex) {
			int prevLargerElemenMintIndex = getPrevLargerElementIndex(arr, initialLocalMinIndex);
			if(prevLargerElemenMintIndex >= 0) {
				if(arr[prevLargerElemenMintIndex] >= arr[afterLocalMaxIndex]) {
					swapFrom = prevLargerElemenMintIndex + 1;
					swapTo = getPrevEqualElementIndex(arr, afterLocalMaxIndex);
					System.out.println("Min-Max : Can be sorted by reversing  from " + swapFrom + " to " + swapTo);
					reverse(arrCopy, swapFrom, swapTo);
					System.out.println("Sorted Array : " + Arrays.toString(arrCopy));
					return true;
				}
			}else {
				int prevSmallerElementMaxIndex = getPrevSmallerElementIndex(arr, afterLocalMaxIndex);
				if(arr[prevSmallerElementMaxIndex] <= arr[arr.length - 1]) {
					swapFrom = prevSmallerElementMaxIndex + 1;
					swapTo = arr.length - 1;
					System.out.println("Min-Max : Can be sorted by reversing  from " + swapFrom + " to " + swapTo);
					reverse(arrCopy, swapFrom, swapTo);
					System.out.println("Sorted Array : " + Arrays.toString(arrCopy));
					return true;
				}else if(arr[0] >= arr[afterLocalMaxIndex + 1]) {
					swapFrom = 0;
					swapTo = afterLocalMaxIndex;
					System.out.println("Min-Max : Can be sorted by reversing  from " + swapFrom + " to " + swapTo);
					reverse(arrCopy, swapFrom, swapTo);
					System.out.println("Sorted Array : " + Arrays.toString(arrCopy));
					return true;
				} 
			}
			
			int initialLocalMaxIndex = getLocalMaxIndexFrom(arr, 0);
			if(initialLocalMaxIndex == arr.length - 1) {
				return true;
			}
			int afterLocalMinIndex = getLocalMinIndexFrom(arr, initialLocalMaxIndex + 1);
			boolean isIncreasingAfterMinElemIndex = isIncreasingFrom(arr, afterLocalMinIndex);
			if(isIncreasingAfterMinElemIndex) {
				int prevSmallerElementMaxIndex = getPrevSmallerElementIndex(arr, initialLocalMaxIndex);
				if(prevSmallerElementMaxIndex >= 0) {
					if(arr[prevSmallerElementMaxIndex] <= arr[afterLocalMinIndex]) {
						swapFrom = prevSmallerElementMaxIndex + 1;
						swapTo = getPrevEqualElementIndex(arr, afterLocalMinIndex);
						System.out.println("Max-Min : Can be sorted by reversing  from " + swapFrom + " to " + swapTo);
						reverse(arrCopy, swapFrom, swapTo);
						System.out.println("Sorted Array : " + Arrays.toString(arrCopy));
						return true;
					}
				}else {
					int prevLargerElementMinIndex = getPrevLargerElementIndex(arr, afterLocalMinIndex);
					if(arr[prevLargerElementMinIndex] >= arr[arr.length - 1]) {
						swapFrom = prevLargerElementMinIndex + 1;
						swapTo = arr.length - 1;
						System.out.println("Max-Min : Can be sorted by reversing  from " + swapFrom + " to " + swapTo);
						reverse(arrCopy, swapFrom, swapTo);
						System.out.println("Sorted Array : " + Arrays.toString(arrCopy));
						return true;
					}else if(arr[0] <= arr[afterLocalMinIndex + 1]) {
						swapFrom = 0;
						swapTo = afterLocalMinIndex;
						System.out.println("Max-Min : Can be sorted by reversing  from " + swapFrom + " to " + swapTo);
						reverse(arrCopy, swapFrom, swapTo);
						System.out.println("Sorted Array : " + Arrays.toString(arrCopy));
						return true;
					} 
				}
			}
		}
		return false;
	}

	private static boolean isIncreasingFrom(int[] arr, int fromIndex) {
		int i = fromIndex;
		while(i < arr.length - 1) {
			if(arr[i] > arr[i + 1]) {
				return false;
			}
			i++;
		}
		return true;
	}

	private static boolean isDecreasingFrom(int[] arr, int fromIndex) {
		int i = fromIndex;
		while(i < arr.length - 1) {
			if(arr[i] < arr[i + 1]) {
				return false;
			}
			i++;
		}
		return true;
	}

	private static int getPrevEqualElementIndex(int[] arr, int indexOfElement) {
		int i = indexOfElement;
		while(i >= 0) {
			if(arr[i] == arr[i - 1]) {
				i--;
			}else {
				return i;
			}
		}
		return -1;
	}

	private static void reverse(int[] arr, int begin, int end) {
		if(begin < end) {
			int temp = arr[begin];
			arr[begin] = arr[end];
			arr[end] = temp;
			reverse(arr, begin + 1, end - 1);
		}
	}
	
	private static int getPrevSmallerElementIndex(int[] arr, int indexOfElement) {
		int i = indexOfElement - 1;
		while(i >= 0) {
			if(arr[i] < arr[indexOfElement]) {
				return i;
			}
			i--;
		}
		return -1;
	}

	private static int getPrevLargerElementIndex(int[] arr, int indexOfElement) {
		int i = indexOfElement - 1;
		while(i >= 0) {
			if(arr[i] > arr[indexOfElement]) {
				return i;
			}
			i--;
		}
		return -1;
	}

	private static int getLocalMaxIndexFrom(int[] arr, int fromIndex) {
		int maxIndex = fromIndex;
		int i = maxIndex;
		while(i < arr.length - 1) {
			if(arr[i + 1] >= arr[i]) {
				i++;
				maxIndex = i;
			}else {
				break;
			}
		}
		return maxIndex;
	}

	private static int getLocalMinIndexFrom(int[] arr, int fromIndex) {
		int minIndex = fromIndex;
		int i = minIndex;
		while(i < arr.length - 1) {
			if(arr[i + 1] <= arr[i]) {
				i++;
				minIndex = i;
			}else {
				break;
			}
		}
		return minIndex;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter array : ");
		String inputLine = scanner.nextLine();
		scanner.close();
		String[] inputStrArr = inputLine.split(",");
		int[] input = new int[inputStrArr.length];
		int i = 0;
		for(String string : inputStrArr) {
			input[i++] = Integer.parseInt(string);
		}
		System.out.println("Can be sorted with one reversal : " + canBeSortedWithSingleSubArrReverse(input));
	}

}
