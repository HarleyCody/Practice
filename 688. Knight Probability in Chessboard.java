________________________________________________________My Solution____________________________________________________________
class Solution {
    // dfs TLE use memo to reduce
    int in = 0;
    int len; 
    //int[][] dirs = new int[]{{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2} ,{-1, 2}, {-2, 1}}
    int[] dirs = new int[]{-2, -1, 2, 1, 2, -1, -2, 1, -2};
    double[][] memo; 
    public double knightProbability(int N, int K, int r, int c) {
        len = N;
        Math.pow(8, K);
        memo = new double[N * N][K + 1];
        double in = nextSteps(K, r, c);
        return in/Math.pow(8, K);
    }
    
    // get num of in when knight at (x, y) with K steps left;
    private double nextSteps(int K, int x, int y){
        double ans = 0;
        if(x < 0 || y < 0 || x >= len || y >= len){
            return 0;
        }
        if( K == 0){
            return 1;
        }
        int idx = x * len + y;
        if(memo[idx][K] != 0) return memo[idx][K];
        for(int i = 0; i < 8; ++i){
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            ans += nextSteps(K - 1, nx, ny);
        }
        memo[idx][K] = ans;
        return ans;
    }
}
