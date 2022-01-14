//Best Solution: Sliding window with right bound, and shrink left bound
class Solution {
    public int maxFrequency(int[] nums, int k) {
        
        Arrays.sort(nums);
        
        int i=0;
        int j=0;
        long m=(long)k;
        
        while(j<nums.length){
            m+=nums[j];
            if(m<(long)nums[j]*(j-i+1))
                m-=nums[i++];
            
           j++;
        }
        return j-i;
    }
}

//My Solution: Sliding window to get the max length right is the target number, expand when k > 0
class Solution {
    public int maxFrequency(int[] nums, int k) {
        if(nums.length == 1) return 1;
        Arrays.sort(nums);
        int r = 0;
        int ans = 0;
        for(int i = 0; i < nums.length; ++i){
            while(r < nums.length - 1 && k >= 0){
                ++r;
                int cost = (nums[r] - nums[r - 1]) * (r - i);                
                k -= cost;
            }
            ans = Math.max(k >= 0 ? r - i + 1 : r - i, ans);
            k += nums[r] - nums[i];
        }
        
        return ans;
    }
}
