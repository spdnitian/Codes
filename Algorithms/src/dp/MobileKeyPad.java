package dp;

public class MobileKeyPad {
	private static boolean isValidMove(int rowIndex,int colIndex, int[] move){
		int nextRowIndex = rowIndex + move[0], nextColIndex = colIndex + move[1];
		if(nextRowIndex == 3 && (nextColIndex == 0 || nextColIndex == 2)){
			return false;
		}
		if(nextRowIndex < 0 || nextRowIndex > 3 || nextColIndex < 0 || nextColIndex > 2){
			return false;
		}
		return true;
	}
	private static int getCount(int rowIndex,int colIndex, int stepLength){
		if(stepLength == 0 || stepLength == 1){
			return stepLength;
		}
		int count = 0;
		int[] move = null;
		count += getCount(rowIndex, colIndex, stepLength - 1); // no move clicking on same
		move = new int[]{-1, 0};
		if(isValidMove(rowIndex, colIndex, move)){ // top move
			count += getCount(rowIndex - 1, colIndex, stepLength - 1);
		}
		move = new int[]{1, 0};
		if(isValidMove(rowIndex, colIndex, move)){ // bottom move
			count += getCount(rowIndex + 1, colIndex, stepLength - 1);
		}
		move = new int[]{0, 1};
		if(isValidMove(rowIndex, colIndex, move)){ // right move
			count += getCount(rowIndex, colIndex + 1, stepLength - 1);
		}
		move = new int[]{0, -1};
		if(isValidMove(rowIndex, colIndex, move)){ // left move
			count += getCount(rowIndex, colIndex - 1, stepLength - 1);
		}
		return count;
	}
	
	public static int getTotalNumbers(int stepLength){
		int totalCount = 0;
		for(int i = 0; i <= 2; i++){
			for(int j = 0; j <= 2; j++){
				totalCount += getCount(i, j, stepLength);
			}
		}
		totalCount += getCount(3, 1, stepLength);
		return totalCount;
	}
	
	public static int getTotalNumbersDP(int stepLength){
		int totalCount = 0;
		int[][][] valueMatrix = new int[4][3][stepLength];
		for(int i = 0; i <= 2; i++){
			for(int j = 0; j <= 2; j++){
				valueMatrix[i][j][0] = 1;
			}
		}
		int[] move = null;
		valueMatrix[3][1][0] = 1;
		for(int k = 1; k < stepLength; k++){
			for(int rowIndex = 0; rowIndex <= 3; rowIndex++){
				for(int colIndex = 0; colIndex <= 2; colIndex++){
					if(!(rowIndex == 3 && colIndex != 1)){
						int count = 0;
						count += valueMatrix[rowIndex][colIndex][k - 1]; // no move clicking on same
						move = new int[]{-1, 0};
						if(isValidMove(rowIndex, colIndex, move)){ // top move
							count += valueMatrix[rowIndex - 1][colIndex][k - 1];
						}
						move = new int[]{1, 0};
						if(isValidMove(rowIndex, colIndex, move)){ // bottom move
							count += valueMatrix[rowIndex + 1][colIndex][k - 1];
						}
						move = new int[]{0, 1};
						if(isValidMove(rowIndex, colIndex, move)){ // right move
							count += valueMatrix[rowIndex][colIndex + 1][k - 1];
						}
						move = new int[]{0, -1};
						if(isValidMove(rowIndex, colIndex, move)){ // left move
							count += valueMatrix[rowIndex][colIndex - 1][k - 1];
						}
						valueMatrix[rowIndex][colIndex][k] = count;
					}
				}
			}
		}
		for(int i = 0; i <= 3; i++){
			for(int j = 0; j <= 2; j++){
				totalCount += valueMatrix[i][j][stepLength - 1];
			}
		}
		return totalCount;
	}
	
	public static void main(String[] args) {
		for(int j = 1; j <= 5; j++){
			System.out.println(getTotalNumbers(j));
			System.out.println(getTotalNumbersDP(j));
			System.out.println();
		}
	}
}
