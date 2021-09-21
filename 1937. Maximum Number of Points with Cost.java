//Best Solution:
//curVal = Math.max(cur, left -1, right -1)
class Solution {
    public long maxPoints(int[][] points) {
        long sum[] = new long[points[0].length];
        for(int jdx = 0; jdx < points[0].length; jdx++) {
            sum[jdx] = points[0][jdx];
        }
        
        for(int idx = 1; idx < points.length; idx++) {
            for(int jdx = 1; jdx < points[0].length; jdx++) {
                sum[jdx] = Math.max(sum[jdx], sum[jdx-1]-1);
            }
            for(int jdx = points[0].length-2; jdx >= 0; jdx--) {
                sum[jdx] = Math.max(sum[jdx], sum[jdx+1]-1);
            } 
            for(int jdx = 0; jdx < sum.length; jdx++) {
                sum[jdx] += points[idx][jdx];
            }
        }
        
        long maxSum = sum[0];
        for(long num : sum) {
            if(maxSum < num) {
                maxSum = num;
            }
        }
        return maxSum;
    }
}
// My Solution: val = ogVal + increment
// max increment is Math.max(upper, upper left, upper right)
// use two array to get upper left max and upper right max with O(1)
// upper left = Max(upper left MaxVal + it's index)
// upper right = Max(upper right MaxVal + it's distance to (col - 1)
class Solution {
    public long maxPoints(int[][] points) {
        int row = points.length, col = points[0].length;
        long[] cur = new long[col];
        long ans = 0;
        
        long[] lPrev = new long[col];
        int[] lPrevIdx = new int[col];
        long[] rPrev = new long[col];
        int[] rPrevIdx = new int[col];
        long[] prev = new long[col];
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                cur[c] = prev[c] + points[r][c];
                long left = points[r][c] + lPrev[c] - c;
                cur[c] = Math.max(cur[c], left);
                long right = points[r][c] + rPrev[c] - (col - c - 1);
                cur[c] = Math.max(cur[c], right);
            }
            lPrev[0] = cur[0];
            rPrev[col - 1] = cur[col - 1];
            prev[0] = cur[0];
            for(int i = 1; i < col; ++i){
                prev[i] = cur[i];
                lPrev[i] = lPrev[i - 1];
                lPrevIdx[i] = lPrevIdx[i - 1];
                if(cur[i] + i > lPrev[i]){
                    lPrev[i] = cur[i] + i;
                    lPrevIdx[i] = i;
                }
                
                rPrev[col - 1 - i] = rPrev[col - i];
                rPrevIdx[col - 1 - i] = rPrevIdx[col - i];
                if(cur[col - 1 - i] + i > rPrev[col - 1 - i]){
                    rPrev[col - 1 -i] = cur[col - 1 - i] + i;
                    rPrevIdx[col - 1 - i] = i;
                }
            }
        }
        for(long i : cur){
            ans = Math.max(ans, i);
        }
        return ans;
    }
}
