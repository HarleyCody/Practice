__________________________________________________________Best Solution(2D)________________________________________________________________
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // record maxlength for start at i end at j;
        int[] dp = new int[n];
        char[] ss = s.toCharArray();
        Arrays.fill(dp,1);      // A single char is count as a palindrom with length 1
        for (int j = 1; j < n; j++) {
            // i and j start with different as 1, no character between them initialiy (curLong == 0)
            // update dp for every level;
            int curLong = 0;
            for (int i = j - 1; i >= 0; --i) {
                // retrive max palindorme length in last level.
                int tmp = dp[i];    // dp[i][j]
                if (ss[i] == ss[j]) {
                    dp[i] = curLong + 2;
                }
                // record max palindrom length which is defintely within range i + 1 , j - 1;
                // for next use. 
                curLong = Math.max(curLong, tmp);
            }
        }
        int max = 1;
        for (int m : dp) max = Math.max(max, m);
        return max;
    }
}
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
