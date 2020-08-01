__________________________________________________________________________________________My Solution________________________________________________________________________
class Solution {
    // dp from right or down, calcualte from tar to src(reverse back)
    // in any status the HP should not be negative,
    // 0 can be regard as alive in dp, in the final answer it should increase 1
    public int calculateMinimumHP(int[][] dungeon) {
        int R = dungeon.length, C = dungeon[0].length;
        int[][] dp = new int[R][C];
        
        dp[R - 1][C - 1] = dungeon[R - 1][C - 1];
        int curMin;
        for(int r = R - 1; 0 <= r; --r){
            for(int c = C - 1; 0 <= c; --c){
                curMin = Integer.MAX_VALUE;
                if(r < R - 1 && c < C - 1){
                     curMin = Math.min(dp[r][c + 1] - dungeon[r][c], dp[r + 1][c] - dungeon[r][c]);
                }else if(c < C - 1){
                    curMin = dp[r][c + 1] - dungeon[r][c];
                }else if(r < R - 1){
                    curMin = dp[r + 1][c] - dungeon[r][c];
                }else{
                    curMin = -dungeon[r][c];
                }
                dp[r][c] = Math.max(0, curMin);
            }
        }
        return Math.max(1, dp[0][0] + 1);
    }
}
