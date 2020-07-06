______________________________________________________________________My Solution(DFS + Memoization)______________________________________________________________________________
class Solution {
    //Recursion + memoization to get maxSum start at i with M
    // Use sum array to get sum by O(1) time;
    // use mem to record status;
    int[][] mem;
    int len;
    public int stoneGameII(int[] piles) {
        len = piles.length;
        mem = new int[len][len];
        
        for(int i = 1; i < len; ++i){
            piles[i] += piles[i - 1];
        }
        
        return stoneGame(piles, 0, 1);
    }
    
    private int stoneGame(int[] nums, int s, int m){
        if(s >= nums.length){
            return 0;
        }
        
        if(mem[s][m] != 0){
            return mem[s][m];
        }
        
        int ans = 0;
        int prev = s == 0 ? 0 : nums[s - 1];
        for(int i = 0; i < 2 * m && s + i < len; ++i){
            int maxM = Math.max(m, i + 1);
            int remainSum = nums[len - 1] - nums[s + i];
            // key: when Alex and Lee takes turn to get stones, so Alex can get the rest that Lee chosed.
            int newSum = nums[s + i] - prev + remainSum - stoneGame(nums, s + i + 1, maxM);
            ans = Math.max(ans, newSum);
        }
        mem[s][m] = ans;
        return ans;
    }
}
