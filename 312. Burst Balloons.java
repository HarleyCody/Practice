_________________________________________________________________dp Solution_______________________________________________________________
class Solution {
    // dp[i][j] ==> mem record the result start from i end at j;
    // accumulate the result by distance and start point
    // calcualte all possible start point with distance k
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len + 2][len + 2];
 
        int[] n = new int[len + 2];
        for(int i = 0; i < len; ++i){
            n[i + 1] = nums[i];
        }
        n[0] = n[len + 1] = 1;
        
        for(int l = 2; l < len + 2; ++l){
            for(int left = 0; left < len + 2 - l; ++left){
                int right = left + l;
                
                for(int i = left + 1; i < right; ++i){
                    dp[left][right] = Math.max(dp[left][right], dp[left][i] + dp[i][right] + n[left] * n[i] * n[right]);
                }
            }
        }
        return dp[0][len + 1];
    }
}
_______________________________________________________________D & C recursion Solution__________________________________________________
class Solution {
    // recursivly calculate mem
    // mem[i][j] = maxCoins from i to j
    int[][] mem;
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] n = new int[len + 2];
        
        for(int i = 0; i < len; ++i){
            n[i + 1] = nums[i];
        }
        n[0] = n[len + 1] = 1;
        
        mem = new int[len + 2][len + 2];
        return dc(n, 0, len + 1);
    }
    
    private int dc(int[] n, int start, int end){
        if(start + 1 == end){
            return 0;
        }
        if(mem[start][end] > 0){
            return mem[start][end];
        }
        for(int i = start + 1; i < end; ++i){
            mem[start][end] = Math.max(mem[start][end], n[start] *  n[i] * n[end] + dc(n, start, i) + dc(n, i, end));
        }
        //System.out.println("sum at " + start + " " + end + " is " + mem[start][end]);
        return mem[start][end];
    }
}
