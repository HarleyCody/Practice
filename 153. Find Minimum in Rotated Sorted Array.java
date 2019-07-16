__________________________________________________________Binary Search(logN)__________________________________________________
class Solution {
    public int findMin(int[] nums) {
        int minimal = nums[0];
        int start = 0, end = nums.length - 1;
        while(start < end){
            int mid = start + (end - start) / 2;
            if(mid > 0 && nums[mid] < nums[mid - 1]) return nums[mid];
            // mid is not answer, in case of [2, 1] mid = 0 == start; mid should move to later part
            if(nums[mid] >= nums[start] && nums[mid] >= nums[end]){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return nums[start];
    }
}
__________________________________________________________For loop(On)_________________________________________________________
class Solution {
    public int findMin(int[] nums) {
        int minimal = nums[0];
        for(int i = 1; i < nums.length; ++i){
            if(minimal > nums[i]){
                minimal = nums[i];
            }
        }
        return minimal;
    }
}
