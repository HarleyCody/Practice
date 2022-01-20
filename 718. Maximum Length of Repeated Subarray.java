//My Solution: Dynamic programming, current max length depends on the previous max length;
class Solution{
	public int findLength(int[] nums1, int[] nums2){
		int len1 = nums1.length;
		int len2 = nums2.length;
		
        int[][] dp = new int[len1][len2];
        int ans = 0;
        for(int i = 0; i < len1; ++i){
            for(int j = 0; j < len2; ++j){
                if(nums1[i]  == nums2[j]){
                    int prev = 0;
                    if(i > 0 && j > 0){
                        prev = dp[i - 1][j - 1];
                    }
                    dp[i][j] = Math.max(1 + prev, dp[i][j]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
