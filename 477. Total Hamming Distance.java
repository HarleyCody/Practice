//Best Solution: Same idea but calcualte distance bit by bit
class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int oneCnt = 0, zeroCnt = 0;
        int ret = 0;
        for (int i = 0; i < 30; i++) {
            oneCnt = 0;
            for (int j = 0; j < nums.length; j++) {
                oneCnt += (nums[j] >> i) & 1;
            }
            zeroCnt = n - oneCnt;
            ret += oneCnt * zeroCnt;
        }
        return ret;
    }
}
//My Solution: Store the num bit by bit
//dp[i][0] store the number of num that has 0 at ith bit
//dp[i][1] store the number of num that has 1 at ith bit
//the distance += dp[i][1] * dp[i][0]
class Solution{
	public int totalHammingDistance(int[] nums){
        int[][] dp = new int[33][2];
        int ans = 0;
        for(int num : nums){
            int idx = 0;
            while(idx < 33){
                ++dp[idx][num & 1];
                ans += dp[idx++][1 - (num & 1)];
                num >>= 1;
            }
        }
        return ans;
    }
}
