package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {
	int max_lis;
	List<Integer> lis_list = new ArrayList<Integer>();
	int[] lisOf;
	public int lis(int[] arr, int end){
		int lis = 1, max_lis = 0;
		List<Integer> lis_list = new ArrayList<Integer>();
		if(end == 0){
			return 1;
		}
		for(int i = 0 ; i < end; i++){
			lis = lis(arr, i);
			if(lis > max_lis){
				this.max_lis = lis;
				lis_list.add(arr[i]);
				if(arr[end] > arr[i]){
					max_lis = lis;
				}
			}
		}
		max_lis++;
		lis_list.add(arr[end]);
		if(max_lis > this.max_lis){
			this.max_lis = max_lis;
			this.lis_list = lis_list;
		}
		return max_lis;
	}
	
	public int lis_dp_memorization(int[] arr, int end){
		int lis = 1, max_lis = 0;
		List<Integer> lis_list = new ArrayList<Integer>();
		if(end == 0){
			return 1;
		}
		for(int i = 0 ; i < end; i++){
			if(this.lisOf[i] != -1){
				lis = this.lisOf[i];
			}else {
				lis = lis(arr, i);
				this.lisOf[i] = lis;
			}
			if(lis > max_lis){
				this.max_lis = lis;
				lis_list.add(arr[i]);
				if(arr[end] > arr[i]){
					max_lis = lis;
				}
			}
		}
		max_lis++;
		lis_list.add(arr[end]);
		if(max_lis > this.max_lis){
			this.max_lis = max_lis;
			this.lis_list = lis_list;
		}
		return max_lis;
	}
	
	public int lis_dp_tabulation(int[] arr, int end){
		int max_lis = 0, overall_max = 0, end_index = 0;
		for(int i = 0; i <= end; i++){
			for(int j = 0; j < i; j++){
				if(arr[i] > arr[j] && this.lisOf[j] > max_lis){
					max_lis = this.lisOf[j];
				}
			}
			this.lisOf[i] = 1 + max_lis;
			max_lis = 0;
			if(overall_max < this.lisOf[i]){
				end_index = i;
				overall_max = this.lisOf[i];
			}
		}
		int max = overall_max;
		int[] lis_arr = new int[max];
		List<Integer> lis_list = new ArrayList<Integer>();
		for(int j = end_index; j >= 0; j--){
			if(max == this.lisOf[j]){
				lis_arr[max - 1] = arr[j];
				max--;
			}
		}
		for(int j = 0; j < overall_max; j++){
			lis_list.add(lis_arr[j]);
		}
		this.lis_list = lis_list;
		return overall_max;
	}
	
	public static void main(String[] args) {
		LIS lis = new LIS();
		int[] arr = /*{ 10, 22, 9, 33, 21, 50, 41, 60 }*/{13,12,13,1,15,1,14,3,16,17,19,22,33,100};
		lis.lisOf = new int[arr.length];
		Arrays.fill(lis.lisOf, -1);
		int lis_dp = lis.lis_dp_tabulation(arr, arr.length - 1);
		System.out.println(lis_dp);
		System.out.println(lis.lis_list);
	}
}
