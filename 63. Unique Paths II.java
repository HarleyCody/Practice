____________________________________________My Solution(dp from top and left)__________________________________________________
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;
    
        int[][] dp = new int[R][C];
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for(int i = 0; i < R; ++i){
            for(int j = 0; j < C; ++j){
                if(obstacleGrid[i][j] == 1 || (i == 0 && j == 0))continue;
                int left = 0, top = 0;
                if(j > 0) left = dp[i][j - 1];
                if(i > 0) top = dp[i - 1][j];
                dp[i][j] = left + top;
            }
        }
        return dp[R - 1][C - 1];
    }
}
