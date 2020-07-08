__________________________________________________________________________Best Solution dp(reversely)___________________________________________________________________________
class Solution {
    // reverse from rlt to head, as rlt is limited in range
    // dp[k] = probability of reaching point k
    // dp[k] = (dp[k+1] + ... + dp[k+W]) / W
    // largest val it can reach is K + W;
    // s starts from min() as it can only expand W vals most but N - K + 1 make sure vals starts from k to N
    // N - K + 1 = sum of dp[k] - dp[N];
    // shift Sum = dp[k] + ... + dp[k + w]; nextSum = dp[k - 1] + ... + dp[k - 1 + w];
    // so Sum - nextSum = dp[k + w] - dp[k - 1];
    // nextSum = Sum + dp[k - 1] - dp[k + w];
    // deduct from k - 1 to k as k to N has been calculated
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[K + W + 1];
        
        // dp[x] = the answer when Alice has x points
        for (int k = K; k <= N; ++k)
            dp[k] = 1.0;

        double S = Math.min(N - K + 1, W);

        // S = dp[k+1] + dp[k+2] + ... + dp[k+W]
        for (int k = K - 1; k >= 0; --k) {
            dp[k] = S / W;
            // shift Sum to calculate next dp
            S += dp[k] - dp[k + W];
        }
        return dp[0];
    }
}
___________________________________________________________________________My Solution Mem + dfs (TLE)___________________________________________________________________________
class Solution {
    double[][] mem;
    int len;
    public double new21Game(int N, int K, int W) {
        len = W;
        if(N == 0 || K == 0){
            return 1.0;
        }
        mem = new double[N + 1][K + 1];
        
        return calculate(N, K);
    }
    
    private double calculate(int n, int k){
        if(k <= 0){
            return 1.0;
        }
        if(mem[n][k] != 0){
            return mem[n][k];
        }
        
        double ans = 0.0;
        double per = 1.0 / len;
        for(int i = 1; i <= len; ++i){
            if(n - i >= 0){
                ans += per * calculate(n - i, k - i);
            }
        }
        
        mem[n][k] = ans;
        return ans;
    }
}
