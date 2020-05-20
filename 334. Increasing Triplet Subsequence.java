____________________________________________________________________Best Solution__________________________________________________________
class Solution {
    //update min if its smaller than min
    //update mid if min < i < mid;
    // if i > mid => 1: i> mid only happen when i has valid value
    //                  once mid is valid value means it has two value ascending, i will be 3rd one;
    // combination is min, mid, i;
    public boolean increasingTriplet(int[] nums) {
        if(nums.length < 3) return false;
        
        int len = nums.length, i = 0;
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        
        while(i < len){
            if(min > nums[i]){
                min = nums[i];
            }else if(min < nums[i] && nums[i] < mid){
                mid = nums[i];
            }else if(nums[i] > mid){
                return true;
            }
            ++i;
        }
        return false;
    }
}
