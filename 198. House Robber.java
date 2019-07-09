class Solution {
    // key is rob current or not, if previous max( excpet last neighbor) + current is larger than  neighbor, than rob current, otherwise, rober neighbor(nums[i] == nums[i - 1])
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        if(len == 1) return nums[0];
        if(len == 2) return Math.max(nums[1],nums[0]);
        // recorder previous largest value;
        int preMax = nums[0];
        for(int i = 2; i < len; ++i){
            // rob neighbor
            if(nums[i - 1]  > preMax + nums[i]){
                nums[i] = nums[i - 1];
                preMax = nums[i - 1];
            }
            else{
                // rob current
                nums[i] = preMax + nums[i];
                // update preMax
                if(preMax < nums[i - 1]) preMax = nums[i - 1]; 
            }
        }
        return nums[len - 1];
    }
}
