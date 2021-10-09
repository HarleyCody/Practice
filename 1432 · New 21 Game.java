//Best Solution: One pass O(N), sliding window to get sum of prevStatus. So dp[i] = sum * 1 / W
//If it is W, then it is sum + 1.0 / W(current drives from prevStatus + direct pick from (1 - W))
public class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) return 1;
        double[] dp = new double[N + 1];
        double res = 0.0, sum = 0.0;
        for (int i = 1; i <= N; i++) {
            dp[i] = i <= W ? sum / W + 1.0 / W : sum/W;
            if (i < K) {
                sum += dp[i];
            }
            if (i > W) {
                sum -= dp[i - W];
            }
            if (i >= K) {
                res += dp[i];
            }
        }
        return res;
    }
}
//My Solution: DP, dp[i] means prob of reaching number i
// Sum of dp[K ~ N]
public class Solution {
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[K + W + 1];
        dp[0] = 1.0;
        
        for(int i = 0; i < K; ++i){
            if(dp[i] == 0) continue;
            for(int j = 1; j <= W; ++j){
                dp[i + j] += dp[i] * 1.0 / W; 
            }
        }
        double ans = 0;
        for(int a = K; a <= N; ++a){
            ans += dp[a];
        }
        return ans;
    }
}
