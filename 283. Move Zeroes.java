class Solution {
    public void moveZeroes(int[] nums) {
        int[] ans = new int[nums.length];
        int start = 0;
        for(int i = 0 ; i < nums.length; ++i){
            if(nums[i] != 0) ans[start++] = nums[i];
        }
        System.arraycopy(ans,0,nums,0,nums.length);
        //for(int i = 0 ; i < nums.length; ++i){
          //  nums[i] = ans[i];
        //}
    }
}
