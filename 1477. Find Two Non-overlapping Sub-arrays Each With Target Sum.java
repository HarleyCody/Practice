___________________________________________________________________Best Solution_____________________________________________________________________________________
class Solution {
    // improve: update dp and ans in sliding window
    // sliding window to get valid subarray;
    // use dp to get minbefore subarray, concate before and current;
    public int minSumOfLengths(int[] arr, int target) {
        int left = 0;
        int sum = 0;
        int[] dp = new int[arr.length];
        int result = arr.length + 1, bestCount = arr.length + 1;
        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                int current = right - left + 1;
                bestCount = Math.min(bestCount, current);
                if (left > 0) {
                    result = Math.min(dp[left-1] + current, result);
                }
            }
            dp[right] = bestCount;
        }
        return result > arr.length ? -1 : result;
    }
}
____________________________________________________________________My Solution(Space 100%)_____________________________________________________________________________________
class Solution {
    // sliding window get the valid subarray, and record lens and end position
    // dp record minNext subarray
    // traverse every valid subarray add the lens, use min() to narrow down the ans
    public int minSumOfLengths(int[] arr, int target) {
        int l = 0, r = 0, len = arr.length, sum = 0;
        int[] lens = new int[len], end = new int[len];
        int[] dp = new int[len];
        while(l < len){
            while(r < len && sum < target){
                sum += arr[r++];
            }
            if(sum == target){
                lens[l] = r - l;
                end[l] = r;
            }
            sum -= arr[l++];
        }
        dp[len - 1] = lens[len - 1];
        for(int i = len - 2; 0 <= i; --i){
            if(lens[i] == 0){
                dp[i] = dp[i + 1];
            }else if(dp[i + 1] == 0){
                dp[i] = lens[i];
            }else{
                dp[i] = Math.min(dp[i + 1], lens[i]);
            }
            
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < len; ++i){
            if(end[i] < len && lens[i] != 0 && dp[end[i]] != 0){
                ans = Math.min(ans, lens[i] + dp[end[i]]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
