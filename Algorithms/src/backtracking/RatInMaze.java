package backtracking;

import java.awt.Point;
import java.util.ArrayList;

public class RatInMaze {
	private static int solution = 0;
	public static void printRatPath(int[][] boardConfig, Point startPosition, Point endPosition){
		boolean[][] board = new boolean[boardConfig.length][boardConfig[0].length];
		board[startPosition.x][startPosition.y] = true;
		ArrayList<Point> pathArrayList = new ArrayList<Point>();
		pathArrayList.add(new Point(startPosition.x, startPosition.y));
		printMazePath(boardConfig, board, startPosition, endPosition, pathArrayList);
	}
	private static boolean printMazePath(int[][] boardConfig, boolean[][] board, Point position, Point endPosition, ArrayList<Point> pathArrayList) {
		if(position.x == endPosition.x && position.y == endPosition.y){
			printPath(pathArrayList);
			return true;
		}
		ArrayList<Point> allNextValidPositions = getAllNextValidPositions(boardConfig, board, position);
		boolean solutionFound = false;
		for (Point nextPosition : allNextValidPositions) {
			if(!solutionFound){
				pathArrayList.add(nextPosition);
				board[nextPosition.x][nextPosition.y] = true;
				solutionFound = printMazePath(boardConfig, board, nextPosition, endPosition, pathArrayList);
				pathArrayList.remove(pathArrayList.size() - 1);
				board[nextPosition.x][nextPosition.y] = false;
			}
		}
		return solutionFound;
	}
	private static ArrayList<Point> getAllNextValidPositions(int[][] boardConfig, boolean[][] board, Point position) {
		ArrayList<Point> validPositions = new ArrayList<Point>();
		int nextCellRowIndex, nextCellColIndex;
		
		nextCellColIndex = position.y + 1;
		nextCellRowIndex = position.x;
		Point nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(boardConfig, board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y - 1;
		nextCellRowIndex = position.x;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(boardConfig, board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y;
		nextCellRowIndex = position.x + 1;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(boardConfig, board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y;
		nextCellRowIndex = position.x - 1;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(boardConfig, board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		return validPositions;
	}
	private static boolean isValidStep(int[][] boardConfig, boolean[][] board, Point point) {
		int boardRowSize = boardConfig.length, boardColSize = boardConfig[0].length;
		//System.out.println(boardRowSize + " " + boardColSize + " " + point);
		if(point.x < 0 || point.y < 0 || point.x > boardRowSize - 1 || point.y > boardColSize - 1 || boardConfig[point.x][point.y] == 0 || board[point.x][point.y]){
			return false;
		}
		return true;
	}
	private static void printPath(ArrayList<Point> pathArrayList) {
		System.out.println("Solution "+ (++solution));
		for (Point point : pathArrayList) {
			System.out.println(point.toString());
		}
		System.out.println("End Solution "+ (solution));
		System.out.println();
	}
	public static void main(String[] args) {
		int[][] maze  =  { {1, 0, 0, 0},
		        {1, 1, 0, 1},
		        {0, 1, 0, 0},
		        {1, 1, 1, 1}
		    };
		printRatPath(maze, new Point(0, 0), new Point(3, 3));
	}

}
