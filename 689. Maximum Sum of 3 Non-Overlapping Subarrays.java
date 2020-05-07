_______________________________________________________________My(Best) Solution_________________________________________________________
class Solution {
    // dp[i] = k sum from start i;
    // in order to find three elements, use middle as partition;
    // sum = max in left part + middle + max in right part;
    // left : 0 ~ m - k, middle m - k ~ len - 2 * k - 1, right = m + k ~ len - k;
    
    /*
    improvement:
     maxForwardIndice records the idx of max element(left to right) in the dp,
     maxBackwardIndice records the idx of max element(right to left) in the dp
    */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[len - k + 1];
        int[] maxForwardIndice = new int[len - k + 1];
        int[] maxBackwardIndice = new int[len - k + 1];
        
        int ans[] = new int[3];
        int sum = 0; 
        for(int i = 0; i < k; ++i){
            sum += nums[i];
        }
        dp[0] = sum;
        
        for(int i = 0; i < len - k; ++i){
            sum += nums[i + k] - nums[i] ;
            dp[i + 1] = sum;
        }
        
        int maxForward = -1, maxForwardIdx = 0;
        int maxBackward = -1, maxBackwardIdx = 0;
        
        int revIdx = len - k;
        for(int i = 0; i < len - k; ++i){
            if(maxForward < dp[i]){
                maxForward = dp[i];
                maxForwardIdx = i;
            }
            if(maxBackward <= dp[revIdx - i]){
                maxBackward = dp[revIdx - i];
                maxBackwardIdx = revIdx - i;
            }
            maxForwardIndice[i] = maxForwardIdx;
            maxBackwardIndice[revIdx - i] = maxBackwardIdx;
        }
        
        int max = 0, l, r;
        // middle can only reach len - 2 * k + 1 at maximal
        // len - k + 1 == last valid sum in dp;
        // - k, left room for right;
        for(int m = k; m < len - 2 * k + 1; ++m){
            l = maxForwardIndice[m - k];
            r = maxBackwardIndice[m + k];
            sum = dp[l] + dp[m] + dp[r];
            if(max < sum){
                ans = new int[]{l,m,r};
                max = sum;
            }
        }
        return ans;
    }
}
