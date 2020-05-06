________________________________________________________________Best DP____________________________________________________________
//https://leetcode.com/problems/target-sum/discuss/295929/Java-2ms-easy-to-understand-dp-solution-with-explanation
class Solution {
    // find S1 subset and S2 subset and their Math.abs will be S;
    // S1 + S2 = Sum;
    // S1 - S2 = S; so the S1 would be (S + Sum) / 2
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;            
        }
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        // if Sum is odd S is even its impossible to use all to sum up to S
        // ie Sum and S must be same at odd ot even;
        if (sum < S || (sum + S) % 2 != 0) {
            return 0;
        }
        // p = average number betweem Sum and s
        // as sum may be larger than S, so the maximal it can reach is average number, ie S + (sum - S) / 2 = (S + sum) >> 1;
        // in case of S + (sum - S) / 2, this can still reach by - (sum - S) / 2, as maximal it only can be Sum
        int p = (sum + S) >> 1;
        int[] dp = new int[p+1];
        dp[0] = 1;
        for (int num : nums) {
            for(int i = p; i >= num ; i--) {
                dp[i] += dp[i-num];
            }
        }
        return dp[p];
    }
}
________________________________________________________________Best Solution____________________________________________________________
public class Solution {
    // use dp to record when it comes to sum at i, how many ways can be collected
    public int findTargetSumWays(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }
}

__________________________________________________________________Better Solution(Memoization)___________________________________________
public class Solution {
    // mem[i][sum] == num of ways when sum at pos i can reach.
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for (int[] row: memo)
            Arrays.fill(row, Integer.MIN_VALUE);
        return calculate(nums, 0, 0, S, memo);
    }
    public int calculate(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S)
                return 1;
            else
                return 0;
        } else {
            if (memo[i][sum + 1000] != Integer.MIN_VALUE) {
                return memo[i][sum + 1000];
            }
            int add = calculate(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculate(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum + 1000] = add + subtract;
            return memo[i][sum + 1000];
        }
    }
}
___________________________________________________________________My Solution____________________________________________________________
// backtracking
class Solution {
    int curIdx = 0, curSum = 0, ans = 0;
    public int findTargetSumWays(int[] nums, int S) {
        
        if(curIdx == nums.length){
            if(curSum == S){
                ++ans;
            }
            return ans;
        }
        
        curSum += nums[curIdx];
        ++curIdx;
        findTargetSumWays(nums, S);
        --curIdx;
        curSum -= nums[curIdx];
        
        curSum -= nums[curIdx];
        ++curIdx;
        findTargetSumWays(nums, S);
        --curIdx;
        curSum += nums[curIdx];
        
        return ans;
    }
}
