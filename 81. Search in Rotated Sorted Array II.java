__________________________________________________________________My Solution___________________________________________________________
class Solution {  
    // find rotate point and binary search
    public boolean search(int[] nums, int target) {
        int len = nums.length, p = 0;
        if(len == 0){
            return false;
        }
        while(p < len - 1){
            if(nums[p] > nums[p + 1]){
                break;
            }
            ++p;
        }
        
        if(nums[p] < target){
            return false;
        }
        return search(nums, 0, p, target) || search(nums, p + 1, len - 1, target);
    }
    
    private boolean search(int[] nums, int s, int e, int tar){
        if(e < s){
            return false;
        }
        int m = (s + e) / 2;
        if(nums[m] == tar){
            return true;
        }else if(nums[m] < tar){
            return search (nums, m + 1, e, tar);
        }else{
            return search(nums, s, m - 1, tar);
        }
    }

}
