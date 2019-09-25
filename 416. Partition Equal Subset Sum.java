________________________________________________________________dp Solution________________________________________________________________
class Solution {
    // 0 1 backpack
    // calculate sum, dp;
    // dp[i][j] : with previous i and i elements consist sum to j; if it can true, otherwise false;
    public boolean canPartition(int[] nums) {
        if(nums.length < 2) return false;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        
        //has to be even number
        if((sum & 1) == 1) return false;
        int tar = sum / 2;
        // dp (01 backpack)
        boolean[][] recorder = new boolean[nums.length + 1][tar + 1];
        // every i can sum up to 0
        for(int i = 0; i < recorder.length; i++){
            recorder[i][0] = true;
        }
        
        for(int i = 1; i < recorder.length; i++){
            for(int j = 1; j < recorder[0].length; j++){
                // 
                recorder[i][j] = recorder[i - 1][j];
                // can be derive from previous status without current value dp[i-1][j - nums[i - 1]];
                // nums[i - 1] == current value, as dp starts from 1;
                if(j >= nums[i - 1]){
                    recorder[i][j] = recorder[i - 1][j] || recorder[i - 1][j - nums[i - 1]];
                }
            }
        }
        return recorder[nums.length][tar];
    }
}
