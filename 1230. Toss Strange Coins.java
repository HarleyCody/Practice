//Concise Solution: same with mine just conlcude the zero and the rest target to one line(line 10)
class Solution {
    public double probabilityOfHeads(double[] prob, int k) {
        int n = prob.length;
        double dp[] = new double[k + 1];
        dp[0] = 1.0;
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(k, i + 1); j >= 0; j--) {
                double p = prob[i];
                dp[j] = (j > 0? dp[j - 1] : 0) * p + dp[j] * (1 - p);
            }
        }
        return dp[k];
    }
}

//My Solution: DP dp[h] means the prob that has the h to be head;
// Formula: dp[h] = dp[h] * (1 - prob[curCoin]); current is not head(from previous h head);
//            dp[h] = dp[h - 1] * prob[curCoin]; current is head
class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        double[] dp = new double[target + 1];
        int len = prob.length;
        dp[0] = 1;
        int start = 0;
        for(int i = 0; i < len; ++i){
            start = i + 1 > target ? target : i + 1;
            for(int h = start; 0 < h; --h){
                dp[h] *= (1 - prob[i]);
                dp[h] += dp[h - 1] * prob[i];
            }
            dp[0] *= (1 - prob[i]);
        }
        
        return dp[target];
    }
}
