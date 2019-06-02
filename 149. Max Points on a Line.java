class Point {
	int x, y;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public int maxPoints(int[][] points) {
	Point[] arr = new Point[points.length];
	int i = 0;
	for (int[] point : points)
		arr[i++] = new Point(point[0], point[1]);
	return maxPoints(arr);
}

public int maxPoints(Point[] points) {
	if (points.length < 3) {
		return points.length;
	}
	int max = 2; // 2 points will always be colinear
	for (int i = 1; i < points.length; i++) {
		int count = 0;
		// long type used to handle cross-multiplication which in turn avoids calculating GCD
		long x = points[i].x;
		long y = points[i].y;
		long xPrev = points[i - 1].x;
		long yPrev = points[i - 1].y;

		long dx = x - xPrev;
		long dy = y - yPrev;
		if ((dx == 0) && (dy == 0)) { // duplicate point
			// count duplicate points
			for (int j = 0; j < points.length; j++) {
				if ((points[j].x == x) && (points[j].y == y)) {
					count++;
				}
			}
		} else {
			for (int j = 0; j < points.length; j++) {
				if ((points[j].x - x) * dy == (points[j].y - y) * dx) { // cross-multiply
					count++;
				}
			}
		}
		max = Math.max(max, count);
	}
	return max;
}
