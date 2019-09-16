class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // start from i calculate contigously until sum >= s;
        // Math.min(ans, length);
        // if ans == length + 1; cannot find valid result; return 0;
        // else return ans;
        if(nums.length == 0) return 0;
        int ans = nums.length + 1;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            int j = i;
            int len = 0;
            while(j < nums.length && sum < s){
                sum += nums[j++];
                len++;
            }
            if(sum >= s){
                ans = Math.min(len, ans);
            }
        }
        return ans == nums.length + 1 ? 0 : ans;
    }
}
