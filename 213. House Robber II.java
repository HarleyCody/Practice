________________________________________________________________Concise Solution(Best)___________________________________________________
class Solution {
    // only rob start or rob end
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }
    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
}
________________________________________________________________Improved Solution(Best)___________________________________________________
class Solution {
    // Improvement, only related to previous status, so use varaible not array to proceed dp;
    // rob1: from first to second last, rob2: from second to last
    // do not calculate rob1 from start to last => do not have to consider when rob1 != rob2 and update rob1 to from frist to second last
    public int rob(int[] nums) {
        int len = nums.length; 
        if(len == 0){
            return 0;
        }
        if(len == 1){
            return nums[0];
        }
        //do not rob first
        int max1 = 0;
        int notRob = 0, rob = nums[1], prevRob = 0;
        for(int i = 2; i < len; ++i){
            prevRob = rob;
            rob = notRob + nums[i];
            notRob = Math.max(notRob, prevRob);
        }
        max1 = Math.max(notRob, rob);
        
        // do not rob last
        int max2 = 0;
        notRob = 0; rob = nums[0]; prevRob = 0;
        for(int i = 1; i < len - 1; ++i){
            prevRob = rob;
            rob = notRob + nums[i];
            notRob = Math.max(notRob, prevRob);
        }
        max2 = Math.max(notRob, rob);
        
        return Math.max(max1, max2);
    }
}
__________________________________________________________________My Solution(Best)___________________________________________________
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
