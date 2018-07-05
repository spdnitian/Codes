package misc;

import java.util.Arrays;

public class ArrayBar {
	public static void calcWater(int[] tower) {
		int waterAmount = 0;
		int n = tower.length;
		int leftMax[] = new int[n];
		int rightMax[] = new int[n];
		// to calculate height highest tower to left of each tower
		int tempMax = tower[0];
		for (int i = 0; i < n; i++) {
			if (tower[i] > tempMax) {
				tempMax = tower[i];
				leftMax[i] = tempMax;
			} else {
				leftMax[i] = tempMax;
			}
		}
		tempMax = tower[n - 1];
		for (int j = n - 1; j >= 0; j--) {
			if (tower[j] > tempMax) {
				tempMax = tower[j];
				rightMax[j] = tempMax;
			} else {
				rightMax[j] = tempMax;
			}
		}
		for (int k = 0; k < n; k++) {
			waterAmount += Math.min(leftMax[k], rightMax[k]) - tower[k];
		}
		System.out.println(Arrays.toString(leftMax));
		System.out.println(Arrays.toString(tower));
		System.out.println(Arrays.toString(rightMax));
		System.out.println(waterAmount);
	}

	public static void main(String[] args) {
		calcWater(new int[] { 5, 3, 7, 2, 6, 4, 5, 9, 1, 2 });
	}
}