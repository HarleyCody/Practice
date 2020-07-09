_____________________________________________________________________________Best Solution_____________________________________________________________________________
// not my answer, manhatten dist and chebyshev dist
class Solution {
    // try to image (x1, y1) and (x2, y2) are points
    // so |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| is manhatten dist between two points
    // three poinst must be |x1O| + |x2O| <= |x1x2|
    // find a med point so max we can get is |x1x2| iff three points are in same direction
    // only check cloest point to for corners(represents by P[])
    // narrow down maxValue by cur - cloest
    public int maxAbsValExpr(int[] x, int[] y) {
        int res = 0, n = x.length, P[] = {-1,1};
        // pq is factor that can be used as px1 + px0 + qy1 + qy0
        for (int p : P) {
            for (int q : P) {
                int closest = p * x[0] + q * y[0] + 0;
                for (int i = 1; i < n; ++i) {
                    int cur = p * x[i] + q * y[i] + i;
                    res = Math.max(res, cur - closest);
                    closest = Math.min(closest, cur);
                }
            }
        }
        return res;
    }
}
