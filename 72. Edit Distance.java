class Solution {
    public int minDistance(String word1, String word2) {
        char[] first = word1.toCharArray();
        char[] second = word2.toCharArray();
        int f = word1.length();
        int s = word2.length();
        int[][] dp = new int[f+1][s+1];
        return helper(dp, first, second, f, s);
    }
    
    // if sec equals to fir, the min is depend on previous chars
    // else min is depend on previus min in (top, left and top left) + 1
    public int helper(int[][] dp, char[] fir, char[] sec, int f, int s){
        if(dp[f][s] != 0) return dp[f][s];
        if(f == 0) return dp[0][s] = s;
        if(s == 0) return dp[f][0] = f;
        if(sec[s-1] == fir[f-1]) return dp[f][s] = helper(dp, fir, sec, f-1, s-1);
        return dp[f][s] = Math.min(helper(dp, fir, sec, f-1, s), 
                                   Math.min(helper(dp, fir, sec, f-1, s-1), 
                                   helper(dp, fir, sec, f, s-1)))+1;
    }
}
