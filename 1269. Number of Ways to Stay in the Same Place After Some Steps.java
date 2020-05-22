____________________________________________________________Best Solution_______________________________________________________________
class Solution {
    //dp[j] = dp[j - 1][steps - 1] + dp[j][steps - 1] + dp[j + 1][steps - 1];
    // dp[i] means how many ways for 0 go to point i; 
    public int numWays(int steps, int arrLen) {
        if (steps == 1 || arrLen == 1) return 1;
        
        int MOD = 1000000007;
        arrLen = Math.min(steps / 2 + 1, arrLen);
        int[] dp = new int[arrLen + 2];
        dp[1] = 1;
        for (int i = 0; i < steps; i++) {
            int prev = 0;
            for (int j = 1; j <= arrLen; j++) {
                int temp = dp[j];
                dp[j] = ((dp[j] + prev) % MOD + dp[j+1]) % MOD;
                prev = temp;
            }
        }
        return dp[1];
    }
}
____________________________________________________________My(Best) Solution___________________________________________________________
class Solution {
    //dp for mem
    //accumulate recursivly
    int[][]dp;
    public int numWays(int steps, int arrLen) {
        int dis = steps / 2 + 1;
        dp = new int[dis][steps + 1];
        dp[0][0] = 1;
        
        steps(0, steps, arrLen);
        
        return dp[0][steps];
    }
    
    private int steps(int cur, int steps, int rightBound){
        if(cur < 0 || cur > steps || cur == rightBound){
            return 0;
        }
        if(cur == steps){
            dp[cur][steps] = 1;
            return 1;
        }else if(dp[cur][steps] != 0){
            return dp[cur][steps];
        }
        
        int ans = 0;
        ans += steps(cur, steps - 1, rightBound);
        ans = (ans + steps(cur - 1, steps - 1, rightBound)) % 1000000007;
        ans = (ans + steps(cur + 1, steps - 1, rightBound)) % 1000000007;
        
        dp[cur][steps] = ans;
        return ans;
    }
}
