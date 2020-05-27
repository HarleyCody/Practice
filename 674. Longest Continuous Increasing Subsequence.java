class Solution {
    //two pointers narrow down the range of increasing sequence, update l to r
    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length, l = 0, r = 0;
        int ans = 0;
        while(l < len){
            while(++r < len && nums[r] > nums[r - 1]){
            }
            int curLen = r - l;
            ans = Math.max(curLen, ans);
            l = r;
        }
        return ans;
    }
}
