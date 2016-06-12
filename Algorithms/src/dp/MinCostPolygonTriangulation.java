package dp;

import java.awt.Point;

import dp.timetesting.MeasureTimings;
import dp.timetesting.TimeTesting;

public class MinCostPolygonTriangulation implements TimeTesting{
	private static double getDist(Point p1, Point p2){
		return 	Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
	}
	private static double getCost(Point[] points, int start, int intermediate, int end) {
		return getDist(points[start], points[intermediate]) + getDist(points[intermediate], points[end]) + getDist(points[end], points[start]);
	}
	public static double minCostDP(Point[] points) {
		int n = points.length;
		double dp[][] = new double[n][n];
		for (int diagonal = 0; diagonal <= n - 1; diagonal++) {
			for(int i = 0, j = diagonal; j <= n - 1; i++,j++){
				if(j < i + 2){
					dp[i][j] = 0;
				}else{
					double minCost = Double.POSITIVE_INFINITY;
					for(int  k = i + 1; k <= j -1; k++){
						double cost = dp[i][k] + dp[k][j] + getCost(points, i, k, j);
						if(cost < minCost){
							minCost = cost;
						}
					}
					dp[i][j] = minCost;
				}
			}
		}
		return dp[0][n-1];
	}
	public static double minCost(Point[] points, int start, int end) {
		if(end <  start + 2){ // The start and end doesn't form a triangle
			return 0;
		}
		double minCost = Double.POSITIVE_INFINITY;
		for(int intermediate = start + 1; intermediate <= end - 1; intermediate++){
			double cost = minCost(points, start, intermediate) + minCost(points, intermediate, end) + getCost(points, start, intermediate, end);
			if(cost < minCost){
				minCost = cost;
			}
		}
		return minCost;
	}
	@Override
	public void testNormal() {
		Point points[] = {new Point(0, 0), new Point(1, 0), new Point(2, 1), new Point(1, 2), new Point(0, 2), new Point(7, 12)};
		System.out.println("Triangluation value:" + minCost(points, 0, points.length - 1));
	}
	@Override
	public void testDP() {
		Point points[] = {new Point(0, 0), new Point(1, 0), new Point(2, 1), new Point(1, 2), new Point(0, 2), new Point(7, 12)};
		System.out.println("Triangluation value:" + minCostDP(points));
	}
	public static void main(String[] args) {
		MeasureTimings.meauseRecursiveTimeTaken(new MinCostPolygonTriangulation());
		MeasureTimings.measusreDPTimeTaken(new MinCostPolygonTriangulation());
	}
}
