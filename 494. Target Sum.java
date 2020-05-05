___________________________________________________________________My Solution____________________________________________________________
// backtracking
class Solution {
    int curIdx = 0, curSum = 0, ans = 0;
    public int findTargetSumWays(int[] nums, int S) {
        
        if(curIdx == nums.length){
            if(curSum == S){
                ++ans;
            }
            return ans;
        }
        
        curSum += nums[curIdx];
        ++curIdx;
        findTargetSumWays(nums, S);
        --curIdx;
        curSum -= nums[curIdx];
        
        curSum -= nums[curIdx];
        ++curIdx;
        findTargetSumWays(nums, S);
        --curIdx;
        curSum += nums[curIdx];
        
        return ans;
    }
}
