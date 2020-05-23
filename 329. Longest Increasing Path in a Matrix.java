_____________________________________________________________Best Solution_______________________________________________________________
// same mem and dfs but dfs without for loop, dfs directly, 2ms faster
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        // do dfs from each index
        // keep arr of known paths
        int[][] known = new int[matrix.length][matrix[0].length];
        int longest = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                longest = Math.max(longest, dfs(matrix, known, i, j));
            }
        }
        return longest;
    }
    
    private int dfs(int[][] matrix, int[][] known, int i, int j) {
        if (known[i][j] > 0) {
            return known[i][j];
        }
        int max = 1;
        if (i >= 1 && matrix[i][j] < matrix[i - 1][j]) {
            max = Math.max(max, 1 + dfs(matrix, known, i - 1, j));
        }
        if (j >= 1 && matrix[i][j] < matrix[i][j - 1]) {
            max = Math.max(max, 1 + dfs(matrix, known, i, j - 1));
        }
        if (i < matrix.length - 1 && matrix[i][j] < matrix[i + 1][j]) {
            max = Math.max(max, 1 + dfs(matrix, known, i + 1, j));
        }
        if (j < matrix[i].length - 1 && matrix[i][j] < matrix[i][j + 1]) {
            max = Math.max(max, 1 + dfs(matrix, known, i, j + 1));
        }
        known[i][j] = max;
        return max;
    }
}
______________________________________________________________My Solution_______________________________________________________________
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
