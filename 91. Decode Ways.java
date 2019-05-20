class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        int tail = 0;
        
        dp[len] = 1;
        dp[len-1] = s.charAt(len-1) == '0'? 0:1;
        
        for(int i = len - 2; i >= 0; --i){
            
            tail = s.charAt(i+1) - 48 + 10 * (s.charAt(i) - 48);
            
            // ex: 1001, rlt should be 0;
            if(tail == 0)return 0;
            
            if(s.charAt(i) == '0') continue;// dp[i] = 0;
            else dp[i] = (tail <= 26) ? dp[i+1] + dp[i+2] : dp[i+1]; // > 26 i cannot be decode so follows the previous dp; otherwise can be decode, dp = sum of two previous dps.
        }
        return dp[0];
    }
}
