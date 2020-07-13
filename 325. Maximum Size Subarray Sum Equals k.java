________________________________________________________________Best Solution________________________________________________________________________________________
class Solution {
    // two sum
    // use hashmap to record sum and index, i alwyas be the leftest index for sum[i];
    // sum[i] - k == sum[left], i is right point, left is left point
    // i - left + 1 == length, as nums[idx] include idx, so the left = idx + 1
    // i - idx - 1 + 1 = i - idx;
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length == 0) return 0;
        
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i=0; i<nums.length; i++) {
            nums[i] += (i == 0 ? 0 : nums[i-1]);
            map.putIfAbsent(nums[i], i);
        }
        
        for (int i=nums.length-1; i >= max; i--) {
            if (map.containsKey(nums[i] - k)) max = Math.max(max, i - map.get(nums[i]-k));           
        }
        
        return max;
        
    }
}
__________________________________________________________________My Solution________________________________________________________________________________________
class Solution {
    // tarSum = sum - prevSum[i] - latSum[j];
    // ans = j - 1 - (i + 1) + 1 = j - i - 1;
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        if(len == 0){
            return 0;
        }
        int l = 0, r = 0, ans = nums[0] == k || nums[len - 1] == k ? 1 : 0;
        
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        int[] latSum = new int[len];
        latSum[len - 1] = nums[len - 1];
        
        for(int i = 1; i < len; ++i){
            preSum[i] = nums[i] + preSum[i - 1];
            latSum[len - i - 1] = nums[len - i - 1] + latSum[len - i];
            
            if(preSum[i] == k || latSum[len - i - 1] == k){
                ans = Math.max(ans, i + 1);
            }
        }
        
        int sum = latSum[0];
        for(int i = 0; i < len; ++i){
            int curSum = sum - preSum[i];
            if(curSum == k){
                ans = Math.max(ans, len - i - 1);
                continue;
            }
            for(int j = len - 1; i < j; --j){
                if(curSum - latSum[j] == k){
                    ans = Math.max(ans, j - i - 1);
                }
            }
        }
        
        return ans;
    }
}
