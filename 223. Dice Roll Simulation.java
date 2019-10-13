class Solution {
    // dp record roll i times dice, the ith dice is j;
    // equation( axxb xx='11' 1 times = 2) dp[b][1] = sum(dp[b-1][1~6] - dp[a][2~6])
    // a cannot be 1 casuse if a = 1 is overlap situation, if it is deducted, ans will be smaller than expected.
    public int dieSimulator(int n, int[] rollMax) {
        long divisor = (long)Math.pow(10, 9) + 7;
        long[][] dp = new long[n][7];
        for(int i = 0; i < 6; i++) {
            dp[0][i] = 1;
        }
        dp[0][6] = 6;
        for(int i = 1; i < n; i++) {
            // record dp[i][1~6]
            long sum = 0;
            for(int j = 0; j < 6; j++) {
                dp[i][j] = dp[i - 1][6];
                // can add more i;
                if(i - rollMax[j] < 0) {
                    dp[i][j] = dp[i - 1][6];
                }
                // i can be consecutive. + other number
                else if(i - rollMax[j] > 0) {
                    // can consectutive j is substring in i(not only consecutive j in i)
                    dp[i][j] = (dp[i][j] - (dp[i - rollMax[j] - 1][6] - dp[i - rollMax[j] - 1][j])) % divisor + divisor;
                }
                // i can only be consecutive j;
                else{ 
                    // 1 : possbility of in i - 1 all is j;
                    dp[i][j] = (dp[i][j] - 1) % divisor;
                }
                // update sum;
                sum = (sum + dp[i][j]) % divisor;
            }
            // sum of dp[i][1 ~ 6]
            dp[i][6] = sum;
        }
        return (int)(dp[n - 1][6]);
    }
}
