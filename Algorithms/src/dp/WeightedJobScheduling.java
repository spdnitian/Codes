package dp;

import java.util.Arrays;

import dp.timetesting.MeasureTimings;
import dp.timetesting.TimeTesting;
import searchNsort.CommonUtilities;

class Job implements Comparable<Job>{
	int startTime, finishTime, profit;
	public Job(int[] job) {
		startTime = job[0];
		finishTime = job[1];
		profit = job[2];
	}
	
	public static Job[] getJobs(int[]...jobs){
		Job[] allJobs = new Job[jobs.length];
		for (int i = 0; i < jobs.length; i++) {
			allJobs[i] = new Job(jobs[i]);
		}
		return allJobs;
	}

	@Override
	public int compareTo(Job o) {
		if(this.finishTime > o.finishTime){
			return 1;
		}
		if(this.finishTime < o.finishTime){
			return -1;
		}
		return 0;
	}

}

public class WeightedJobScheduling implements TimeTesting{
	private static int getPreviousNonConflictingJobIndex(Job[] jobs,int currentJobIndex) {
		for (int i = currentJobIndex - 1; i >= 0; i--) {
			if(jobs[i].finishTime <= jobs[currentJobIndex].startTime){
				return i;
			}
		}
		return -1;
	}
	private static int getMaxProfitUtil(Job[] jobs, int currentTotalJobs) {
		if(currentTotalJobs == 0){
			return 0; // No jobs are there so total profit is zero
		}
		if(currentTotalJobs == 1){
			return jobs[0].profit; //If single job , then return the only profit
		}
		//Now calculating the profit including this job and excluding this job
		int previousNonConflictingJobIndex = getPreviousNonConflictingJobIndex(jobs, currentTotalJobs - 1);
		int profitIncludingCurrentJob = jobs[currentTotalJobs -1].profit + getMaxProfitUtil(jobs, previousNonConflictingJobIndex + 1);
		int profitExcludingCurrentJob = getMaxProfitUtil(jobs, currentTotalJobs - 1);
		return CommonUtilities.getMax(profitIncludingCurrentJob, profitExcludingCurrentJob);
	}

	public static int getMaxProfit(Job[] jobs) {
		Arrays.sort(jobs);
		return getMaxProfitUtil(jobs, jobs.length);
	}
	
	public static int getMaxProfitDP(Job[] jobs) {
		Arrays.sort(jobs);
		int[] dp = new int[jobs.length];
		dp[0] = jobs[0].profit;
		for (int i = 1; i < dp.length; i++) {
			int profitIncludingCurrentJob = jobs[i].profit;
			int previousNonConflictingJobIndex = getPreviousNonConflictingJobIndex(jobs, i);
			if(previousNonConflictingJobIndex != -1){
				profitIncludingCurrentJob += dp[previousNonConflictingJobIndex];
			}
			int profitExcludingCurrentJob = dp[i - 1];
			dp[i] = CommonUtilities.getMax(profitIncludingCurrentJob, profitExcludingCurrentJob);
		}
		return dp[dp.length - 1];
	}

	public static void main(String[] args) {
		MeasureTimings.meauseRecursiveTimeTaken(new WeightedJobScheduling());
		MeasureTimings.measusreDPTimeTaken(new WeightedJobScheduling());
	}

	@Override
	public void testNormal() {
		Job jobs[] = Job.getJobs(new int[]{3, 10, 20},new int[]{1, 2, 50},new int[]{6, 19, 100},new int[]{2, 100, 200});
		System.out.println("Recursive max profit value : " + getMaxProfit(jobs));
	}

	@Override
	public void testDP() {
		Job jobs[] = Job.getJobs(new int[]{3, 10, 20},new int[]{1, 2, 50},new int[]{6, 19, 100},new int[]{2, 100, 200});
		System.out.println("DP max profit value : " + getMaxProfitDP(jobs));
	}

}
