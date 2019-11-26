_____________________________________________________________Concise dp Solution_____________________________________________________________
class Solution {
    int mod = 1000000007;
    public int numRollsToTarget(int d, int f, int target) {
        // dp[i][j] ith dice with target j;
        // dp[i][j] == sum(dp[i - 1][j - f] to dp[i - 1][j - 1]);
        int[][] dp = new int[d][target];
        for(int i = 0; i < Math.min(f, target); i++){
            dp[0][i] = 1;
        }
        long sum;
        for(int i = 1; i < d; i++){
            for(int j = i; j < Math.min((i + 1) * f, target); j++){
                sum  = 0;
                for(int k = Math.max(j - f, 0); k < j; k++){
                    sum += dp[i - 1][k];
                }
                dp[i][j] = (int)(sum % mod);
            }
        }
        return dp[d - 1][target - 1];
    }
}
_____________________________________________________________Naive dp Solution_____________________________________________________________
class Solution {
    int mod = 1000000007;
    public int numRollsToTarget(int d, int f, int target) {
        // dp[i][j] ith dice with target j;
        // dp[i][j] == sum(dp[i - 1][j - f] to dp[i - 1][j - 1]);
        int[][] dp = new int[d + 1][target + 1];
        for(int i = 1; i <= Math.min(f, target); i++){
            dp[1][i] = 1;
        }
        long sum;
        for(int i = 2; i <= d; i++){
            for(int j = i; j <= Math.min(i * f, target); j++){
                sum  = 0;
                for(int k = Math.max(j - f, 1); k < j; k++){
                    sum += dp[i - 1][k]; 
                }
                dp[i][j] = (int) (sum % mod);
            }
        }
        return dp[d][target];
    }
}
