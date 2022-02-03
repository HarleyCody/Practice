//My Solution: DP. dp[i][j] means pick j from left at length i
// dp[i][j] = max(dp[i - 1][j] + current use right, dp[i - 1][j] + current use left)
class Solution{
	public int maximumScore(int[] nums, int[] multipliers){
		int len = multipliers.length;
        int[][] dp = new int[len + 1][len + 1];
        int ans = Integer.MIN_VALUE;
        for(int i = 1; i <= len; ++i){
            int multiplier = multipliers[i - 1];
            dp[i][0] = dp[i - 1][0] + multiplier * nums[nums.length - i];
            dp[i][i] = dp[i - 1][i - 1] + multiplier * nums[i - 1];
            for(int j = 1; j < i; ++j){
                dp[i][j] = Math.max(dp[i - 1][j] + multiplier * nums[nums.length - i + j], dp[i - 1][j - 1] + multiplier * nums[j - 1]);
            }
        }
        for(int i = 0; i <= len; ++i){
            ans = Math.max(ans, dp[len][i]);
        }

        return ans;
    }
}

//My Solution: Dfs with memoization
class Solution{
	int ans = Integer.MIN_VALUE;
	HashMap<Integer, Integer>[] mem;
	boolean[][] visited;
	public int maximumScore(int[] nums, int[] multipliers){
		int len = multipliers.length;
		mem = new HashMap[nums.length];
		return getMax(nums, multipliers, 0, nums.length - 1, len - 1);
}

private int getMax(int[] nums, int[] multipliers, int left, int right, int m){
	if(m < 0) return 0;
	if(mem[left] != null && mem[left].containsKey(right)) return mem[left].get(right);
    
	int leftSum = multipliers[multipliers.length - m - 1] * nums[left] + getMax(nums, multipliers, left + 1, right, m - 1);
	int rightSum = multipliers[multipliers.length - m - 1] * nums[right] + getMax(nums, multipliers, left, right - 1, m - 1);
	int curMax = Math.max(leftSum, rightSum);
    if(mem[left] == null){
        mem[left] = new HashMap();
    }
    mem[left].put(right, curMax);
return curMax;
}
}
