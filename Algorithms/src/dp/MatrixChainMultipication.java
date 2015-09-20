package dp;

public class MatrixChainMultipication {
	int[][] matrixDimensionArr;
	
	public int minMult(int startIndex, int endIndex) {
		int minMult = Integer.MAX_VALUE;
		if(startIndex == endIndex){
			return 0;
		}
		int mult = 0;
		for(int i = startIndex; i < endIndex; i++){
			mult = minMult(startIndex, i) + minMult(i + 1, endIndex) + getCost(startIndex, i, endIndex);
			if(mult < minMult){
				minMult = mult;
			}
		}
		return minMult;
	}
	
	public int minMult_dp(int startIndex, int endIndex) {
		int size = endIndex - startIndex + 1;
		int multMatrix[][] = new int[size][size];
		for(int i = 1; i <= size - 1;i++){
			for(int j = 0; j <= size - 1 - i; j++){
				multMatrix[j][j + i] = getMCMMin(multMatrix, j, j + i);
			}
		}
		return multMatrix[0][size - 1];
	}

	private int getMCMMin(int[][] multMatrix, int start, int end) {
		int interMed = 0;
		int mult, minMult = Integer.MAX_VALUE;
		
		for(interMed = start; interMed < end; interMed++){
			mult = multMatrix[start][interMed] + multMatrix[interMed + 1][end] + getCost(start, interMed, end);
			if(mult < minMult){
				minMult = mult;
			}
		}
		return minMult;
	}

	private int getCost(int startIndex, int i, int endIndex) {
		return this.matrixDimensionArr[startIndex][0]*this.matrixDimensionArr[i][1]*this.matrixDimensionArr[endIndex][1];
	}
	
	public static void main(String[] args) {
		MatrixChainMultipication mcm = new MatrixChainMultipication();
		mcm.matrixDimensionArr = new int[][]{{40, 20},{20, 30},{30, 10},{10, 30}};
		System.out.println(mcm.minMult(0, mcm.matrixDimensionArr.length - 1));
		System.out.println(mcm.minMult_dp(0, mcm.matrixDimensionArr.length - 1));
	}
}
