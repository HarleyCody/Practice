class Solution {
    public int findPeakElement(int[] nums) {
        return find(nums, 0, nums.length - 1);
    }
    private int find(int[] nums, int start, int end){
        // approching from start and end, only start == end will be peak
        if(start == end){
            return start;
        }else{
            int mid = start + (end - start) / 2;
            int later = mid + 1;
            // mid is larger then later one, peak is possibly in previous part
            if(nums[mid] > nums[later]){
                return find(nums, start, mid);
            }else{ // mid is smaller then later one, peak is possibly in later part
                return find(nums, later, end);
            }
        }
    }
}
