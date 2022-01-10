//My Solution: find the median by quickSelect, and calcualte steps
class Solution {
    public int minMoves2(int[] nums) {
        int tar = nums.length / 2;
        int median = find(nums, tar);
        
        int ans = 0;
        for(int num : nums){
            ans += Math.abs(num - median);
        }
        
        return ans;
    }
    
    private int find(int[] nums, int idx){
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int p = getPartition(nums, l, r);
            if(p == idx){
                return nums[p];
            }
            if(p < idx){
                l = p + 1;
            }else{
                r = p;
            }
        }
        return nums[r];
    }
    
    private int getPartition(int[] nums, int l, int r){
        int p = nums[l];
        while(l < r){
            while(l < r && nums[r] >= p){
                --r;
            }
            nums[l] = nums[r];
            while(l < r && nums[l] < p){
                ++l;
            }
            nums[r] = nums[l];
        }
        nums[l] = p;
        return l;
    }
}
