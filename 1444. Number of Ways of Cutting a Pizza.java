___________________________________________________________________________Best Solution(DP)_____________________________________________________________________
class Solution {
    // dp Solution scan whole pizza with different cut each time
    // prev for rlt cutting with times - 1 next for rlt cutting with times
    // prev = next to update status
    // prev[i][j] sum of cut at i, j from 1 to k
    // part of k is valid to i, j so need to add for every i , j at last
    private static final int mod =(int)1e9 + 7;

    public int ways(String[] pizza, int k) {
        int m = pizza.length;
        int n = pizza[0].length();

        int[][] c = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            char[] chars = pizza[i].toCharArray();
            for (int j = n - 1; j >= 0; j--) {
                c[i][j] = c[i + 1][j] + c[i][j + 1] - c[i + 1][j + 1] + (chars[j] == 'A' ? 1 : 0);
            }
        }

        if (c[0][0] < k) return 0;
        int[][] prev = new int[m][n];
        int[][] next = new int[m][n];
        prev[0][0] = 1;
        for (int nCut = 1; nCut < k; nCut++) {
            for (int i = 0; i < m; i++) {
                int tmp = 0;
                next[i][0] = 0;
                int tmp2 = prev[i][0];
                for (int j = 1; j < n; j++) {
                    if (c[i][j] < c[i][j - 1]) tmp = tmp2;
                    next[i][j] = tmp;
                    tmp2 = (tmp2 + prev[i][j]) % mod;
                }
            }
            for (int j = 0; j < n; j++) {
                int tmp = 0;
                int tmp2 = prev[0][j];
                for (int i = 1; i < m; i++) {
                    if (c[i][j] < c[i - 1][j]) tmp = tmp2;
                    next[i][j] = (next[i][j] + tmp) % mod;
                    tmp2 = (tmp2 + prev[i][j]) % mod;
                }
            }
            int[][] t = prev;
            prev = next;
            next = t;
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] != 0) ans += prev[i][j];
            }
        }
        return (int) (ans % mod);
    }
}
____________________________________________________________________________Naive Solution(DFS + mem)_____________________________________________________________________
class Solution {
    // memoization, sum record number of apples from point(i, j) to (r, c) (rectangle area)
    // mem[k][i][j] record caluclated rlt that when stop at (i, j) has k chances to cut
    // dp[i][j] = cut vertically + cur horizontally; accumulate both ways
    int mod = (int)1e9 + 7, R, C;
    int[][] sum;
    int[][][] mem;
    public int ways(String[] pizza, int k) {
        R = pizza.length;
        C = pizza[0].length();
        sum = new int[R + 1][C + 1];
        mem = new int[k + 1][R][C];
        for(int r = R - 1; 0 <= r; --r){
            for(int c = C - 1; 0 <= c; --c){
                sum[r][c] = sum[r][c + 1] + sum[r + 1][c] - sum[r + 1][c + 1] + (pizza[r].charAt(c) == 'A' ? 1 : 0);
            }
        }
        return dfs(k, 0, 0);
    }
    
    private int dfs(int times, int x, int y){
        if(sum[x][y] == 0){
            return 0;
        }
        if(times == 1){
            return sum[x][y] > 0 ? 1 : 0;
        }
        
        if(mem[times][x][y] != 0){
            return mem[times][x][y];
        }
        
        int ans = 0;
        
        for(int i = x; i < R; ++i){
            if(sum[x][y] - sum[i][y] > 0){
                ans = (ans + dfs(times - 1, i, y)) % mod;
            }
        }
        
        for(int j = y; j < C; ++j){
            if(sum[x][y] - sum[x][j] > 0){
                ans = (ans + dfs(times - 1, x, j)) % mod;
            }
        }
        
        return mem[times][x][y] = ans;
    }
}
