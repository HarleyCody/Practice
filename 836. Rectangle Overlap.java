class Solution {
    // find intersect of X axis,
    // find intersect of Y axis.
    // both have intersection, true, otherwise false
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // recX record X of rec from left to right
        int[] rec1X = new int[]{rec1[0], rec1[2]};
        int[] rec2X = new int[]{rec2[0], rec2[2]};
        if(rec1[0] > rec2[0]){
            rec1X = new int[]{rec2[0], rec2[2]};
            rec2X = new int[]{rec1[0], rec1[2]};
        }
        // recY record Y of rec from bottom to top;
        int[] rec1Y = new int[]{rec1[1], rec1[3]};
        int[] rec2Y = new int[]{rec2[1], rec2[3]};
        if(rec1[1] > rec2[1]){
            rec1Y = new int[]{rec2[1], rec2[3]};
            rec2Y = new int[]{rec1[1], rec1[3]};
        }
        // x and y both has intersections
        if(rec1X[1] > rec2X[0] && rec1Y[1] > rec2Y[0]) return true;
        return false;
    }
}
