//Best Solution: find start which is preSt by choosing the max value it can make with i and update the preSt accordingly
// curMax = Math.max(points[pre] + points[i], points[i] + points[i - 1])
class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        if (points.length == 2) {
            return points[1][1] + points[0][1] + points[1][0] - points[0][0];
        }
        int maxv = Integer.MIN_VALUE;
        int preSt = -1;
        for (int i = 1; i < points.length; i++) {
            if (preSt == -1) {
                // previous point at i-1 does not have a k such that points[i-1][0] - points[k][0] <= k
                int v = points[i][0] - points[i-1][0];
                if (v <= k) {
                    v += points[i][1] + points[i-1][1];
                    if (v > maxv) {
                        maxv = v;
                    }
                    preSt = i - 1;
                }
            } else {
                int v = points[i][0] - points[preSt][0];
                if (v <= k) {
                    // the same point may be the max peer
                    v += points[i][1] + points[preSt][1];
                    // or the prev one
                    int v2 = points[i][1] + points[i-1][1] + points[i][0] - points[i-1][0];
                    if (v2 >= v) {
                        preSt = i - 1;
                        v = v2;
                    }
                    if (v > maxv) {
                        maxv = v;
                    }
                } else {
                    // scan to find max
                    int j = preSt + 1;
                    preSt = -1;
                    int mv = Integer.MIN_VALUE;
                    for (; j < i; j++) {
                        v = points[i][0] - points[j][0];
                        if (v <= k) {
                            v += points[i][1] + points[j][1];
                            if (v > mv) {
                                mv = v;
                                preSt = j;
                            }
                        }
                    }
                    if (mv > maxv) {
                        maxv = mv;
                    }
                }
            }
        }
        return maxv;
    }
}
