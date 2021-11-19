//Best Solution: Same dp idea, dp[l][r] = dp[l][k] + dp[k + 1][r - 1];
//Skip the duplicate when finding k
class Solution {
    public int strangePrinter(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        
        if (len < 2) return len;
        
        int[] cur = new int[26];
        int[] next = new int[len];
        Arrays.fill(next, -1);
        Arrays.fill(cur, -1);
        cur[chs[0] - 'a'] = 0;
        int size = 1;
        
        //!!set index from current index to next same char for sake of finding middle faster, reverse order, from larget to small so dp has to start from len - 1 to 0
        for (int i = 1; i < len; ++i) {
            if (chs[i - 1] == chs[i]) 
                continue;
            
            int idx = chs[i] - 'a';
            
            if (cur[idx] != -1) 
                next[cur[idx]] = size;
            
            cur[idx] = size++;
        }
        
        int[][] dp = new int[size][size];
        for (int l = size - 1; l >= 0; --l) {
            Arrays.fill(dp[l], Integer.MAX_VALUE);
            for (int m = l; m < size; ++m) {
                if (m == l) 
                    dp[l][m] = 1;
                else 
                    dp[l][m] = Math.min(dp[l][m], dp[l][m - 1] + 1);
                for (int r = next[m]; r != -1; r = next[r]) 
                    dp[l][r] = Math.min(dp[l][r], dp[l][m] + dp[m + 1][r - 1]);
            }
        }
        return dp[0][size - 1];
    }
}
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
