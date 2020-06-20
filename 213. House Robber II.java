class Solution {
    // rules: rob from second house, and rob from first house, if profits are different(rob from first house robbed first and last, so update rob from first house but do not rob last)
    // compare two rob, choose max
    //two status
    //dp0[i] max profit when reach to house i but do not rob house i
    //dp1[i] max profit when reach to house i and rob house i
    // transition formula
    //dp0[i] = max(dp1[i - 1], dp0[i - 1]);
    //dp1[i] = dp0[i - 1] + nums
    public int rob(int[] nums) {
        int len = nums.length; 
        if(len == 0){
            return 0;
        }
        if(len == 1){
            return nums[0];
        }
        int max1 = 0, max2 = 0;
        int[] dp0 = new int[len], dp1 = new int[len];
        dp1[1] = nums[1];
        for(int i = 2; i < len; ++i){
            dp0[i] = Math.max(dp0[i - 1], dp1[i - 1]);
            dp1[i] = dp0[i - 1] + nums[i];
        }
        max2 = Math.max(dp0[len - 1], dp1[len - 1]);
        
        dp0 = new int[len];
        dp1 = new int[len];
        dp1[0] = nums[0];
        for(int i = 1; i < len; ++i){
            dp0[i] = Math.max(dp0[i - 1], dp1[i - 1]);
            dp1[i] = dp0[i - 1] + nums[i];
        }
        max1 = Math.max(dp0[len - 1], dp1[len - 1]);
        
        if(max2 == max1){
            return max2;
        }
        
        max1 = Math.max(dp0[len - 1], dp1[len - 2]);
        return Math.max(max1, max2);
    }
}
