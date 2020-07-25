__________________________________________________________________ Best Solution__________________________________________________________________________________________
// Improve
// 1D dp, it only relative to previous status
class Solution {
    public int superEggDrop(int K, int N) {
        int dp[] = new int[K + 1], m = 0;
        for (m = 0; dp[K] < N; ++m)
            for (int k = K; k > 0; --k)
                dp[k] += dp[k - 1] + 1;
        return m;
    }
}
__________________________________________________________________Dp Solution________________________________________________________________________________________
class Solution {
    //dp[m][k] record the max number of floor we can check when take m moves with k eggs
    //max should less than N
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
            // we can check m - 1 moves with k - 1 eggs + m - 1 moves with k eggs + current floor
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
        }
        return m;
    }
}
