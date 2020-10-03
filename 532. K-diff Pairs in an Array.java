________________________________________________________________________________________Best Solution___________________________________________________________________________
class Solution {
    // sort to make sure pair is unique, as it start with first num
    // sliding window to find the other one
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int left=0,right=1;
        int ans=0;
        
        while(left<nums.length && right<nums.length)
        {
            if(left==right || nums[right] - nums[left] < k)
                right++;
            
            else if(nums[right] - nums[left] > k)
                left++;
            else
            {
                left++;
                ans++;
                while(left<nums.length && nums[left] == nums[left-1])
                {
                    left++;
                }
            }
            
            
        }
        return ans;
    }
}
_______________________________________________________________________________________My Solution___________________________________________________________________________
class Solution {
    // two sum, recorder record the number in nums
    // visited record b of pair, so to speak the max in the pair.
    // in any number it can be a and it can be b, so try to find the other one by set
    public int findPairs(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        HashSet<Integer> recorder = new HashSet();
        HashSet<Integer> visited = new HashSet();
        for(int i = 0; i < len; ++i){
            if(!visited.contains(nums[i]) && recorder.contains(nums[i] - k)){
                ++ans;
                visited.add(nums[i]);
            }
            if(!visited.contains(nums[i] + k) && recorder.contains(nums[i] + k)){
                ++ans;
                visited.add(nums[i] + k);
            }
            recorder.add(nums[i]);
        }
        
        return ans;
    }
}
