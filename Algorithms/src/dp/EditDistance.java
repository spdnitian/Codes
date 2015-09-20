package dp;

public class EditDistance {
	public int editDistance(String s1, String s2, int s1_length, int s2_length){
		int editDist = 0;
		if(s1_length == 0 && s2_length == 0){
			return 0;
		}
		if(s1_length == 0){
			return s2_length;
		}
		if(s2_length == 0){
			return s1_length;
		}
		editDist = min(1 + editDistance(s1, s2, s1_length - 1, s2_length), 1 + editDistance(s1, s2, s1_length, s2_length - 1), editDistance(s1, s2, s1_length - 1, s2_length - 1) + diff(s1, s2, s1_length - 1, s2_length - 1));
		return editDist;
	}
	
	public int editDistance_dp_tabulation(String s1, String s2){
		int[][] editDistMatrix = new int[s1.length() + 1][s2.length() + 1];
		int editDist = 0;
		for(int k = 0 ;k <= s1.length(); k++){
			editDistMatrix[k][0] = k;
		}
		for(int k = 0 ;k <= s2.length(); k++){
			editDistMatrix[0][k] = k;
		}
		for(int i = 1; i <= s1.length(); i++){
			for(int j = 1; j <= s2.length(); j++){
				editDistMatrix[i][j] = min(1 + editDistMatrix[i-1][j], 1 + editDistMatrix[i][j-1], editDistMatrix[i - 1][j - 1] + diff(s1, s2, i - 1, j - 1));
			}
		}
		editDist = editDistMatrix[s1.length()][s2.length()];
		return editDist;
	}

	private int min(int num, int... ints) {
		int min = num;
		for(int i : ints){
			if(i < min){
				min = i;
			}
		}
		return min;
	}

	private int diff(String s1, String s2, int i, int j) {
		if(s1.charAt(i) == s2.charAt(j)){
			return 0;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		EditDistance editDistance = new EditDistance();
		String s1 = "Hey Baby!";
		String s2 = "Babo!";
		int s1_lastIndex = s1.length();
		int s2_lastIndex = s2.length();
		System.out.println(editDistance.editDistance(s1, s2, s1_lastIndex, s2_lastIndex));
		System.out.println(editDistance.editDistance_dp_tabulation(s1, s2));
	}
}
