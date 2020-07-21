class Solution {
    // sliding window get certain range of valid array
    // calculate the number of subsequence
    // pow[high - low] means start from low with rest high - low numbers to construct sequence(total hight - low + 1 nums)
    // so there is not duplicate as it has start from low 
    private static final int MOD = 1000000007;
    public int numSubseq(int[] nums, int target) {
        long[] modPow = new long[nums.length + 1];
        modPow[0] = 1;
        for (int i = 1; i < modPow.length; i++) {
            modPow[i] = 2 * modPow[i-1] % MOD;
        }
        
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length -1;
        long result = 0;
        while (low <= high) {
            if (nums[low] + nums[high] > target) {
                high--;
            } else {
                long curPower = modPow[high-low];
                result += curPower%MOD;
                low++;
            }
        }
        result = result%MOD;
        return (int) result;
    }
}
