class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // choose one kind of coin;
        for(int coin : coins){
            // calculate how many ways can be change by only use current coin;
            for(int i = coin; i <= amount; ++i){
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
