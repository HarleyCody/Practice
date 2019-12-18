___________________________________________________My Solution(Improved)________________________________________________________
class Solution {
    public int missingElement(int[] nums, int k) {
        int ans = 0, len = nums.length;
        // missing determine kth is in nums or not;
        int missing = nums[len - 1] - nums[0] + 1 - len;
        // kth is not in nums return last num + gap;
        if(missing < k){
            return nums[len - 1] + k - missing;
        }
        // kth is in nums find one by one;
        for(int i = 1; i < nums.length; ++i){
            int gap = nums[i] - nums[i - 1] - 1;
            if(gap != 0){
                if(gap >= k){
                    return ans = nums[i - 1] + k;
                }else{
                    k -= gap;
                }
            }
        }
        return 0;
    }
}
_________________________________________________________My Solution__________________________________________________________
class Solution {
    public int missingElement(int[] nums, int k) {
        int ans = 0;
        for(int i = 1; i < nums.length; ++i){
            int gap = nums[i] - nums[i - 1] - 1;
            // not consecutive numbers
            if(gap != 0){
                // kth is in the gap nums[i - 1] < kth < nums[i] 
                if(gap >= k){
                    return ans = nums[i - 1] + k;
                }else{
                    // pass gap missing numbers, find rest kth
                    k -= gap;
                }
            }
        }
        // kth is not in nums, last + k == kth
        return nums[nums.length - 1] + k;
    }
}
