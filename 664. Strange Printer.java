//DP: Dp[left][right]: min print from left to right;
//Dp[left][right] = dp[left][k] + dp[k + 1][right - 1]
//This could be use to get the operation of string which can be divided as subquestions
class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int len = 0; len < n; len++) {
            for (int left = 0; left + len < n; left++) {
                int right = left + len;
                // deals with single character
                if (left == right) {
                    dp[left][right] = 1;
                } else {
                    dp[left][right] = dp[left][right-1] + 1;
                }
                for (int k = left; k < right; k++) {
                    if (s.charAt(k) == s.charAt(right)) {
                        dp[left][right] = Math.min(dp[left][right], dp[left][k] + dp[k+1][right-1]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
