____________________________________________________________Best Solution(DP)______________________________________________________________
class Solution {
// status transition fomula dp[i][j] = dp[prev][i] + 1
// if prev is not exist dp[i][j] = 2; start status
    public int longestArithSeqLength(int[] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        int[] idx = new int[10001];
        Arrays.fill(idx, -1);
        int ans = 0;
        for(int i = 0; i < len; ++i){
            idx[A[i]] = i;
            for(int j = i + 1; j < len; ++j){
                int prev = A[i] + A[i] - A[j];
                if(prev >= 0 && prev <= 10000 && idx[prev] != -1){
                    dp[i][j] = dp[idx[prev]][i] == 0 ? 2 : dp[idx[prev]][i] + 1;
                }else{
                    dp[i][j] = 2;
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
____________________________________________________________My Solution(Bruteforce)___________________________________________________________________
class Solution {
    public int longestArithSeqLength(int[] A) {
        int len = A.length;
        int ans = 0;
        for(int i = 0 ; i < len; ++i){
            for(int j = i + 1; j < len; ++j){
                if(ans > len - j)break;
                int diff = A[j] - A[i];
                int k = j + 1, curLen = 2, prev = A[j];
                while(k < len){
                    if(A[k] - prev == diff){
                        ++curLen;
                        prev = A[k];
                    }
                    ++k;
                }
                ans = Math.max(curLen, ans);
            }
        }
        return ans;
    }
}
