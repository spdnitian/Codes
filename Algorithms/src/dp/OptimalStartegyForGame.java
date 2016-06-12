package dp;

import searchNsort.CommonUtilities;

public class OptimalStartegyForGame {
	public static int getOptimalScore(int[] scores){
		return getOptimalScoreUtil(scores,0, scores.length - 1, 'A' , 0);
	}
	private static int getOptimalScoreUtil(int[] scores, int i, int j, char currentPlayer, int total) {
		// currentPlayer either can take i or j, then the next player can take i +1 or j - 1
		//Let's say the player takes i
		if(total < (scores.length/2) && i < j){
			if(currentPlayer == 'A'){
				return CommonUtilities.getMax(scores[i] + getOptimalScoreUtil(scores, i + 1, j, 'B', total + 1),
						scores[j] + getOptimalScoreUtil(scores, i, j - 1, 'B', total + 1));
			}else if(currentPlayer == 'B'){
				return CommonUtilities.getMax(getOptimalScoreUtil(scores, i + 1, j, 'A', total),
						getOptimalScoreUtil(scores, i, j - 1, 'A', total));
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		 int arr[] = {8, 15, 3, 7};
		 System.out.println(getOptimalScore(arr));
	}
}
