class Solution {
    // mem + recursion find(dfs)
    // dp[i][j] the max path start from i, j;
    int[] dirs = {0, -1, 0, 1, 0};
    int[][] dp;
    int R, C, ans = 0;
    public int longestIncreasingPath(int[][] matrix) {
        R = matrix.length;
        if(R == 0){
            return 0;
        }
        C = matrix[0].length;
        
        int num = R * C;
        dp = new int[R][C];
        
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(dp[r][c] != 0){
                    continue;
                }
                find(matrix, r, c);
            }
        }
        return ans;
    }
    
    private int find(int[][] matrix, int r, int c){
        if(dp[r][c] != 0 ) {
            return dp[r][c];
        }
        int len = 1;
        for(int i = 0; i < 4; ++i){
            int nx = r + dirs[i];
            int ny = c + dirs[i + 1];
            if(nx < 0  || nx == R || ny < 0 || ny == C || matrix[nx][ny] <= matrix[r][c]){
                continue;
            }
            len = Math.max(1 + find(matrix, nx, ny), len);
        }
        ans = Math.max(ans, len);
        dp[r][c] = len;
        
        return len;
    }
}
