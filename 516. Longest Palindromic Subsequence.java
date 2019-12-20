__________________________________________________________Best Solution(2D)________________________________________________________________

____________________________________________________________Dp Solution(2D)________________________________________________________________
class Solution {
    // dp[i][j] longest palindrome length of string start at i end at j
    // status transmission(reverse of recursion way)
    // chs[i] == chs[j] dp[i][j] = dp[i + 1][j - 1] + 2;
    // chs[i] != chs[j] max(dp[i + 1][j], dp[i][j - 1])
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if(len < 2) return len;
        int[][] dp = new int[len][len];
        for(int i = len - 1; 0 <= i ; --i){
            dp[i][i] = 1;
            for(int j = i + 1; j < len; ++j){
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
