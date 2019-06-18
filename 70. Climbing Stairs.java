class Solution {
    public int climbStairs(int n) {
        // n = 0,1,2 result = n;
        if(n < 3) return n;
        // result of n = result of n - 1(one step from previous) + result of n - 2(two steps from previous)
        int[] dp = new int[n];
        // initial two start point, as dp[n] = dp[n - 1] + dp[n - 2]
        dp[0] = 1;
        dp[1] = 2;
        // start from dp[2], dp[i] means result of i + 1
        for(int i = 2; i < n; ++i)
            dp[i] = dp[i-1] + dp[i - 2];
        return dp[n -1];
    }
}
