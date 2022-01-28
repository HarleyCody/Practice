//My Soluton: Monotone stack try to use the smallest number in curIdx by comparing it with previous numbers
class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int[] ans = new int[k];
        int idx = -1;
        for(int i = 0; i < nums.length; ++i){
            while(0 <= idx && k - idx <= nums.length - i &&
                  nums[i] < ans[idx]){
                --idx;
            }
            if(idx < k - 1){
                ans[++idx] = nums[i];
            }
        }
        
        return ans;
    }
}
