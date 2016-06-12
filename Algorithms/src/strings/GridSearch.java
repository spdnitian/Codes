package strings;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class GridSearch {
	
	private static Point getIndex(String[] grid, long index){
		int colSize = grid[0].length();
		int rowNo = (int)(index / colSize);
		int colNo = (int) (index - rowNo * colSize);
		Point point = new Point(rowNo, colNo);
		return point;
	}
	
	private static ArrayList<Integer> findPatternKMP(String[] dataGrid, String[] patternGrid) { // returns list of starting indexes of pattern
		int[] prefixArr = prefixArr(patternGrid);
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int rowSizeData = dataGrid.length, colSizeData = dataGrid[0].length(),rowSizePattern = patternGrid.length, colSizePattern = patternGrid[0].length();
		int strlen = rowSizeData * colSizeData, patternlen = rowSizePattern  * colSizePattern, j = 0;
		for(int i = 0; i < strlen; i++){
			Point pointI = getIndex(dataGrid, i), pointJ = getIndex(patternGrid, j);
			while(j > patternlen - 1 || (j != 0 && dataGrid[pointI.x].charAt(pointI.y) != patternGrid[pointJ.x].charAt(pointJ.y))){
				j = prefixArr[j - 1];
				pointJ = getIndex(patternGrid, j);
			}
			if(dataGrid[pointI.x].charAt(pointI.y) == patternGrid[pointJ.x].charAt(pointJ.y)){
				j++;
			}
			if(j == patternlen){
				indices.add(i - j + 1);
			}
		}
		return indices;
	}
	
	private static int[] prefixArr(String[] patternGrid) {
		int rowSize = patternGrid.length, colSize = patternGrid[0].length();
		int[] prefixArr = new int[rowSize * colSize];
		prefixArr[0] = 0;
		int j = 0;
		for(int i = 1; i < prefixArr.length;i++){
			Point pointI = getIndex(patternGrid, i), pointJ = getIndex(patternGrid, j);
			while(j != 0 && patternGrid[pointI.x].charAt(pointI.y) != patternGrid[pointJ.x].charAt(pointJ.y)){
				j = prefixArr[j - 1];
			}
			if(patternGrid[pointI.x].charAt(pointI.y) == patternGrid[pointJ.x].charAt(pointJ.y)){
				prefixArr[i] = ++j;
			}
		}
		return prefixArr;
	}
	
	public static void printIfGridFound(String[] dataGrid, String[] patternGrid) {
		String[] strings = new String[]{patternGrid[0]};
		ArrayList<Integer> indices = findPatternKMP(dataGrid, strings);
		boolean isPresent = true;
		for (Integer index : indices) {
			Point p = getIndex(dataGrid, index);
			int pLength = patternGrid[0].length();
			if(p.y + pLength <= dataGrid[0].length()){
				int k = p.x + 1;
				for (int i = 1; isPresent && i < patternGrid.length; i++,k++) {
					if(k > dataGrid.length - 1){
						isPresent = false;
					}else{
						isPresent = dataGrid[k].regionMatches(p.y, patternGrid[i], 0, pLength);
					}
				}
			}
			if(isPresent){
				break;
			}
		}
		if(isPresent){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}
	}
	
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int R = scanner.nextInt();
            String G[] = new String[R];
            for(int G_i=0; G_i < R; G_i++){
                G[G_i] = scanner.next();
            }
            int r = scanner.nextInt();
            String P[] = new String[r];
            for(int P_i=0; P_i < r; P_i++){
                P[P_i] = scanner.next();
            }
            GridSearch.printIfGridFound(G, P);
        }
        scanner.close();
    }
}
