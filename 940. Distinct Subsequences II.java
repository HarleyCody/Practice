//Best Solution: dp[k] = 2 * dp[k-1] - dp[last[S[k]] - 1]
class Solution{
	public int distinctSubseqII(String str){
		int mod = (int)1e9 + 7;
		int[] lastOcc = new int[26];
    int prev = 1;
		// As we start from empty string so current value is for next element
		for(int i = 0; i < str.length(); ++i){
        int subtract = lastOcc[str.charAt(i) - 'a'];
        int current = (prev << 1) % mod;
        current = (current + mod - subtract) % mod;
        lastOcc[str.charAt(i) - 'a'] = prev;
        prev = current;
    }
    // -1 delete the empty string
    return prev - 1;
  }
}
