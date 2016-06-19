package dp;

import dp.timetesting.MeasureTimings;
import dp.timetesting.TimeTesting;
import searchNsort.CommonUtilities;

public class MaxPointGridUsing2Traversals implements TimeTesting{
	
	private static int getMaxPointUtil(int[][] grid, int row, int col1, int col2) {
		int cols = grid[0].length;
		if(col1 < 0 || col1 > cols - 1 || col2 < 0 || col2 > cols - 1){
			return Integer.MIN_VALUE; // when col value is invalid
		}
		if(row == 0){ // Base case where we are in first row
			if(col1 == 0 && col2 == cols - 1){
				return grid[row][col1] + grid[row][col2]; // when we are in first col cell and last col cell
			}else{
				return Integer.MIN_VALUE; // if we are in any other col cell
			}
		}
		int maxFromLastTraversal = CommonUtilities.getMax(
				getMaxPointUtil(grid, row - 1, col1 - 1, col2 - 1),
				getMaxPointUtil(grid, row - 1, col1, col2 - 1),
				getMaxPointUtil(grid, row - 1, col1 + 1, col2 - 1),
				getMaxPointUtil(grid, row - 1, col1 - 1, col2),
				getMaxPointUtil(grid, row - 1, col1, col2),
				getMaxPointUtil(grid, row - 1, col1 + 1, col2),
				getMaxPointUtil(grid, row - 1, col1 - 1, col2 + 1),
				getMaxPointUtil(grid, row - 1, col1, col2 + 1),
				getMaxPointUtil(grid, row - 1, col1 + 1, col2 + 1)
				); //get Max value from last traversal
		if(col1 == col2){ // Both traversal are in same cell
			return grid[row][col1] + maxFromLastTraversal;
		}else{
			return grid[row][col1] + grid[row][col2] + maxFromLastTraversal;
		}
	}
	
	public static int getMaxPoint(int[][] grid){
		int row = grid.length, col = grid[0].length;
		return getMaxPointUtil(grid, row - 1 , 0, col - 1);
	}
	
	private static int getMaxPointFromDP(int[][][] dp,int row, int col1, int col2){
		int cols = dp[0][0].length;
		if(col1 < 0 || col1 > cols -1 || col2 < 0 || col2 > cols - 1){
			return Integer.MIN_VALUE;
		}
		return dp[row][col1][col2];
	}
	
	public static int getMaxPointDP(int[][] grid){
		int row = grid.length, col = grid[0].length;
		int[][][] dp = new int[row][col][col];
		dp[0][0][col - 1] = grid[0][0] + grid[0][col - 1];
		for(int col1No = 1; col1No < col - 1; col1No++){
			for(int col2No = 1; col2No < col - 1; col2No++){
				dp[0][col1No][col2No] = Integer.MIN_VALUE;
			}
		}
		for(int rowNo = 1; rowNo <= row - 1; rowNo++){
			for(int col1 = 0; col1 <= col - 1; col1++){
				for(int col2 = col - 1; col2 >= 0; col2--){
					int maxFromLastTraversal = CommonUtilities.getMax(
							getMaxPointFromDP(dp, rowNo - 1, col1 - 1, col2 - 1),
							getMaxPointFromDP(dp, rowNo - 1, col1, col2 - 1),
							getMaxPointFromDP(dp, rowNo - 1, col1 + 1, col2 - 1),
							getMaxPointFromDP(dp, rowNo - 1, col1 - 1, col2),
							getMaxPointFromDP(dp, rowNo - 1, col1, col2),
							getMaxPointFromDP(dp, rowNo - 1, col1 + 1, col2),
							getMaxPointFromDP(dp, rowNo - 1, col1 - 1, col2 + 1),
							getMaxPointFromDP(dp, rowNo - 1, col1, col2 + 1),
							getMaxPointFromDP(dp, rowNo - 1, col1 + 1, col2 + 1)
							);
					if(col1 == col2){
						dp[rowNo][col1][col2] = grid[rowNo][col1] + maxFromLastTraversal;
					}else{
						dp[rowNo][col1][col2] = grid[rowNo][col1] + grid[rowNo][col2] + maxFromLastTraversal;
					}
				}
			}
		}
		return dp[row - 1][0][col - 1];
	}

	@Override
	public void testNormal() {
		int grid[][] = {{3, 6, 8, 2},
                {5, 2, 4, 3},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {5, 2, 4, 3},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {5, 2, 4, 3},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {1, 1, 20, 10} 
               };
		System.out.println("Max point from 2 traversal using recursive approach : " + getMaxPoint(grid));
		
	}

	@Override
	public void testDP() {
		int grid[][] = {{3, 6, 8, 2},
                {5, 2, 4, 3},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {5, 2, 4, 3},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {5, 2, 4, 3},
                {1, 1, 20, 10},
                {1, 1, 20, 10},
                {1, 1, 20, 10}
               };
		System.out.println("Max point from 2 traversal using DP approach : " + getMaxPointDP(grid));	
	}
	
	public static void main(String[] args) {
		MaxPointGridUsing2Traversals maxPointGridUsing2Traversals = new MaxPointGridUsing2Traversals();
		MeasureTimings.meauseRecursiveTimeTaken(maxPointGridUsing2Traversals);
		MeasureTimings.measusreDPTimeTaken(maxPointGridUsing2Traversals);
	}

}
