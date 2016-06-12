package dp;

import java.util.ArrayList;

public class NoOfWaysToReachScore {
	
	int[] validPoints;
	int pointToScore;
	ArrayList<Integer> points;
	public NoOfWaysToReachScore(int[] validPoints, int pointToScore) {
		// TODO Auto-generated constructor stub
		this.validPoints = validPoints;
		this.pointToScore = pointToScore;
		points = new ArrayList<Integer>();
	}

	private int getNoWaysToReachScoreUtil(int currentPos, int currentPoint) {
		if(currentPoint > pointToScore){
			return 0;
		}
		if(currentPoint == pointToScore){
			printPoints();
			return 1;
		}
		int totalWays = 0;
		for (int i = currentPos; i < validPoints.length; i++) {
			points.add(validPoints[i]);
			totalWays += getNoWaysToReachScoreUtil(i, currentPoint + validPoints[i]);
			points.remove(points.size() - 1);
		}
		return totalWays;
	}

	private void printPoints() {
		for (Integer point : points) {
			System.out.print(point + " ");
		}
		System.out.println();
	}
	
	public static int getNoWaysToReachScoreDP(int[] validPoints, int pointToScore) {
		int[] totalWaysToScore = new int[pointToScore + 1];
		totalWaysToScore[0] = 1;
		for(int i = 0; i < validPoints.length; i++){
			for (int point = validPoints[i]; point < totalWaysToScore.length; point++) {
				totalWaysToScore[point] += totalWaysToScore[point - validPoints[i]];
			}
		}
		return totalWaysToScore[pointToScore];
	}

	public static int getNoWaysToReachScore(int[] validPoints, int pointToScore) {
		NoOfWaysToReachScore noOfWaysToReachScore = new NoOfWaysToReachScore(validPoints, pointToScore);
		return noOfWaysToReachScore.getNoWaysToReachScoreUtil(0, 0);
	}
	
	public static void main(String[] args) {
		//System.out.println("No of ways : " + getNoWaysToReachScore(new int[]{3, 5, 10}, 100));
		System.out.println("No of ways DP : " + getNoWaysToReachScoreDP(new int[]{3, 5, 10}, 100000));
	}

}
