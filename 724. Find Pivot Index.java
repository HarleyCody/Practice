class Solution {
    // sum of nums, compare nums[i - 1](ie, left) with nums[len - 1] - nums[i](ie, right)
    // PS [1, 0] return 1, so no left element == left = 0
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        for(int i = 1; i < len; ++i){
            nums[i] += nums[i - 1];
        }
        int left = 0;
        for(int i = 0; i < len; ++i){
            if(left == nums[len - 1] - nums[i]){
                return i;
            }
            left = nums[i];
        }
        return -1;
    }
}
