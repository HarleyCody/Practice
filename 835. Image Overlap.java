________________________________________________________________________________________Best Solution________________________________________________________________________
class Solution {
    // use bit to recorder sum of status of rows;
    // b is used in first test, so use c to replace b do the reverse testMax
    // bits[i] recorder status of row[i]
    public int largestOverlap(int[][] A, int[][] B) {
        int len = A.length;
        int[] bitsA = new int[len], bitsB = new int[len],bitsC = new int[len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                bitsA[i] = (bitsA[i] << 1) + A[i][j];
                bitsB[i] = (bitsB[i] << 1) + B[i][j];
                bitsC[i] = (bitsC[i] << 1) + B[i][j];
            }
        }
        int max = testMax(bitsA, bitsB, 0);
        return Math.max(max, testMax(bitsC, bitsA, max));
    }
    private int testMax(int[] bitsA, int[] bitsB, int max) {
        int len = bitsA.length;        
        for (int i = 0, m = len; i < len; i++, m--) {
            for (int j = 0, k = len; j < len && m * k > max; j++,k--) {
                int sum = 0;
                for (int n = len - k, p = 0; n < len; n++, p++)
                    sum += Integer.bitCount(bitsB[p] & bitsA[n]);
                max = Math.max(sum, max);
            }
            for (int j = 0; j < len; j++) bitsB[j] >>= 1;
        }
        return max;
    }
}
__________________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // only need to move right down
    // ans == Math.max( use A to match B , use B to match A)
    // A start with (offSetR, offSetC), B starts with (0, 0) count both 1
    public int largestOverlap(int[][] A, int[][] B) {
        return Math.max(getMax(A, B), getMax(B, A));
    }
    
    private int getMax(int[][] src, int[][] tar){
        int R = src.length;
        if(R == 0){
            return 0;
        }
        int C = src[0].length;
        
        int ans = 0;
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                int sum = 0;
                for(int offR = 0; r + offR < R; ++offR){
                    for(int offC = 0; c + offC < C; ++offC){
                        if((tar[offR][offC] & src[r + offR][c + offC]) == 1){
                            ++sum;
                        }
                    }
                }
                ans = Math.max(ans, sum);
            }
        }
        
        return ans;
    }
}
