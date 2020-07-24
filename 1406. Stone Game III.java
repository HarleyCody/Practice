______________________________________________________________________________Best Solution______________________________________________________________________
// improve dp record max diff not max number, does not have to use sum array any more 
class Solution {
    // from tail to head
    // dp[i] recorder max number of stones one person can have more than to the other's at position i;
    // instead of recorder max number some one can have,
    // dp just record max diff(negative will be regard as small) to the other;
    // so do not need sum array here
    public String stoneGameIII(int[] A) {
        int n = A.length, dp[] = new int[n+1];
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = Integer.MIN_VALUE;
            for (int k = 0, take = 0; k < 3 && i + k < n; ++k) {
                take += A[i + k];
                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}
________________________________________________________________________________My Solution______________________________________________________________________
class Solution {
    // mem + dfs
    // min max, curMax == curVals + later min(laterSum - laterMin)
    int[] mem;
    int[] sum;
    int len;
    public String stoneGameIII(int[] stoneValue) {
        len = stoneValue.length;
        sum = new int[len];
        mem = new int[len];
        
        sum[0] = stoneValue[0];
        for(int i = 1; i < len; ++i){
            sum[i] = stoneValue[i] + sum[i - 1];
        }
        
        int aScore = collect(stoneValue, 0);
        int bScore = sum[len - 1] - aScore;
        
        return aScore == bScore ? "Tie" : aScore < bScore ? "Bob" : "Alice";
    }
    
    private int collect(int[] val, int pos){
        if(pos == len){
            return 0;
        }
        if(mem[pos] != 0){
            return mem[pos];
        }
        
        int ans = -50000000;
        int cur = 0;
        for(int i = pos; i < Math.min(pos + 3, len); ++i){
            cur += val[i];
            int next = sum[len - 1] - sum[i] - collect(val, i + 1);
            ans = Math.max(cur + next, ans);
        }
        
        mem[pos] = ans;
        
        return ans;
    }
}
