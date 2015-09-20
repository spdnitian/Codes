package geomatric;

import java.awt.Point;
import java.util.ArrayList;

public class ConvexHullJarvis {
	
	private enum Directions{
		COLINEAR, CLOCKWISE, ANTCLOCKWISE;
	}
	public static ArrayList<Point> convexHull(Point[] points){
		ArrayList<Point> convexHull = new ArrayList<Point>();
		if(points.length < 3){
			return null;
		}
		int pointIndex = getLeftMostPointIndex(points);
		Point leftMost = points[pointIndex];
		Point nextPoint = leftMost;
		do{
			Point lastPoint = nextPoint;
			convexHull.add(lastPoint);
			nextPoint = points[(pointIndex + 1) % points.length];
			for(int i = 0; i < points.length; i++) {
				Point interPoint = points[i];
				Directions direction = getDirection(lastPoint, interPoint, nextPoint);
				if(direction == Directions.ANTCLOCKWISE){
					nextPoint = interPoint;
					pointIndex = i;
				}
			}
		}while(!nextPoint.equals(leftMost));
		return convexHull;
	}

	private static Directions getDirection(Point a, Point b,
			Point c) {
		int direction = (b.x - a.x)*(c.y - b.y) - (b.y - a.y)*(c.x - b.x);
		if(direction == 0){
			return Directions.COLINEAR;
		}
		return direction > 0 ? Directions.ANTCLOCKWISE : Directions.CLOCKWISE;
	}
	
	private static int getLeftMostPointIndex(Point[] points) {
		Point lowestX = points[0];
		int index = 0;
		for(int i = 0; i < points.length; i++){
			Point point = points[i];
			if(point.x < lowestX.x){
				lowestX = point;
				index = i;
			}
		}
		return index;
	}
	
	public static void main(String[] args) {
		Point points[] = new Point[]{new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(1, 2), new Point(3, 0), new Point(0, 0), new Point(3, 3)};
		ArrayList<Point> convexHull = convexHull(points);
		for (Point point : convexHull) {
			System.out.println(point);
		}
	}
}
