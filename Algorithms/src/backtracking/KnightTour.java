package backtracking;

import java.awt.Point;
import java.util.ArrayList;

public class KnightTour {
	private static int solution;
	public static void printKnightTour(Point startPosition, int rowSize, int columnSize){
		boolean[][] board = new boolean[rowSize][columnSize];
		board[startPosition.x][startPosition.y] = true;
		ArrayList<Point> pathArrayList = new ArrayList<Point>();
		pathArrayList.add(new Point(startPosition.x, startPosition.y));
		printKT(board, startPosition, pathArrayList);
	}
	private static boolean printKT(boolean[][] board, Point position, ArrayList<Point> pathArrayList) {
		if(pathArrayList.size() == board.length * board[0].length){
			printPath(pathArrayList);
			return true;
		}
		ArrayList<Point> allNextValidPositions = getAllNextValidPositions(board, position);
		boolean solutionFound = false;
		for (Point nextPosition : allNextValidPositions) {
			if(!solutionFound){
				pathArrayList.add(nextPosition);
				board[nextPosition.x][nextPosition.y] = true;
				solutionFound = printKT(board, nextPosition, pathArrayList);
				pathArrayList.remove(pathArrayList.size() - 1);
				board[nextPosition.x][nextPosition.y] = false;
			}
		}
		return solutionFound;
	}
	private static ArrayList<Point> getAllNextValidPositions(boolean[][] board,
			Point position) {
		ArrayList<Point> validPositions = new ArrayList<Point>();
		int nextCellRowIndex, nextCellColIndex;
		
		nextCellColIndex = position.y + 2;
		nextCellRowIndex = position.x + 1;
		Point nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y + 2;
		nextCellRowIndex = position.x - 1;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y + 1;
		nextCellRowIndex = position.x + 2;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y + 1;
		nextCellRowIndex = position.x - 2;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y - 2;
		nextCellRowIndex = position.x + 1;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y - 2;
		nextCellRowIndex = position.x - 1;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y - 1;
		nextCellRowIndex = position.x + 2;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		
		nextCellColIndex = position.y - 1;
		nextCellRowIndex = position.x - 2;
		nextPoint = new Point(nextCellRowIndex, nextCellColIndex);
		if(isValidStep(board, nextPoint)){
			validPositions.add(nextPoint);
		}
		return validPositions;
	}
	private static boolean isValidStep(boolean[][] board, Point point) {
		int boardRowSize = board.length, boardColSize = board[0].length;
		//System.out.println(boardRowSize + " " + boardColSize + " " + point);
		if(point.x < 0 || point.y < 0 || point.x > boardRowSize - 1 || point.y > boardColSize - 1 || board[point.x][point.y]){
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
		printKnightTour(new Point(0, 0), 8, 8);
	}

}
