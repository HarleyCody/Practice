/*
3 3 1 
3 * 2 * 1 = 6
2 3 0
com 2 2 2 = 8;
duplicate 0 2 
n == goal  ans = n * (n - 1) *(n - 2) … 1;
Dynamic Programming: dp[i][j] : number of play list when list length is i and has j unique songs
dp[i][j] = dp[i - 1][j - 1] * (N - j + 1);
dp[i][j] += dp[i - 1][j] * Math.max(j - K, 0);
prev with j unique or prev with j - 1 unique
*/

class Solution{
	public int numMusicPlaylists(int n, int goal, int k){
	    if(n > goal) return 0;
	    int mod = (int)1e9 + 7;
	    long[][] dp = new long[goal + 1][n + 1];
	    dp[0][0] = 1;
	    for(int i = 1; i <= goal; ++i){
            for(int j = 1; j <= n; ++j){
	          dp[i][j] = dp[i - 1][j - 1] * (n - j + 1);
	            dp[i][j] += dp[i - 1][j] * Math.max((j - k), 0);
	            dp[i][j] %= mod;
            } 	
        }
        return (int) dp[goal][n];
    }
}
