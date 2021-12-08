//Best Solution: DFS with memoization to get the possibility in previous status for target
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        
        dp[0] = 1;
        helper(nums, target, dp);
        return dp[target];
        
    }
    
    private int helper(int[]nums, int target, int[] dp) {
        if(dp[target] > -1) return dp[target];
        int res = 0;
        
        for(int i: nums) {
            if(i <= target) {
                res += helper(nums, target-i, dp);
            }
        }
        
        dp[target] = res;
        return dp[target];
    }
}
//My Solution: need to go one more layer in recursive for checking target, this can be done in last layer
class Solution {
    int[] memo;
    public int combinationSum4(int[] nums, int target) {
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return findComboCount(nums, target);
    }
    
    private int findComboCount(int[] nums, int target){
        if(target < 0) return 0;
        if(target == 0) return 1;
        if(memo[target] != -1) return memo[target];
        
        int count = 0;
        for(int num : nums){
            count += findComboCount(nums, target - num);
        }
        
        return memo[target] = count;
    }
}
