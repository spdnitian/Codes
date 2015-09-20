package greedy;

import java.util.Arrays;

public class JobSequencing {
	private int getMaxDeadLine(int[][] jobs){
		int max = 0;
		for (int i = 0; i < jobs.length; i++) {
			if(jobs[i][0] >= max){
				max = jobs[i][0];
			}
		}
		return max;
	}
	
	private void sortJobsOnProfit(int[][] arr){
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			for (int j = 1; j < length - i; j++) {
				if(arr[j][1] > arr[j-1][1]){ // swap adjacent values
					int[] temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
	}
	
	public int[] getJobSequence(int[][] jobs){
		int size = getMaxDeadLine(jobs);
		int[] jobSequences = new int[size+1];
		sortJobsOnProfit(jobs);
		for(int i = 0; i < jobs.length; i++){
			int deadline = jobs[i][0];
			for(int j = deadline; j > 0; j--){
				if(jobSequences[j] == 0){
					jobSequences[j] = jobs[i][2];
					break;
				}
			}
		}
		return jobSequences;
	}
	
	public static void main(String[] args) {
		int[][] jobs = { {2, 100, 1}, {1, 19, 2}, {2, 27, 3},
                {1, 25, 4}, {3, 15, 5}};
		JobSequencing jobSequencing = new JobSequencing();
		int[] js = jobSequencing.getJobSequence(jobs);
		System.out.println(Arrays.toString(js));
	}
}
