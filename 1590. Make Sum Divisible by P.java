_____________________________________________________________________________Best Solution__________________________________________________________________
class Solution {
    // two sum record the idx of cur reminder, update it from left to right
    // sum - prefixSum = tailSum;
    // p - tailSum = the sum that is needed in prefix to be disvisible by p;
    // Combine formula p + prefixSum - sum = the sum that is needed in prefix to be disvisible by p;
    // use two sum way to update ans
    public int minSubarray(int[] nums, int p) {
        long remainder = 0, prefixSum = 0; 
        int n = nums.length, minLen = n;
        for (int num : nums) {
            remainder = (remainder + num) % p; 
        }
        if (remainder == 0) {
            return 0;
        }
        var prefixSumToIndex = new HashMap<Long, Integer>();
        prefixSumToIndex.put(prefixSum, -1);
        for (int i = 0; i < n; ++i) {
            prefixSum = (prefixSum + nums[i]) % p;
            long key = (prefixSum - remainder + p) % p;
            if (prefixSumToIndex.containsKey(key)) {
                minLen = Math.min(minLen, i - prefixSumToIndex.get(key));
            }
            prefixSumToIndex.put(prefixSum, i);
        }
        return minLen == n ? -1 : minLen;                     
    }
}
