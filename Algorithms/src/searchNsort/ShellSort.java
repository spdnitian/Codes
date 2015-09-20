package searchNsort;

import java.util.Arrays;

public class ShellSort {
	public <E> void shellSort(E arr){
		
	}
	
	private void insertionSort(int[] arr, int startPos, int dist){
		int length = arr.length;
		for(int i = startPos; i < length; i+=dist){
			int j = i - dist;
			int pos = i;
			while (j >= startPos) {
				if(arr[pos] < arr[j]){
					int temp = arr[pos];
					arr[pos] = arr[j];
					arr[j] = temp;
					pos = j;
				}else {
					break;
				}
				j-= dist;
			}
		}
	}
	public void shellSort(int[] arr, int h){
		while(h >= 1){
			for (int i = 0; i < h; i++) {
				insertionSort(arr, i, h);
			}
			h -= 2;
		}
	}
	
	public static void main(String[] args) {
		ShellSort sort = new ShellSort();
		int[] arr = CommonUtilities.getRandomIntArr(10)/*{0, 3, 1, 4, 3, 5, 6, 2, 8, 0}*/;
		System.out.println("init :"+Arrays.toString(arr));
		int dist = 5;
		sort.shellSort(arr, dist );
		System.out.println("final :"+Arrays.toString(arr));
	}
}
